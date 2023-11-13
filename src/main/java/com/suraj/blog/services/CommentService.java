package com.suraj.blog.services;

import com.suraj.blog.dto.CommentDto;

/**
 * @author suraj.yadav
 * @created on Nov 7, 2023
 */
public interface CommentService {
	
	CommentDto createComment(CommentDto commentDto,Long postId);
	void deleteComment(long commentId);

}
