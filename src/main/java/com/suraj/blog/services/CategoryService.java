package com.suraj.blog.services;

import java.util.List;
import com.suraj.blog.dto.CategoryDto;

public interface CategoryService {

	/* Method for create category */
	public CategoryDto createCategory(CategoryDto categoryDto);

	/* Method for update category */
	public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId);

	/* Method for find by id category */
	public CategoryDto findCategoryById(Long categotyId);

	/* Method for delete category */
	public void deleteCategory(Long categotyId);

	/* Method for find all category */
	public List<CategoryDto> getAllCategory();
}
