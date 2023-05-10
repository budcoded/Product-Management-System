package com.inventory.productmanagementsystem.Service;

import com.inventory.productmanagementsystem.Exceptions.ResourceNotFoundException;
import com.inventory.productmanagementsystem.Model.User;
import com.inventory.productmanagementsystem.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<User> createUser(User user) {
        logger.info("To create a user first checking if given email exists or not.");
        logger.info("Provided Email: " + user.getEmail());
        Optional<User> userOptional = userRepository.findUserByEmail(user.getEmail());
        if (userOptional.isPresent()) {
            logger.info("User with Email: " + user.getEmail() + " already exists.");
            return ResponseEntity.status(HttpStatusCode.valueOf(400)).body(null);
        } else {
            logger.info("Created a new user with Email: " + user.getEmail());
            User savedUser = userRepository.save(user);
            return ResponseEntity.ok().body(savedUser);
        }
    }

    public User updateUser(User user, Long userId) {
        logger.info("Updating user with given id: " + userId);
        logger.info("Checking if the user exists or not.");
        User user1 = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        user1.setName(user.getName());
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        user1.setMobileNumber(user.getMobileNumber());
        user1.setAddress(user.getAddress());
        logger.info("User with user id: " + userId + " updated successfully.");
        return this.userRepository.save(user1);
    }

    public User getUserById(Long userId) {
        logger.info("Getting user's information with given user id: " + userId);
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        user.getOrderList().forEach((order) -> {
            order.getProductList().clear();
            order.setUserId(null);
        });
        user.getComplaintList().forEach((complaint) -> {
            complaint.setUser(null);
        });
        return user;
    }

    public List<User> getAllUsers() {
        logger.info("Getting all the information of users.");
        List<User> userList = this.userRepository.findAll();
        userList.forEach((user) -> {
            user.getOrderList().forEach((order) -> {
                order.getProductList().clear();
                order.setUserId(null);
            });
            user.getComplaintList().forEach((complaint) -> {
                complaint.setUser(null);
            });
        });
        return userList;
    }

    public String deleteUser(Long userId) {
        logger.info("To delete first checking if user with id: " + userId + " exists or not.");
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        this.userRepository.delete(user);
        logger.info("User with user id: " + userId + " deleted successfully.");
        return "User Deleted";
    }

    public ResponseEntity<User> loginUser(User user1) {
        String email = user1.getEmail();
        String password = user1.getPassword();
        logger.info("User is trying to login with email: " + email);
        Optional<User> userOptional = userRepository.findUserByEmail(email);
        if (userOptional.isEmpty()) {
            logger.info(email + " is an invalid email.");
            return ResponseEntity.status(HttpStatusCode.valueOf(400)).body(null);
        } else {
            logger.info("Email exists.");
            User user = userOptional.get();
            if (user.getPassword().equals(password)) {
                user.getComplaintList().forEach(complaint -> {
                    complaint.setUser(null);
                });
                user.getOrderList().forEach(order -> {
                    order.getProductList().clear();
                    order.setUserId(null);
                });
                return ResponseEntity.ok().body(user);
            } else {
                logger.info("Incorrect password provided by the user.");
                return ResponseEntity.status(HttpStatusCode.valueOf(401)).body(null);
            }
        }
    }
}