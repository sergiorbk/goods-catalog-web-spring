package com.sergosoft.goodscatalog.mapper;

import org.springframework.stereotype.Component;

import com.sergosoft.goodscatalog.dto.user.UserDto;
import com.sergosoft.goodscatalog.dto.user.UserRegisterRequest;
import com.sergosoft.goodscatalog.model.user.UserEntity;
import com.sergosoft.goodscatalog.model.user.UserRole;

@Component
public class UserMapper {

    public UserEntity toEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        return new UserEntity(userDto.getId(), userDto.getUsername(), userDto.getPassword(), userDto.getRole());
    }

    public UserDto toDto(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        return new UserDto(userEntity.getId(), userEntity.getUsername(), userEntity.getPassword(), userEntity.getRole());
    }

    public UserEntity toEntity(UserRegisterRequest request) {
        if (request == null) {
            return null;
        }
        return new UserEntity(null, request.getUsername(), request.getPassword(), UserRole.USER);
    }
}
