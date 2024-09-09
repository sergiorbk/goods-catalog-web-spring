package com.sergosoft.goodscatalog.repository;

import com.sergosoft.goodscatalog.model.Category;
import com.sergosoft.goodscatalog.model.Product;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    List<Category> findAllCategories();
    Optional<Category> findCategoryById(Long id);
    Category saveCategory(Category category);
    Category updateCategory(Long id, Category category);
    boolean deleteCategory(Long id);
}
