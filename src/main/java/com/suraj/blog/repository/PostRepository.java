package com.suraj.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suraj.blog.dto.PostDto;
import com.suraj.blog.entity.Category;
import com.suraj.blog.entity.Post;
import com.suraj.blog.entity.UserEntity;
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

	/*
	 * Creating the custum finder methods for getting data by category and
	 * UserEntity
	 */

	List<Post> findByEntity(UserEntity userEntity);

	List<Post> findByCategory(Category category);
	
	List<Post> findBypTitleContaining(String keyword);
}
