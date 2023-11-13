package com.suraj.blog.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

	private Integer categotyId;
	@NotEmpty
	@Size(min = 4,message = "Categoty Title must be minimum of 4 character")
	private String categotyTitle;
	@NotEmpty
	@Size(min = 5,max = 50,message = "Category Description must be minimum of 5 character or maximum 50")
	private String categoryDescription;
}
