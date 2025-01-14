package com.e_commerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_commerce.category_repository.CategoryRepository;
import com.e_commerce.model.Category;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryrepository;
	
	public List<Category> getAllCategory()
	{
		return categoryrepository.findAll();
	}
	public Optional<Category> getCategoryById(Long id)
	{
		return categoryrepository.findById(id);
	}

	public Category saveCategory(Category category)
	{
		return categoryrepository.save(category);
	}
	public void deleteCategoryById(Long id)
	{
		categoryrepository.deleteById(id);
	}
	
}
