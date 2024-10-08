package com.sergosoft.goodscatalog.repository;

import com.sergosoft.goodscatalog.model.user.UserEntity;

import java.util.Optional;

public interface UserRepository {
    boolean existsByUsername(String username);
    Optional<UserEntity> findByUsername(String username);
}
