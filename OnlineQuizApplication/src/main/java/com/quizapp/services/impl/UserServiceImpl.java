package com.quizapp.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.quizapp.entities.Role;
import com.quizapp.entities.User;
import com.quizapp.exceptions.ResourceNotFoundException;
import com.quizapp.payloads.UserDto;
import com.quizapp.repositories.RoleRepository;
import com.quizapp.repositories.UserRepository;
import com.quizapp.services.UserServiceI;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserServiceI {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto registerUser(UserDto userDto) {
        log.info("Request to register a new user with email: {}", userDto.getEmail());

        User user = modelMapper.map(userDto, User.class);

        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        // Assign default role (e.g., NORMAL)
        Role defaultRole = roleRepository.findByName("NORMAL");
        if (defaultRole != null) {
            user.getRoles().add(defaultRole);
            log.info("Assigned default role 'NORMAL' to user: {}", userDto.getEmail());
        } else {
            log.warn("Default role 'NORMAL' not found. Skipping role assignment.");
        }

        User savedUser = userRepository.save(user);
        log.info("User registered successfully with ID: {}", savedUser.getUserId());

        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto loginUser(String email, String password) {
        log.info("Request to login user with email: {}", email);

        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                log.info("User login successful for email: {}", email);
                return modelMapper.map(user, UserDto.class);
            } else {
                log.warn("Invalid credentials for user: {}", email);
                throw new RuntimeException("Invalid credentials");
            }
        }
        log.error("User not found with email: {}", email);
        throw new RuntimeException("User not found");
    }

    @Override
    public UserDto updateUser(Long userId, UserDto updatedUserDto) {
        log.info("Request to update user with ID: {}", userId);

        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> {
                    log.error("User not found with ID: {}", userId);
                    return new ResourceNotFoundException("User", "id", userId);
                });

        existingUser.setUserName(updatedUserDto.getUserName());
        existingUser.setEmail(updatedUserDto.getEmail());
        if (updatedUserDto.getPassword() != null && !updatedUserDto.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(updatedUserDto.getPassword()));
            log.info("Password updated for user ID: {}", userId);
        }

        User updatedUser = userRepository.save(existingUser);
        log.info("User updated successfully with ID: {}", userId);

        return modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public UserDto getUserById(Long userId) {
        log.info("Request to fetch user details with ID: {}", userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    log.error("User not found with ID: {}", userId);
                    return new ResourceNotFoundException("User", "id", userId);
                });

        log.info("Fetched user details successfully for ID: {}", userId);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        log.info("Request to fetch all users");

        List<User> users = userRepository.findAll();
        log.info("Fetched {} users successfully", users.size());

        return users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long userId) {
        log.info("Request to delete user with ID: {}", userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    log.error("User not found with ID: {}", userId);
                    return new ResourceNotFoundException("User", "id", userId);
                });

        userRepository.delete(user);
        log.info("User deleted successfully with ID: {}", userId);
    }
}
