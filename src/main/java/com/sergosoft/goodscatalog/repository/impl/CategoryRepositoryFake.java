package com.sergosoft.goodscatalog.repository.impl;

import com.sergosoft.goodscatalog.model.Category;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
@Qualifier("fake")
@Deprecated(forRemoval = true)
public class CategoryRepositoryFake {

    private final Map<Integer, Category> categories = new HashMap<>();
    private Integer id = 0;

    public List<Category> findAllCategories() {
        return new ArrayList<>(categories.values());
    }

    public Optional<Category> findCategoryById(Integer id) {
        return Optional.ofNullable(categories.get(id));
    }

    public Category saveCategory(Category category) {
        category.setId(++id);
        return categories.put(category.getId(), category);
    }

    public Category updateCategory(Integer id, Category category) {
        return categories.put(id, category);
    }

    public boolean deleteCategory(Integer id) {
        return categories.remove(id) != null;
    }
}
