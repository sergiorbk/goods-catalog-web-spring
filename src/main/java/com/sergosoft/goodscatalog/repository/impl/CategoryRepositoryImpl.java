package com.sergosoft.goodscatalog.repository.impl;

import com.sergosoft.goodscatalog.model.Category;
import com.sergosoft.goodscatalog.repository.CategoryRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    private final JdbcTemplate jdbcTemplate;

    public CategoryRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean existsByName(String name) {
        // ToDo
        return false;
    }

    //
    // CrudRepository methods
    //
    @Override
    public <S extends Category> S save(S entity) {
        // ToDo
        return null;
    }

    @Override
    public <S extends Category> Iterable<S> saveAll(Iterable<S> entities) {
        // ToDo
        return null;
    }

    @Override
    public Optional<Category> findById(Integer integer) {
        // ToDo
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        // ToDo
        return false;
    }

    @Override
    public Iterable<Category> findAll() {
        // ToDo
        return null;
    }

    @Override
    public Iterable<Category> findAllById(Iterable<Integer> integers) {
        // ToDo
        return null;
    }

    @Override
    public long count() {
        // ToDo
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {
        // ToDo
    }

    @Override
    public void delete(Category entity) {
        // ToDo
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        // ToDo
    }

    @Override
    public void deleteAll(Iterable<? extends Category> entities) {
        // ToDo
    }

    @Override
    public void deleteAll() {
        // ToDo
    }
}
