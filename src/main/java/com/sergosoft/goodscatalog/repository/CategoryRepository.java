package com.sergosoft.goodscatalog.repository;

public interface CategoryRepository {
    boolean existsByName(String name);
}
