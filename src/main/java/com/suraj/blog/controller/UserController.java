package com.suraj.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suraj.blog.dto.UserDto;
import com.suraj.blog.payload.ApiResponse;
import com.suraj.blog.services.UserServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserServices userServices;

	/* Method for storing the data in the database. */

	@PostMapping("/save")
	public ResponseEntity<UserDto> saveUser(@Valid @RequestBody UserDto userDto) {
		UserDto dto = this.userServices.createUser(userDto);
		return new ResponseEntity<>(dto, HttpStatus.CREATED);
	}

	/* Method for updating the data from the database. */
	@PutMapping("updateuser/{id}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto dto, @PathVariable("id") long id) {
		UserDto userDto = this.userServices.updateUser(dto, id);
		return ResponseEntity.ok(userDto);
	}

	/* Method for Get the data from the database. */

	@GetMapping("getuser/{id}")
	public ResponseEntity<UserDto> getUset(@Valid @PathVariable("id") long userId) {
		UserDto dto2 = this.userServices.getUserById(userId);
		return ResponseEntity.ok(dto2);
	}

	/* Method for Get all the data from the database. */
	@GetMapping("/getalluser")
	public List<UserDto> getAllUser() {
		List<UserDto> dto = this.userServices.getAllUsers();
		return dto;
	}

	/* Method for delete data from the database. */

	@DeleteMapping("deleteuser/{id}")
	public ResponseEntity<ApiResponse> deleteUser(@Valid @PathVariable("id") Long userId) {
		userServices.deleteUser(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully",true),HttpStatus.OK);
	}

}
