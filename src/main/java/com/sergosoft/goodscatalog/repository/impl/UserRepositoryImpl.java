package com.sergosoft.goodscatalog.repository.impl;

import com.sergosoft.goodscatalog.model.user.UserEntity;
import com.sergosoft.goodscatalog.repository.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean existsByUsername(String username) {
        // ToDo
        return false;
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        // ToDo
        return Optional.empty();
    }

    //
    // CrudRepository methods
    //
    @Override
    public <S extends UserEntity> S save(S entity) {
        // ToDo
        return null;
    }

    // unused
    @Override
    public <S extends UserEntity> Iterable<S> saveAll(Iterable<S> entities) {
        throw new UnsupportedOperationException("Method 'save' is not implemented yet.");
    }

    @Override
    public Optional<UserEntity> findById(Long aLong) {
        // ToDo
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        // ToDo
        return false;
    }

    @Override
    public Iterable<UserEntity> findAll() {
        // ToDo
        return null;
    }

    // unused
    @Override
    public Iterable<UserEntity> findAllById(Iterable<Long> longs) {
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
    public void delete(UserEntity entity) {
        // ToDo
    }

    // unused
    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        throw new UnsupportedOperationException("Method 'save' is not implemented yet.");
    }

    // unused
    @Override
    public void deleteAll(Iterable<? extends UserEntity> entities) {
        throw new UnsupportedOperationException("Method 'save' is not implemented yet.");
    }

    // unused
    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Method 'save' is not implemented yet.");
    }
}
