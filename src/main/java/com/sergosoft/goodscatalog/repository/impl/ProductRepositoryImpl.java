package com.sergosoft.goodscatalog.repository.impl;

import com.sergosoft.goodscatalog.model.Category;
import com.sergosoft.goodscatalog.model.Product;
import com.sergosoft.goodscatalog.repository.ProductRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProductRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //
    // CrudRepository methods
    //
    @Override
    public <S extends Product> S save(S entity) {
        // ToDo
        return null;
    }

    @Override
    public <S extends Product> Iterable<S> saveAll(Iterable<S> entities) {
        // ToDo
        return null;
    }

    @Override
    public Optional<Product> findById(Long aLong) {
        // ToDo
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        // ToDo
        return false;
    }

    @Override
    public Iterable<Product> findAll() {
        // ToDo
        return null;
    }

    @Override
    public Iterable<Product> findAllById(Iterable<Long> longs) {
        // ToDo
        return null;
    }

    @Override
    public long count() {
        // ToDo
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {
        // ToDo
    }

    @Override
    public void delete(Product entity) {
        // ToDo
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        // ToDo
    }

    @Override
    public void deleteAll(Iterable<? extends Product> entities) {
        // ToDo
    }

    @Override
    public void deleteAll() {
        // ToDo
    }
}
