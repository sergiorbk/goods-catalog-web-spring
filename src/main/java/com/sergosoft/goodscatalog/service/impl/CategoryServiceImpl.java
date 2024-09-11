package com.sergosoft.goodscatalog.service.impl;

import com.sergosoft.goodscatalog.dto.CategoryCreationRequest;
import com.sergosoft.goodscatalog.exception.EntityNotFoundException;
import com.sergosoft.goodscatalog.mapper.CategoryMapper;
import com.sergosoft.goodscatalog.model.Category;
import com.sergosoft.goodscatalog.repository.CategoryRepository;
import com.sergosoft.goodscatalog.service.CategoryService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Integer id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("No category with ID: " + id + " not found."));
    }

    @Override
    public Category addCategory(CategoryCreationRequest categoryCreationRequest){
        return categoryRepository.save(categoryMapper.toEntity(categoryCreationRequest));
    }

    @Override
    public Category updateCategory(Integer id, Category category){
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Integer id) {
        categoryRepository.deleteById(id);
    }

}
