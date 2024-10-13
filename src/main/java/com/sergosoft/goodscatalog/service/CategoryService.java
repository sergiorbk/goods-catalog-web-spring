package com.sergosoft.goodscatalog.service;

import java.util.List;
import com.sergosoft.goodscatalog.dto.category.CategoryCreationRequest;
import com.sergosoft.goodscatalog.model.Category;
import org.springframework.data.domain.Page;

public interface CategoryService {
    List<Category> getAllCategories();
    Category getCategoryById(Integer id);
    Category addCategory(CategoryCreationRequest categoryCreationRequest);
    void updateCategory(Integer id, Category category);
    void deleteCategory(Integer id);
    Page<Category> getPage(int page, int size);
    Page<Category> filteredCategories(String name, String description, int page, int size);
}
