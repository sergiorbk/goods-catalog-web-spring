package com.sergosoft.goodscatalog.dto.user;

import com.sergosoft.goodscatalog.model.user.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String username;
    private String password;
    private UserRole role;
}
