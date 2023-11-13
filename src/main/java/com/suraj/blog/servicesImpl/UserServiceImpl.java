package com.suraj.blog.servicesImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suraj.blog.dto.UserDto;
import com.suraj.blog.entity.UserEntity;
import com.suraj.blog.exception.ResourceNotFoundException;
import com.suraj.blog.repository.UserRepository;
import com.suraj.blog.services.UserServices;
import com.suraj.blog.utility.CommonClass;

@Service
public class UserServiceImpl implements UserServices {

	@Autowired
	private UserRepository repository;

	@Autowired
	private CommonClass commonClass;

	@Override
	public UserDto createUser(UserDto userDto) {
//		Converting dto to userentity
		UserEntity entity = this.commonClass.dtoToUserEntity(userDto);
		UserEntity saveUserEntity = this.repository.save(entity);
		return this.commonClass.userEntityToDto(saveUserEntity);
	}

	@Override
	public UserDto updateUser(UserDto user, Long userId) {
		/*
		 * We are fetching data from DB using the user id which is coming in the request.
		 */
		UserEntity userEntity = this.repository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("UserEntity", "id", userId));
		/*
		 * Fetched data store in the userEntity variable. Getting the data from
		 * userEntity variable and set the data coming in the request.
		 */
		userEntity.setName(user.getName());
		userEntity.setEmail(user.getEmail());
		userEntity.setPassword(user.getPassword());
		userEntity.setAbout(user.getAbout());
		/*
		 * Now all the request data save into the userEntity.
		 */
		UserEntity entity = this.repository.save(userEntity);
		/* We will Convert userEntity in to userDto */
		return this.commonClass.userEntityToDto(userEntity);
	}

	@Override
	public UserDto getUserById(Long userId) {
		UserEntity userEntity = repository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("UserEntity", "id", userId));
		return this.commonClass.userEntityToDto(userEntity);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<UserEntity> userEntity = this.repository.findAll();
		List<UserDto> dto = userEntity.stream().map(user -> this.commonClass.userEntityToDto(user)).collect(Collectors.toList());
		return dto;
	}

	@Override
	public void deleteUser(Long userId) {
		UserEntity entity = this.repository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("UserEntity","id",userId));
		this.repository.delete(entity);
	}

}
