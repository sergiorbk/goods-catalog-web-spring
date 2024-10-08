package com.sergosoft.goodscatalog.repository;

import com.sergosoft.goodscatalog.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
    boolean existsByName(String name);
}
