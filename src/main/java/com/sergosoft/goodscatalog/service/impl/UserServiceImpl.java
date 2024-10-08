package com.sergosoft.goodscatalog.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sergosoft.goodscatalog.dto.user.UserRegisterRequest;
import com.sergosoft.goodscatalog.exception.EntityNotFoundException;
import com.sergosoft.goodscatalog.exception.EntityUniqueViolationException;
import com.sergosoft.goodscatalog.mapper.UserMapper;
import com.sergosoft.goodscatalog.model.user.UserEntity;
import com.sergosoft.goodscatalog.model.user.UserRole;
import com.sergosoft.goodscatalog.repository.impl.UserRepositoryImpl;
import com.sergosoft.goodscatalog.service.UserService;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepositoryImpl userRepository;
    private final UserMapper userMapper;
    private PasswordEncoder passwordEncoder;

    @Override
    public UserEntity registerUser(UserRegisterRequest request) {
        log.debug("Registering user with request: {}", request);

        UserEntity user = userMapper.toEntity(request);
        log.debug("Mapped UserEntity: {}", user);

        // check if username is unique
        if (userRepository.existsByUsername(user.getUsername())) {
            String errorMessage = "User with username: " + user.getUsername() + " already exists.";
            log.error(errorMessage);
            throw new EntityUniqueViolationException(errorMessage);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.debug("Encoded password for user: {}", user.getUsername());

        UserEntity savedUser = userRepository.save(user);
        log.info("User registered successfully with ID: {}", savedUser.getId());
        return savedUser;
    }

    @Override
    public void changeRole(Long userId, UserRole role) {
        log.debug("Changing role for user with ID: {} to role: {}", userId, role);

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    String errorMessage = "User with ID: " + userId + " does not exist.";
                    log.error(errorMessage);
                    return new EntityNotFoundException(errorMessage);
                });

        // update role if the new role is different from the current
        if (!user.getRole().equals(role)) {
            log.debug("Updating role for user ID: {} from {} to {}", userId, user.getRole(), role);
            user.setRole(role);
            userRepository.save(user);
            log.info("Role updated successfully for user ID: {}", userId);
        } else {
            log.debug("Role for user ID: {} is already {}", userId, role);
        }
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
