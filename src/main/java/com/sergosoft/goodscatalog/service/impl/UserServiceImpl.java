package com.sergosoft.goodscatalog.service.impl;

import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private PasswordEncoder passwordEncoder;

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

    @Override
    public void changeRole(Long userId, UserRole role) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with ID: " + userId + " does not exist."));

        // update role if the new role is different from the current
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
