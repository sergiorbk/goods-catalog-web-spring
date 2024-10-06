package com.sergosoft.goodscatalog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.Authentication;
import com.sergosoft.goodscatalog.dto.category.CategoryCreationRequest;
import com.sergosoft.goodscatalog.dto.category.CategoryDto;
import com.sergosoft.goodscatalog.mapper.CategoryMapper;
import com.sergosoft.goodscatalog.model.Category;
import com.sergosoft.goodscatalog.service.CategoryService;
import com.sergosoft.goodscatalog.model.user.UserRole;

@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping({"", "/", "/all"})
    public String showAllCategories(Model model, Authentication auth) {
        log.info("Received request to show all categories");

        model.addAttribute("categories", categoryService.getAllCategories());

        boolean isAdmin = auth != null && auth.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(UserRole.ADMIN.name()));

        model.addAttribute("isAdmin", isAdmin);

        log.info("Returning all categories view. Is admin: {}", isAdmin);

        return "categories";
    }

    @GetMapping("/{categoryId}")
    public String showCategoryById(@PathVariable Integer categoryId, Model model) {
        log.info("Received request to show category with ID: {}", categoryId);

        Category category = categoryService.getCategoryById(categoryId);
        model.addAttribute("category", category);

        log.info("Returning view for category with ID: {}", categoryId);

        return "category";
    }

    @GetMapping("/moderate/create")
    public String createCategory(Model model) {
        log.info("Received request to show category creation form");

        model.addAttribute("category", new CategoryCreationRequest());

        log.info("Returning category creation form view");

        return "admin/category_form";
    }

    @GetMapping("/moderate/{categoryId}/edit")
    public String showEditCategoryForm(@PathVariable Integer categoryId, Model model) {
        log.info("Received request to show edit form for category with ID: {}", categoryId);

        Category category = categoryService.getCategoryById(categoryId);
        model.addAttribute("category", categoryMapper.toDto(category));

        log.info("Returning category edit form view for category with ID: {}", categoryId);

        return "admin/category_update_form";
    }

    @PostMapping("/moderate/create")
    public String createCategory(@Valid @ModelAttribute("category") CategoryCreationRequest categoryCreationRequest) {
        log.info("Received request to create category with name: {}", categoryCreationRequest.getName());

        Category category = categoryService.addCategory(categoryCreationRequest);

        log.info("Category created with ID: {}", category.getId());

        return "redirect:/categories/" + category.getId();
    }

    @PutMapping("/moderate/{categoryId}")
    public String updateCategory(
            @PathVariable Integer categoryId,
            @Valid @ModelAttribute("category") CategoryDto categoryDto,
            BindingResult result
    ) {
        log.info("Received request to update category with ID: {}", categoryId);

        if (result.hasErrors()) {
            log.warn("Validation errors occurred during category update: {}", result.getAllErrors());
            return "admin/category_update_form";
        }

        categoryDto.setId(categoryId);
        categoryService.updateCategory(categoryDto.getId(), categoryMapper.toEntity(categoryDto));

        log.info("Category updated with ID: {}", categoryId);

        return "redirect:/categories/all";
    }

    @GetMapping("/moderate/{categoryId}/delete")
    public String deleteCategory(@PathVariable Integer categoryId) {
        log.info("Received request to delete category with ID: {}", categoryId);

        categoryService.deleteCategory(categoryId);

        log.info("Category deleted with ID: {}", categoryId);

        return "redirect:/categories/all";
    }
}
