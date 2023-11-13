package com.suraj.blog.utility;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.suraj.blog.dto.UserDto;
import com.suraj.blog.entity.UserEntity;

@Controller
public class CommonClass {

	@Autowired
	private ModelMapper modelMapper;

	public UserEntity dtoToUserEntity(UserDto userDto) {
		UserEntity entity = this.modelMapper.map(userDto, UserEntity.class);
		return entity;
	}

	public UserDto userEntityToDto(UserEntity userEntity) {
		UserDto dto = this.modelMapper.map(userEntity, UserDto.class);
		return dto;
	}

}
