package com.suraj.blog.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pId;
	@Column(name = "post_title",length = 100,nullable = false)
	private String pTitle;
	@Column(name = "post_content",length = 1000)
	private String pContent;
	private String pImageName;
	private Date addedDate;
	
	/* Creating the object of the Category and UserEntity class */
	/* This column is created using join of the category_id and userentity_id*/
	@ManyToOne
	private Category category;
	@ManyToOne
	private UserEntity entity;
	
	
	/* Mentioned below mapping id for comment */
	@OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
	private Set<Comment> commets = new HashSet<>();
	
	
	

}
