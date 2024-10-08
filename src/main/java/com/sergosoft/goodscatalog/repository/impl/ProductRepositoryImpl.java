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

    // unused
    @Override
    public <S extends Product> Iterable<S> saveAll(Iterable<S> entities) {
        throw new UnsupportedOperationException("Method 'save' is not implemented yet.");
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

    // unused
    @Override
    public Iterable<Product> findAllById(Iterable<Long> longs) {
        throw new UnsupportedOperationException("Method 'save' is not implemented yet.");
    }

    // unused
    @Override
    public long count() {
        throw new UnsupportedOperationException("Method 'save' is not implemented yet.");
    }

    @Override
    public void deleteById(Long aLong) {
        // ToDo
    }

    @Override
    public void delete(Product entity) {
        // ToDo
    }

    //unused
    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        throw new UnsupportedOperationException("Method 'save' is not implemented yet.");
    }

    // unused
    @Override
    public void deleteAll(Iterable<? extends Product> entities) {
        throw new UnsupportedOperationException("Method 'save' is not implemented yet.");
    }

    //unused
    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Method 'save' is not implemented yet.");
    }
}
