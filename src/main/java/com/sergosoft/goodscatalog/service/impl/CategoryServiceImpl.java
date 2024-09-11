package com.sergosoft.goodscatalog.service.impl;

import com.sergosoft.goodscatalog.dto.CategoryCreationRequest;
import com.sergosoft.goodscatalog.exception.EntityNotFoundException;
import com.sergosoft.goodscatalog.mapper.CategoryMapper;
import com.sergosoft.goodscatalog.model.Category;
import com.sergosoft.goodscatalog.repository.CategoryRepository;
import com.sergosoft.goodscatalog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(@Qualifier("fake") CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAllCategories();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findCategoryById(id).orElseThrow(
                () -> new EntityNotFoundException("No category with ID: " + id + " not found."));
    }

    @Override
    public Category addCategory(CategoryCreationRequest categoryCreationRequest){
        return categoryRepository.saveCategory(categoryMapper.toEntity(categoryCreationRequest));
    }

    @Override
    public Category updateCategory(Long id, Category category){
        return categoryRepository.updateCategory(id, category);
    }

    @Override
    public boolean deleteCategory(Long id) {
        return categoryRepository.deleteCategory(id);
    }

}
