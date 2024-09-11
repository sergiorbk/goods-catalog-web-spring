package com.sergosoft.goodscatalog.service;

import com.sergosoft.goodscatalog.dto.CategoryCreationRequest;
import com.sergosoft.goodscatalog.model.Category;
import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    Category getCategoryById(Integer id);
    Category addCategory(CategoryCreationRequest categoryCreationRequest);
    Category updateCategory(Integer id, Category category);
    void deleteCategory(Integer id);
}
