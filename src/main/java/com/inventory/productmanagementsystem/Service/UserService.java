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
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        User savedUser = this.userRepository.save(user);
        return savedUser;
    }

    public User updateUser(User user, Long userId) {
        User user1  = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","Id", userId));
        user1.setName(user.getName());
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        user1.setMobileNumber(user.getMobileNumber());
        user1.setAddress(user.getAddress());

        User updatedUser = this.userRepository.save(user1);
        return updatedUser;
    }


    public User getUserById(Long userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","Id",userId));
        return user;
    }

    public List<User> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        return users;
    }

    public void deleteUser(Long userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","Id",userId));
        this.userRepository.delete(user);
    }


}
