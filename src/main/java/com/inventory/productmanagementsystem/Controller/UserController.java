package com.inventory.productmanagementsystem.Controller;

import com.inventory.productmanagementsystem.Model.User;
import com.inventory.productmanagementsystem.Service.UserService;
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

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createUser = this.userService.createUser(user);
        return new ResponseEntity<>(createUser, HttpStatus.CREATED);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable("id") Long userId) {
        User updatedUser = this.userService.updateUser(user, userId);
        return new ResponseEntity<>(updatedUser, HttpStatus.CREATED);
    }

    @GetMapping("getUser/{id}")
    public User getUserById(@PathVariable("id") Long userId) {
        return userService.getUserById(userId);
    }

    @GetMapping("getAllUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Long userId) {
        return userService.deleteUser(userId);
    }

    @PostMapping("login")
    public Object userLogin (@RequestBody User user) {
        ResponseEntity<User> userResponseEntity = userService.loginUser(user);
        if (userResponseEntity.getStatusCode() == HttpStatusCode.valueOf(401)) {
            return "Please enter correct password.";
        } else if (userResponseEntity.getStatusCode() == HttpStatusCode.valueOf(400)) {
            return "Email doesn't exist.";
        } else {
            return userResponseEntity;
        }
    }
}
