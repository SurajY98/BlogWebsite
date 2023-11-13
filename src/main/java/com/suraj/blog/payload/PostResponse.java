package com.suraj.blog.payload;

import java.util.List;

import com.suraj.blog.dto.PostDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {
	
	private List<PostDto> content;
	private int pageNumber;
	private int pageSize;
	private long totalPosts;
	private int totalPage;
	private boolean lastPage;

}
