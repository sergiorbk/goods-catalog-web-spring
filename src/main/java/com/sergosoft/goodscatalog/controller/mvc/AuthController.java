package com.sergosoft.goodscatalog.controller.mvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import com.sergosoft.goodscatalog.dto.user.UserRegisterRequest;
import com.sergosoft.goodscatalog.exception.EntityUniqueViolationException;
import com.sergosoft.goodscatalog.service.UserService;

@Controller
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    //
    // GET methods to return HTML pages
    //
    @GetMapping("/register")
    public String getRegistrationForm(Model model) {
        log.info("Rendering registration form");
        model.addAttribute(new UserRegisterRequest());
        return "auth/register";
    }

    //
    // Executive methods (POST, PUT, DELETE and/or their GET representations for MVC)
    //
    @PostMapping("/register")
    public String registerUser(@Valid UserRegisterRequest userRegisterRequest, BindingResult result, Model model) {
        log.info("Received registration request for username: {}", userRegisterRequest.getUsername());

        if (result.hasErrors()) {
            log.warn("Validation errors occurred during registration: {}", result.getAllErrors());
            return "auth/register";
        }

        try {
            userService.registerUser(userRegisterRequest);
            log.info("User registered successfully with username: {}", userRegisterRequest.getUsername());
        } catch (EntityUniqueViolationException e) {
            log.error("EntityUniqueViolationException occurred: {}", e.getMessage());
            model.addAttribute("error", e.getMessage());
            return "auth/register";
        } catch (Exception e) {
            log.error("Unexpected error occurred during registration: {}", e.getMessage(), e);
            model.addAttribute("error", "An unexpected error occurred. Please try again.");
            return "auth/register";
        }

        return "redirect:/login";
    }
}
