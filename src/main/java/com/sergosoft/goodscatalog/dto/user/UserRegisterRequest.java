package com.sergosoft.goodscatalog.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {

    @NotBlank(message = "Username is mandatory.")
    @Size(max = 50, message = "Max username length is 50.")
    private String username;

    @NotBlank(message = "Password is mandatory.")
    @Size(min = 10, max = 255, message = "Password length must be between 10 and 255 characters.")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).+$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character."
    )
    private String password;
}