package com.inventory.productmanagementsystem.Controller;

import com.inventory.productmanagementsystem.Model.User;
import com.inventory.productmanagementsystem.Service.UserService;
import org.hibernate.internal.build.AllowPrintStacktrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@ResponseBody

 public class UserController {
    private UserService userService;
@Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createUser = this.userService.createUser(user);
        return new ResponseEntity<>(createUser, HttpStatus.CREATED);
    }

}
