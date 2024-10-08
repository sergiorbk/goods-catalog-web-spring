package com.sergosoft.goodscatalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.sergosoft.goodscatalog.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>, JpaSpecificationExecutor<Category> {

    @Query("SELECT COUNT(c) > 0 FROM Category c WHERE c.name = :name")
    boolean existsByName(String name);
}
