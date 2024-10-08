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
}
