package com.inventory.productmanagementsystem.Service;

import com.inventory.productmanagementsystem.Model.User;
import com.inventory.productmanagementsystem.PayLoad.UserDto;

import java.util.List;

public interface UserService {

User createUser(User user);
User updateUser(User user,Long userId);
User getUserById(Long userId);
List<User> getAllUsers();

void deleteUser(Long userId);
}
