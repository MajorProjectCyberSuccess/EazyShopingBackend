package com.e_commerce.category_controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.e_commerce.model.Category;
import com.e_commerce.service.CategoryService;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/categoryapi")
public class CategoryController {
	    @Autowired
	    private CategoryService categoryService;

	    @PostMapping("/addcategory")
	    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
	        Category createdCategory = categoryService.saveCategory(category);
	        return ResponseEntity.ok(createdCategory);
	    }

	    @GetMapping
	    public ResponseEntity<List<Category>> getAllCategories() {
	        return ResponseEntity.ok(categoryService.getAllCategory());
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Category> getCategoryById(@PathVariable Long categoryId) {
	    	Optional<Category> category = categoryService.getCategoryById(categoryId);
	        return category.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	    }

	    @PutMapping("/Update/{id}")
	    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category categoryDetails) {
	    	Optional<Category> category = categoryService.getCategoryById(id);
	    	if(category.isPresent())
	    	{
	    		Category existingCategory=category.get();
	    		existingCategory.setName(categoryDetails.getName());
	    		existingCategory.setDescription(categoryDetails.getDescription());
	    		existingCategory.setProductCategoryId(categoryDetails.getProductCategoryId());
		        return ResponseEntity.ok(categoryService.saveCategory(existingCategory));

	    	}
	    	return ResponseEntity.notFound().build();
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
	        categoryService.deleteCategoryById(id);
	        return ResponseEntity.noContent().build();
	    }

}
