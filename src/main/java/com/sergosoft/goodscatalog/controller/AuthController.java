package com.sergosoft.goodscatalog.controller;

import com.sergosoft.goodscatalog.dto.user.UserRegisterRequest;
import com.sergosoft.goodscatalog.exception.EntityUniqueViolationException;
import com.sergosoft.goodscatalog.mapper.UserMapper;
import com.sergosoft.goodscatalog.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final UserService userService;
    private final UserMapper userMapper;

    public AuthController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/register")
    public String getRegistrationForm(Model model) {
        model.addAttribute(new UserRegisterRequest());
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid UserRegisterRequest userRegisterRequest, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "auth/register";
        }

        try {
            userService.registerUser(userRegisterRequest);
        } catch (EntityUniqueViolationException e) {
            model.addAttribute("error", e.getMessage());
            return "auth/register";
        }

        return "redirect:/login";
    }
}
