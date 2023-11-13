package com.suraj.blog.servicesImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.asm.Advice.This;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.suraj.blog.dto.PostDto;
import com.suraj.blog.entity.Category;
import com.suraj.blog.entity.Post;
import com.suraj.blog.entity.UserEntity;
import com.suraj.blog.exception.ResourceNotFoundException;
import com.suraj.blog.payload.PostResponse;
import com.suraj.blog.repository.CategoryRepository;
import com.suraj.blog.repository.PostRepository;
import com.suraj.blog.repository.UserRepository;
import com.suraj.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private CategoryRepository categoryRepo;

	@Autowired
	private UserRepository userRepo;

	@Override
	public PostDto createpost(PostDto postDto, Long userId, Long categoryId) {
		/* We will 1st check the userid present or not in the database */
		UserEntity user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("UserEntity", "user Id", userId));

		/* We will 1st check the categoryid present or not in the database */
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category Id", categoryId));

		/* Converting the postDto type Data inData to the post */
		Post post = this.mapper.map(postDto, Post.class);
		post.setPImageName("suraj.png");
		post.setAddedDate(new Date());
		post.setEntity(user);
		post.setCategory(category);

		/* Saving the data into database */
		Post save = this.postRepository.save(post);
		/* Converting the post into postdto */
		return this.mapper.map(save, PostDto.class);
	}

	/* Method for update post */
	@Override
	public PostDto updatePost(PostDto postDto, Long postId) {
		Post post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post id", postId));
		post.setPTitle(postDto.getPTitle());
		post.setPContent(postDto.getPContent());
		post.setPImageName(postDto.getPImageName());
		
		Post save = postRepository.save(post);
		PostDto postDto2 = this.mapper.map(save, PostDto.class);
		return postDto2;
	}

	/* Method for get Post by id */
	@Override
	public PostDto findPostById(Long postId) {
		Post post = this.postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
		PostDto postDto = this.mapper.map(post, PostDto.class);
		return postDto;
	}

	/* Method for delete Post by id */
	@Override
	public void deletePost(Long postId) {
		Post post = this.postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
		this.postRepository.delete(post);
	}

	/* Method for get all Post */
	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageValue,String sortBy,String sortdir) {
		Sort sort = (sortdir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		/* Creating pageable object */
//		Pageable pageable = PageRequest.of(pageNumber, pageValue,Sort.by(sortBy).descending());
		Pageable pageable = PageRequest.of(pageNumber, pageValue,sort);
		Page<Post> pagePosts = this.postRepository.findAll(pageable);
		List<Post> allPosts = pagePosts.getContent();
		List<PostDto> listOfPostDto = allPosts.stream().map((post) -> this.mapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		PostResponse response = new PostResponse();
		response.setContent(listOfPostDto);
		response.setPageNumber(pagePosts.getNumber());
		response.setTotalPage(pagePosts.getTotalPages());
		response.setTotalPosts(pagePosts.getTotalElements());
		response.setPageSize(pagePosts.getSize());
		response.setLastPage(pagePosts.isLast());
		
		return response;
	}

	/* Method for find all Post by category */
	@Override
	public List<PostDto> getPostByCategory(Long categoryId) {
		/* We are getting the data from category using categoryId */
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));
		/* we are finding the post using category find by categoryid in above code */
		List<Post> postlist = this.postRepository.findByCategory(category);
		/* We are converting the list of posts in the postDto */
		return postlist.stream().map((post) -> this.mapper.map(post, PostDto.class))
				.collect(Collectors.toList());
	}

	/* Method for find all Post by user id */
	@Override
	public List<PostDto> getPostByUser(Long userId) {
		UserEntity entity = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("UserEntity", "User id", userId));
		List<Post> posts = this.postRepository.findByEntity(entity);
		return posts.stream().map((user) -> this.mapper.map(user, PostDto.class))
				.collect(Collectors.toList());
	}

	/* Method for get Post by keyword */
	@Override
	public List<PostDto> serachPost(String keyword) {
		List<Post> posts = this.postRepository.findBypTitleContaining(keyword);
		return posts.stream().map((post) -> this.mapper.map(post, PostDto.class)).collect(Collectors.toList());
	}

}
