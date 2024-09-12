package com.sergosoft.goodscatalog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

import com.sergosoft.goodscatalog.dto.user.UserRegisterRequest;
import com.sergosoft.goodscatalog.exception.EntityUniqueViolationException;
import com.sergosoft.goodscatalog.service.UserService;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    //
    // GET methods to return HTML pages
    //
    @GetMapping("/register")
    public String getRegistrationForm(Model model) {
        model.addAttribute(new UserRegisterRequest());
        return "auth/register";
    }

    //
    // Executive methods (POST, PUT, DELETE and/or their GET representations for MVC)
    //
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
