package com.sergosoft.goodscatalog.service;

import com.sergosoft.goodscatalog.dto.user.UserRegisterRequest;
import com.sergosoft.goodscatalog.model.user.UserEntity;
import com.sergosoft.goodscatalog.model.user.UserRole;

public interface UserService {

    UserEntity registerUser(UserRegisterRequest request);

    void changeRole(Long userId, UserRole role);
}
