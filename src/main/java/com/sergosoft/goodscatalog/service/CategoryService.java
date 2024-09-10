package com.sergosoft.goodscatalog.service;

import com.sergosoft.goodscatalog.dto.CategoryCreationRequest;
import com.sergosoft.goodscatalog.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getAllCategories();
    Category getCategoryById(Long id);
    Category addCategory(CategoryCreationRequest categoryCreationRequest);
    Category updateCategory(Long id, Category category);
    boolean deleteCategory(Long id);
}
