package com.inventory.productmanagementsystem.Controller;

import com.inventory.productmanagementsystem.Model.User;
import com.inventory.productmanagementsystem.ProductManagementSystemApplication;
import com.inventory.productmanagementsystem.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users/")
@ResponseBody
public class UserController {
    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("create")
    public Object createUser(@RequestBody User user) {
        logger.info("Trying to create a " + user.getRole() + " with email id: " + user.getEmail());
        ResponseEntity<User> responseEntity = this.userService.createUser(user);
        if (responseEntity.getStatusCode() == HttpStatusCode.valueOf(400)) {
            logger.warn("Email: " + user.getEmail() + " already exists. giving user a warning.");
            return "Email already Exist.";
        } else {
            logger.info("User successfully created with email id: " + user.getEmail());
            return responseEntity;
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable("id") Long userId) {
        logger.info("Updating the user with user id: " + userId);
        User updatedUser = this.userService.updateUser(user, userId);
        return new ResponseEntity<>(updatedUser, HttpStatus.CREATED);
    }

    @GetMapping("getUser/{id}")
    public User getUserById(@PathVariable("id") Long userId) {
        logger.info("Getting the user with user id: " + userId);
        return userService.getUserById(userId);
    }

    @GetMapping("getAllUsers")
    public List<User> getAllUsers() {
        logger.info("Getting the list of all users.");
        return userService.getAllUsers();
    }

    @DeleteMapping("deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Long userId) {
        logger.warn("Deleting the user with user id: " + userId);
        return userService.deleteUser(userId);
    }

    @PostMapping("login")
    public Object userLogin (@RequestBody User user) {
        logger.info("User is trying to login.");
        ResponseEntity<User> userResponseEntity = userService.loginUser(user);
        if (userResponseEntity.getStatusCode() == HttpStatusCode.valueOf(401)) {
            logger.warn("Password provided by the user is incorrect.");
            return "Please enter correct password.";
        } else if (userResponseEntity.getStatusCode() == HttpStatusCode.valueOf(400)) {
            logger.warn("No user exist with given email id: " + user.getEmail());
            return "Email doesn't exist.";
        } else {
            logger.info("User logged in successfully.");
            return userResponseEntity;
        }
    }
}