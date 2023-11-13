package com.suraj.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suraj.blog.dto.CategoryDto;
import com.suraj.blog.payload.ApiResponse;
import com.suraj.blog.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	/* Method for create category */
	@PostMapping("/save")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
		CategoryDto createCategory = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);
	}

	/* Method for update category */
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,
			@PathVariable("catId") long categoryId) {
		CategoryDto updateCategory = this.categoryService.updateCategory(categoryDto, categoryId);
		return new ResponseEntity<CategoryDto>(updateCategory, HttpStatus.OK);
	}

	/* Method for find by id category */
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> findByIdCategory(@Valid @PathVariable("catId") long catId) {
		CategoryDto finCategory = this.categoryService.findCategoryById(catId);
		return new ResponseEntity<CategoryDto>(finCategory, HttpStatus.OK);
	}

	/* Method for delete category */
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteCategory(@Valid @PathVariable("id") long categoryId) {
		this.categoryService.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted successfully!!", true), HttpStatus.OK);
	}

	/* Method for find all category */
	@GetMapping("/getall")
	public ResponseEntity<List<CategoryDto>> getAllCategory() {
		List<CategoryDto> allCategory = this.categoryService.getAllCategory();
		return ResponseEntity.ok(allCategory);
	}

}
