package com.sergosoft.goodscatalog.repository.impl;

import com.sergosoft.goodscatalog.model.Category;
import com.sergosoft.goodscatalog.repository.CategoryRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Deprecated(forRemoval = true)
public class CategoryRepositoryFake implements CategoryRepository {

    private Map<Long, Category> categories = new HashMap<>();
    private Long id = 0L;

    @Override
    public List<Category> findAllCategories() {
        return new ArrayList<>(categories.values());
    }

    @Override
    public Optional<Category> findCategoryById(Long id) {
        return Optional.of(categories.get(id));
    }

    @Override
    public Category saveCategory(Category category) {
        return categories.put(++id, category);
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        return categories.put(id, category);
    }

    @Override
    public boolean deleteCategory(Long id) {
        return categories.remove(id) != null;
    }
}
