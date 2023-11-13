package com.suraj.blog.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	
	private int id;
	@NotEmpty
	@Size(min = 4,message = "Username must be minimum of 4 character")
	private String name;
	@Email(message = "Email address is not valid.")
	private String email;
	@NotEmpty
	@Size(min = 3,max = 8,message = "Password must be be minimum 3 or maximum 8")
	private String password;
	@NotEmpty
	@Size(min = 10,max = 50,message = "about must be be minimum 50 or maximum 100 character")
	private String about;
}
