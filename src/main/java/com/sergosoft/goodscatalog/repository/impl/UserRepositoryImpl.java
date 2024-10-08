package com.sergosoft.goodscatalog.repository.impl;

import java.util.Optional;

import com.sergosoft.goodscatalog.model.user.UserRole;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.sergosoft.goodscatalog.model.user.UserEntity;
import com.sergosoft.goodscatalog.repository.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean existsByUsername(String username) {
        String sql = "SELECT COUNT(*) FROM users WHERE username = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{username}, Integer.class);
        return count != null && count > 0;
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";

        try {
            UserEntity user = jdbcTemplate.queryForObject(sql, new Object[]{username}, (rs, rowNum) -> {
                UserEntity u = new UserEntity();
                u.setId(rs.getLong("id"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setRole(UserRole.valueOf(rs.getString("role")));
                return u;
            });

            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
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
