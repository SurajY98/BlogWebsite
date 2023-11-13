package com.suraj.blog.servicesImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suraj.blog.dto.CategoryDto;
import com.suraj.blog.entity.Category;
import com.suraj.blog.exception.ResourceNotFoundException;
import com.suraj.blog.repository.CategoryRepository;
import com.suraj.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category map = this.modelMapper.map(categoryDto, Category.class);
		Category save = this.categoryRepository.save(map);
		return this.modelMapper.map(save, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {
		Category data = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
		data.setCategotyTitle(categoryDto.getCategotyTitle());
		data.setCategoryDescription(categoryDto.getCategoryDescription());
		Category save = this.categoryRepository.save(data);
		return this.modelMapper.map(save, CategoryDto.class);
	}

	@Override
	public CategoryDto findCategoryById(Long categotyId) {
		Category category = this.categoryRepository.findById(categotyId).orElseThrow(() -> new ResourceNotFoundException("Category","Category id",categotyId));
		return this.modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Long categotyId) {
		Category category = this.categoryRepository.findById(categotyId).orElseThrow(() -> new ResourceNotFoundException("Category","category id",categotyId));
		this.categoryRepository.delete(category);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> allcategories = this.categoryRepository.findAll();
		return allcategories.stream().map((cat) -> this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
	}

}
