package com.suraj.blog.services;

import java.util.List;

import com.suraj.blog.dto.PostDto;
import com.suraj.blog.payload.PostResponse;

public interface PostService {

	/* Method for create Post */
	public PostDto createpost(PostDto postDto, Long userId, Long categoryId);

	/* Method for update Post */
	public PostDto updatePost(PostDto postDto, Long postId);

	/* Method for find by id Post */
	public PostDto findPostById(Long postId);

	/* Method for delete Post */
	public void deletePost(Long postId);

	/* Method for find all Post */
	public PostResponse getAllPost(Integer pageNumber, Integer pageValue,String sortBy,String sortdir);

	/* Method for find all Post by category */
	public List<PostDto> getPostByCategory(Long categoryId);

	/* Method for find all Post by user */
	public List<PostDto> getPostByUser(Long userId);

	/* Method for search Post by keyword */
	public List<PostDto> serachPost(String keyword);

}
