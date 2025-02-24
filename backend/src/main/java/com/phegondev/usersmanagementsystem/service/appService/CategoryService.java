package com.phegondev.usersmanagementsystem.service.appService;


import com.phegondev.usersmanagementsystem.dto.CategoryDto;
import com.phegondev.usersmanagementsystem.entity.Category;
import com.phegondev.usersmanagementsystem.exceptions.ResourceNotFoundException;
import com.phegondev.usersmanagementsystem.repository.CategoryRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
	@Autowired
	 private CategoryRepo categoryRepo;



      public CategoryDto createCategory(CategoryDto categoryDto) {
	            Category category = new Category();
			BeanUtils.copyProperties(categoryDto, category);
			Category addedCategory = this.categoryRepo.save(category);
			CategoryDto addedCategoryDto = new CategoryDto();
			BeanUtils.copyProperties(addedCategory, addedCategoryDto);
			return addedCategoryDto;
	  }

	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		// Find the category by id
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category ", "Category Id", categoryId));
		BeanUtils.copyProperties(categoryDto, cat);
		Category updatedCat = this.categoryRepo.save(cat);
		CategoryDto updatedCategoryDto = new CategoryDto();
		BeanUtils.copyProperties(updatedCat, updatedCategoryDto);

		return updatedCategoryDto;
	}






	public void deleteCategory(Integer categoryId) {

		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category ", "category id", categoryId));
		this.categoryRepo.delete(cat);
	}




	public CategoryDto getCategory(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));

		// Manually map properties from Category to CategoryDto using BeanUtils
		CategoryDto categoryDto = new CategoryDto();
		BeanUtils.copyProperties(cat, categoryDto); // Copy properties from entity to DTO

		return categoryDto;
	}



	public List<CategoryDto> getCategories() {
		List<Category> categories = this.categoryRepo.findAll();

		// Manually map each Category to CategoryDto using BeanUtils
		List<CategoryDto> catDtos = categories.stream().map(cat -> {
			CategoryDto categoryDto = new CategoryDto();
			BeanUtils.copyProperties(cat, categoryDto); // Copy properties from entity to DTO
			return categoryDto;
		}).collect(Collectors.toList());

		return catDtos;
	}


}