package com.sergosoft.goodscatalog.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sergosoft.goodscatalog.dto.user.UserRegisterRequest;
import com.sergosoft.goodscatalog.exception.EntityNotFoundException;
import com.sergosoft.goodscatalog.exception.EntityUniqueViolationException;
import com.sergosoft.goodscatalog.mapper.UserMapper;
import com.sergosoft.goodscatalog.model.user.UserEntity;
import com.sergosoft.goodscatalog.model.user.UserRole;
import com.sergosoft.goodscatalog.repository.UserRepository;
import com.sergosoft.goodscatalog.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserEntity registerUser(UserRegisterRequest request) {
        UserEntity user = userMapper.toEntity(request);
        // check if username is unique
        if(userRepository.existsByUsername(user.getUsername())) {
            throw new EntityUniqueViolationException("User with username: " + user.getUsername() + " already exists.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }

    @Override
    public void changeRole(Long userId, UserRole role) {
        Optional<UserEntity> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()) {
            throw new EntityNotFoundException("User with ID: " + userId + " does not exist.");
        }
        UserEntity user = optionalUser.get();
        if(!user.getRole().equals(role)) {
            user.setRole(role);
            userRepository.save(user);
        }
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }
}
