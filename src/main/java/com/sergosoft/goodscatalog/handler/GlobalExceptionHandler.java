package com.sergosoft.goodscatalog.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

import com.sergosoft.goodscatalog.exception.EntityNotFoundException;
import com.sergosoft.goodscatalog.exception.EntityUniqueViolationException;

@ControllerAdvice(basePackages = "com.sergosoft.goodscatalog.controller.mvc")
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityUniqueViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleEntityUniqueValidationException(EntityUniqueViolationException ex, Model model) {
        log.error("Entity unique violation: {}", ex.getMessage());
        model.addAttribute("error", ex.getMessage());
        return "auth/register";
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleValidationExceptions(MethodArgumentNotValidException ex, Model model) {
        log.error("Validation error: {}", ex.getMessage());
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
            log.debug("Validation error in field '{}': {}", fieldName, errorMessage);
        });
        model.addAttribute("validationErrors", errors);
        return "error/validation_error";
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleEntityNotFoundException(EntityNotFoundException ex, Model model) {
        log.error("Entity not found: {}", ex.getMessage());
        model.addAttribute("errorMessage", ex.getMessage());
        return "error/404";
    }
}