//package com.inventory.productmanagementsystem.Service.Impl;
//
//import com.inventory.productmanagementsystem.Exceptions.ResourceNotFoundException;
//import com.inventory.productmanagementsystem.Model.User;
//import com.inventory.productmanagementsystem.PayLoad.UserDto;
//import com.inventory.productmanagementsystem.Repository.UserRepo;
//import com.inventory.productmanagementsystem.Service.UserService;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//@Service
//public class UserServiceImpl implements UserService {
//    private UserRepo userRepo;
//
//    public UserServiceImpl(UserRepo userRepo) {
//        this.userRepo = userRepo;
//    }
//
//    @Override
//    public UserDto createUser(UserDto userDto) {
//        User user = this.dtoToUser(userDto);
//        User savedUser = this.userRepo.save(user);
//        return this.userToDto(savedUser);
//    }
//
//    @Override
//    public UserDto updateUser(UserDto userDto, Long userId) {
//        User user  = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","Id", userId));
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setMobileNumber(userDto.getMobileNumber());
//        user.setAddress(userDto.getAddress());
//
//        User updatedUser = this.userRepo.save(user);
//        UserDto userDto1 = this.userToDto(updatedUser);
//        return userDto1;
//    }
//
//    @Override
//    public UserDto getUserById(Long userId) {
//        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","Id",userId));
//        return this.userToDto(user);
//    }
//
//    @Override
//    public List<UserDto> getAllUsers() {
//        List<User> users = this.userRepo.findAll();
//        List<UserDto> userToDtos  = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
//        return userToDtos;
//    }
//
//    @Override
//    public void deleteUser(Long userId) {
//       User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","Id",userId));
//       this.userRepo.delete(user);
//    }
//
//    private User dtoToUser(UserDto userDto){
//        User user = new User();
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setMobileNumber(userDto.getMobileNumber());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setAddress(userDto.getAddress());
//        user.setRole(userDto.getRole());
//        return user;
//    }
//
//    public UserDto userToDto(User user){
//        UserDto userDto = new UserDto();
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setMobileNumber(user.getMobileNumber());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        userDto.setAddress(user.getAddress());
//        userDto.setRole(user.getRole());
//        return userDto;
//    }
//}
package com.inventory.productmanagementsystem.Service;

import com.inventory.productmanagementsystem.Exceptions.ResourceNotFoundException;
import com.inventory.productmanagementsystem.Model.User;
import com.inventory.productmanagementsystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<User> createUser(User user) {
        Optional<User> userOptional = userRepository.findUserByEmail(user.getEmail());
        if (userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatusCode.valueOf(400)).body(null);
        } else {
            User savedUser = userRepository.save(user);
            return ResponseEntity.ok().body(savedUser);
        }
    }

    public User updateUser(User user, Long userId) {
        User user1 = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        user1.setName(user.getName());
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        user1.setMobileNumber(user.getMobileNumber());
        user1.setAddress(user.getAddress());
        return this.userRepository.save(user1);
    }

    public User getUserById(Long userId) {
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
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        this.userRepository.delete(user);
        return "User Deleted";
    }

    public ResponseEntity<User> loginUser(User user1) {
        String email = user1.getEmail();
        String password = user1.getPassword();
        Optional<User> userOptional = userRepository.findUserByEmail(email);
        if (userOptional.isEmpty())
            return ResponseEntity.status(HttpStatusCode.valueOf(400)).body(null);
        else {
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
                return ResponseEntity.status(HttpStatusCode.valueOf(401)).body(null);
            }
        }
    }
}