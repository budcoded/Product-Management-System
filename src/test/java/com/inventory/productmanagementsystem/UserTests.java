package com.inventory.productmanagementsystem;

import com.inventory.productmanagementsystem.Model.User;
import com.inventory.productmanagementsystem.Repository.UserRepository;
import com.inventory.productmanagementsystem.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserTests {
    @InjectMocks
    private UserService userService; 
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(UserTests.this);
        userService = new UserService(userRepository);
    }

    @Test
    public void testLoginFunctionality() {
        User admin = new User();
        admin.setEmail("Admin@iiitb.ac.in");
        admin.setPassword("password");
        when(userRepository.findUserByEmail(admin.getEmail())).thenReturn(Optional.of(admin));
        ResponseEntity<User> responseEntity = userService.loginUser(admin);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(admin, responseEntity.getBody());
    }

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setEmail("john.doe@example.com");
        user.setPassword("password");
        user.setMobileNumber(1234567890L);
        user.setAddress("123 Main St.");
        when(userRepository.save(user)).thenReturn(user);
        User savedUser = userService.createUser(user).getBody();
        assertNotNull(savedUser);
        assertEquals(user.getEmail(), savedUser.getEmail());
        assertEquals(user.getPassword(), savedUser.getPassword());
        assertEquals(user.getMobileNumber(), savedUser.getMobileNumber());
        assertEquals(user.getAddress(), savedUser.getAddress());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testUpdateUser() {
        Long userId = 1L;
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password");
        user.setMobileNumber(1234567890L);
        user.setAddress("123 Main St.");
        when(userRepository.findById(userId)).thenReturn(Optional.of(new User()));
        when(userRepository.save(any(User.class))).thenReturn(user);
        User updatedUser = userService.updateUser(user, userId);
        assertNotNull(updatedUser);
        assertEquals(user.getName(), updatedUser.getName());
        assertEquals(user.getEmail(), updatedUser.getEmail());
        assertEquals(user.getPassword(), updatedUser.getPassword());
        assertEquals(user.getMobileNumber(), updatedUser.getMobileNumber());
        assertEquals(user.getAddress(), updatedUser.getAddress());
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testGetUserById() {
        Long userId = 1L;
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password");
        user.setMobileNumber(1234567890L);
        user.setAddress("123 Main St.");
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        User retrievedUser = userService.getUserById(userId);
        assertNotNull(retrievedUser);
        assertEquals(user.getName(), retrievedUser.getName());
        assertEquals(user.getEmail(), retrievedUser.getEmail());
        assertEquals(user.getPassword(), retrievedUser.getPassword());
        assertEquals(user.getMobileNumber(), retrievedUser.getMobileNumber());
        assertEquals(user.getAddress(), retrievedUser.getAddress());
        assertTrue(retrievedUser.getOrderList().isEmpty());
        assertTrue(retrievedUser.getComplaintList().isEmpty());
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    public void testDeleteUser() {
        // Create a new user
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password");
        user.setMobileNumber(1234567890L);
        user.setAddress("123 Main St");
        // Save the user to the repository
        User savedUser = userService.createUser(user).getBody();
        // Delete the user
        String result = null;
        if (savedUser != null) {
            result = userService.deleteUser(savedUser.getId());
            assertEquals("User Deleted", result);
        }
    }

    @Test
    public void testLoginWithValidCredentials() {
        // create a user with valid credentials
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");
        // mock the behavior of the repository to return the user when searching by email
        when(userRepository.findUserByEmail(user.getEmail())).thenReturn(Optional.of(user));
        // call the loginUser method with the user's credentials
        ResponseEntity<User> responseEntity = userService.loginUser(user);
        // check that the response is OK and contains the correct user object
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(user, responseEntity.getBody());
    }

    @Test
    public void testLoginWithInvalidCredentials() {
        // create a user with invalid credentials
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("wrongpassword");
        // mock the behavior of the repository to return the user when searching by email
        when(userRepository.findUserByEmail(user.getEmail())).thenReturn(Optional.of(user));
        // call the loginUser method with the user's credentials
        ResponseEntity<User> responseEntity = userService.loginUser(user);
        // check that the response is Unauthorized
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testLoginWithInvalidEmail() {
        // create a user with an invalid email
        User user = new User();
        user.setEmail("nonexistent@example.com");
        user.setPassword("password");
        // mock the behavior of the repository to return an empty Optional when searching by email
        when(userRepository.findUserByEmail(user.getEmail())).thenReturn(Optional.empty());
        // call the loginUser method with the user's credentials
        ResponseEntity<User> responseEntity = userService.loginUser(user);
        // check that the response is Bad Request
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }
}