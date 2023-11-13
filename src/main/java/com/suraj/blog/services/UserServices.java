package com.suraj.blog.services;

import java.util.List;

import com.suraj.blog.dto.UserDto;

public interface UserServices {
	
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user,Long userId);
	UserDto getUserById(Long userId);
	List<UserDto> getAllUsers();
	void deleteUser(Long userId);
 
}
