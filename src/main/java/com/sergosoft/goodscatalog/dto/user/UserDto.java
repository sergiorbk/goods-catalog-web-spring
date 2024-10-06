package com.sergosoft.goodscatalog.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import com.sergosoft.goodscatalog.model.user.UserRole;

@Data
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String username;
    private String password;
    private UserRole role;
}
