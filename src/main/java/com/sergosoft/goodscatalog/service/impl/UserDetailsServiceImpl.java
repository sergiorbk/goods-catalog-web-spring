package com.sergosoft.goodscatalog.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sergosoft.goodscatalog.repository.impl.UserRepositoryImpl;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepositoryImpl userRepository;

    public UserDetailsServiceImpl(UserRepositoryImpl userRepository) { this.userRepository = userRepository; }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Attempting to load user by username: {}", username);

        return userRepository.findByUsername(username)
                .map(user -> {
                    log.info("User found with username: {}", username);
                    return user;
                })
                .orElseThrow(() -> {
                    String errorMessage = "User not found with username: " + username;
                    log.error(errorMessage);
                    return new UsernameNotFoundException(errorMessage);
                });
    }
}
