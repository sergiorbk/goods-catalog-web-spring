package com.sergosoft.goodscatalog.service.impl;

import com.sergosoft.goodscatalog.dto.user.UserRegisterRequest;
import com.sergosoft.goodscatalog.exception.EntityUniqueViolationException;
import com.sergosoft.goodscatalog.mapper.UserMapper;
import com.sergosoft.goodscatalog.model.user.UserEntity;
import com.sergosoft.goodscatalog.repository.UserRepository;
import com.sergosoft.goodscatalog.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserEntity registerUser(UserRegisterRequest request) {
        UserEntity user = userMapper.toEntity(request);
        // check if username is unique
        if(userRepository.existsByUsername(user.getUsername())) {
            throw new EntityUniqueViolationException("User with username: " + user.getUsername() + " already exists.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
