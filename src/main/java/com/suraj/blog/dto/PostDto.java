package com.suraj.blog.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.suraj.blog.entity.Comment;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PostDto {

	private Long pId;
	@NotEmpty
	@Size(min = 4,max = 50, message = "Post title must be minimum of 4 character")
	private String pTitle;
	@NotEmpty
	@Size(min = 4,max = 100, message = "Post Content must be minimum of 4 character")
	private String pContent;
	private String pImageName;
	private Date addedDate;

	/* Creating the object of the Category and UserEntity class */
	/* This column is created using join of the category_id and userentity_id */
	private CategoryDto category;
	private UserDto entity;
	
	/* Mentioned below mapping id for comment */
	private Set<Comment> commets = new HashSet<>();

}
