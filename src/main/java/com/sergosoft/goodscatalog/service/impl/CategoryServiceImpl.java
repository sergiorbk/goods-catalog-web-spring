package com.sergosoft.goodscatalog.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.sergosoft.goodscatalog.dto.category.CategoryCreationRequest;
import com.sergosoft.goodscatalog.exception.EntityNotFoundException;
import com.sergosoft.goodscatalog.mapper.CategoryMapper;
import com.sergosoft.goodscatalog.model.Category;
import com.sergosoft.goodscatalog.repository.CategoryRepository;
import com.sergosoft.goodscatalog.service.CategoryService;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Integer id) {
        return findCategoryOrThrow(id);
    }

    @Override
    public Category addCategory(CategoryCreationRequest categoryCreationRequest) {
        String categoryName = categoryCreationRequest.getName();
        if (categoryRepository.existsByName(categoryName) || categoryRepository.existsByName(categoryName.toLowerCase())) {
            throw new EntityNotFoundException("Field 'name' for a new Category must be unique.");
        }
        return categoryRepository.save(categoryMapper.toEntity(categoryCreationRequest));
    }

    @Override
    public void updateCategory(Integer id, Category category) {
        ensureCategoryExists(id);
        categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Integer id) {
        ensureCategoryExists(id);
        categoryRepository.deleteById(id);
    }

    private void ensureCategoryExists(Integer id) {
        if (!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException("No category with ID: " + id + " found.");
        }
    }

    private Category findCategoryOrThrow(Integer id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No category with ID: " + id + " found."));
    }
}
