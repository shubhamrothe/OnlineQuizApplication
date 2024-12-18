package com.quizapp.services;

import java.util.List;

import com.quizapp.payloads.UserDto;

public interface UserServiceI {
	
	 	UserDto registerUser(UserDto userDto);
	    UserDto loginUser(String email, String password);
	    UserDto updateUser(Long userId, UserDto updatedUserDto);
	    UserDto getUserById(Long userId);
	    List<UserDto> getAllUsers();
	    void deleteUser(Long userId);
}
