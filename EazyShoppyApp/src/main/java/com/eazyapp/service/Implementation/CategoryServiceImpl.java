package com.eazyapp.service.Implementation;

import com.eazyapp.dto.CategoryDTO;
import com.eazyapp.exception.EazyShoppyException;
import com.eazyapp.model.Category;
import com.eazyapp.repository.CategoryRepository;
import com.eazyapp.requestwrapper.CategoryRequestWrapper;
import com.eazyapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void createCategory(CategoryRequestWrapper categoryRequestWrapper) throws EazyShoppyException {
        Category category = new Category();
        category.setName(categoryRequestWrapper.getName());

        if (categoryRequestWrapper.getParentId() != null) {
            Category parent = categoryRepository.findById(categoryRequestWrapper.getParentId())
                    .orElseThrow(() -> new EazyShoppyException("Parent category not found", 404));
            category.setParent(parent);
        }
        categoryRepository.save(category);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAllWithSubcategories();

        return categories.stream()
                .map(this::convertToDTO) // Recursive mapping
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryById(Long id) throws EazyShoppyException {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EazyShoppyException("Category not found", 404));

        return convertToDTO(category);
    }

    private CategoryDTO convertToDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setParentId(category.getParent() != null ? category.getParent().getId() : null);

        if (category.getSubcategories() != null && !category.getSubcategories().isEmpty()) {
            List<CategoryDTO> subcategoryDTOs = category.getSubcategories().stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            categoryDTO.setSubcategories(subcategoryDTOs);
        }

        return categoryDTO;
    }
}
