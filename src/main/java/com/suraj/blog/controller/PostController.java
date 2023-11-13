package com.suraj.blog.controller;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.suraj.blog.dto.PostDto;
import com.suraj.blog.payload.PostResponse;
import com.suraj.blog.services.FileUploadService;
import com.suraj.blog.services.PostService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostService postService;
	@Autowired
	private FileUploadService fileUploadService;

	@Value("${project.image}")
	private String path;

	/* Method for storing the data in the database. */

	@PostMapping("/user/{userid}/category/{categoryid}/posts")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto, @PathVariable("userid") long userid,
			@PathVariable("categoryid") long categoryid) {
		PostDto dto = this.postService.createpost(postDto, userid, categoryid);
		return new ResponseEntity<PostDto>(dto, HttpStatus.CREATED);
	}

	/* Method for update the data in the database. */
	@PutMapping("updatetpost/{id}")
	public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable("id") long postid) {
		PostDto updatePost = this.postService.updatePost(postDto, postid);
		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
	}

	/* Method for find all Post by category */
	@GetMapping("category/{id}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable("id") long categoryid) {
		List<PostDto> posts = this.postService.getPostByCategory(categoryid);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}

	/* Method for find all Post by user */
	@GetMapping("user/{id}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable("id") long userid) {
		List<PostDto> posts = this.postService.getPostByUser(userid);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}

	/* Method for get all Post */
	@GetMapping("allposts")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "pId", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortdir) {
		PostResponse allPost = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortdir);
		return new ResponseEntity<PostResponse>(allPost, HttpStatus.OK);
	}

	/* Method for get Post by id */
	@GetMapping("getposts/{id}")
	public ResponseEntity<PostDto> getPostByid(@PathVariable("id") long postid) {
		PostDto postById = this.postService.findPostById(postid);
		return new ResponseEntity<PostDto>(postById, HttpStatus.OK);
	}

	/* Method for delete Post by id */
	@DeleteMapping("deleteposts/{id}")
	public String deletePost(@PathVariable("id") long postid) {
		this.postService.deletePost(postid);
		return "Post deleted successfully !!";
	}

	/* Method for find Post by Keyword */
	@GetMapping("posts/search/{keyword}")
	public ResponseEntity<List<PostDto>> getPostByKey(@PathVariable("keyword") String keyword) {
		List<PostDto> post = this.postService.serachPost(keyword);
		return new ResponseEntity<>(post, HttpStatus.OK);
	}

	/* Method for uploading file or image */
	@PostMapping("/post/image/upload/{postid}")
	public ResponseEntity<PostDto> uploadImage(@RequestParam("image") MultipartFile image, @PathVariable Long postid)
			throws IOException {
		/* Getting the post by postid if post not present will throw resource not found exception*/
		PostDto postDto = this.postService.findPostById(postid);
		
		/* Getting the fileUploadService name */
		String fileName = this.fileUploadService.uploadFile(path, image);
			postDto.setPImageName(fileName);
		PostDto updatePost = this.postService.updatePost(postDto, postid);
		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
	}
	
	
	/* Method for serv image */
	@GetMapping("/post/image/{imageName}")
	public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {
		InputStream resource = this.fileUploadService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}
}
