package com.suraj.blog.servicesImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.suraj.blog.dto.CommentDto;
import com.suraj.blog.entity.Comment;
import com.suraj.blog.entity.Post;
import com.suraj.blog.exception.ResourceNotFoundException;
import com.suraj.blog.repository.CommentRepository;
import com.suraj.blog.repository.PostRepository;
import com.suraj.blog.services.CommentService;

/**
 * @author suraj.yadav
 * @created on Nov 7, 2023
 */
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private ModelMapper mapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Long postId) {
		/* 1String we will get the post by id if available the we will proceed or will throw exception */
		Post post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id", postId));
		Comment comment = this.mapper.map(commentDto, Comment.class);
		comment.setPost(post);
		Comment save = this.commentRepository.save(comment);
		CommentDto map = this.mapper.map(save, CommentDto.class);
		return map;
	}

	@Override
	public void deleteComment(long commentId) {

	}

}
