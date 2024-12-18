package com.quizapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quizapp.payloads.UserDto;
import com.quizapp.services.UserServiceI;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserServiceI userService;

    // Register User
    @Operation(summary = "Register a new User", description = "Registers a new user and returns the user details.")
    @ApiResponse(responseCode = "200", description = "User registered successfully")
    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto) {
        log.info("Request to register a new user with email: {}", userDto.getEmail());
        UserDto registeredUser = userService.registerUser(userDto);
        log.info("User registered successfully with ID: {}", registeredUser.getUserId());
        return ResponseEntity.ok(registeredUser);
    }

    // Login User
    @Operation(summary = "Login a User", description = "Authenticates a user with email and password.")
    @ApiResponse(responseCode = "200", description = "Login successful")
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam String email, @RequestParam String password) {
        log.info("Request to login user with email: {}", email);
        UserDto userDto = userService.loginUser(email, password);
        log.info("User login successful for email: {}", email);
        return ResponseEntity.ok("Login successful for user: " + userDto.getEmail());
    }

    // Update User
    @Operation(summary = "Update User Details", description = "Updates an existing user's details by user ID.")
    @ApiResponse(responseCode = "200", description = "User updated successfully")
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        log.info("Request to update user with ID: {}", userId);
        UserDto updatedUser = userService.updateUser(userId, userDto);
        log.info("User updated successfully with ID: {}", userId);
        return ResponseEntity.ok(updatedUser);
    }

    // Get User By ID
    @Operation(summary = "Get User by ID", description = "Fetches a user's details using their ID.")
    @ApiResponse(responseCode = "200", description = "User details retrieved successfully")
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long userId) {
        log.info("Request to get user details with ID: {}", userId);
        UserDto userDto = userService.getUserById(userId);
        log.info("Retrieved user details successfully for ID: {}", userId);
        return ResponseEntity.ok(userDto);
    }

    // Get All Users
    @Operation(summary = "Get All Users", description = "Fetches a list of all users.")
    @ApiResponse(responseCode = "200", description = "Users retrieved successfully")
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        log.info("Request to get all users");
        List<UserDto> users = userService.getAllUsers();
        log.info("Retrieved all users successfully. Total users: {}", users.size());
        return ResponseEntity.ok(users);
    }

    // Delete User
    @Operation(summary = "Delete User", description = "Deletes a user using their ID.")
    @ApiResponse(responseCode = "200", description = "User deleted successfully")
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        log.info("Request to delete user with ID: {}", userId);
        userService.deleteUser(userId);
        log.info("User deleted successfully with ID: {}", userId);
        return ResponseEntity.ok("User deleted successfully");
    }
}
