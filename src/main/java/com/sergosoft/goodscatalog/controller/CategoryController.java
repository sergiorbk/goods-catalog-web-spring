package com.sergosoft.goodscatalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    private CategoryMapper categoryMapper;

    //
    // GET methods to return HTML pages
    //
    @GetMapping({"", "/", "/all"})
    public String showAllCategories(Model model, Authentication auth) {
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("isAdmin", auth != null && auth.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(UserRole.ADMIN.name())));
        return "categories";
    }

    @GetMapping("/{categoryId}")
    public String showCategoryById(@PathVariable Integer categoryId, Model model) {
        Category category = categoryService.getCategoryById(categoryId);
        model.addAttribute("category", category);
        return "category";
    }

    @GetMapping("/moderate/create")
    public String createCategory(Model model) {
        model.addAttribute("category", new CategoryCreationRequest());
        return "admin/category_form";
    }

    @GetMapping("/moderate/{categoryId}/edit")
    public String showEditProductForm(@PathVariable Integer categoryId, Model model) {
        Category category = categoryService.getCategoryById(categoryId);
        model.addAttribute("category", categoryMapper.toDto(category));
        return "admin/category_update_form";
    }

    //
    // Executive methods (POST, PUT, DELETE and/or their GET representations for MVC)
    //
    @PostMapping("/moderate/create")
    public String createCategory(@Valid @ModelAttribute("category") CategoryCreationRequest categoryCreationRequest) {
        Category category = categoryService.addCategory(categoryCreationRequest);
        return "redirect:/categories/" + category.getId();
    }

    @PutMapping("/moderate/{categoryId}")
    public String updateCategory(
            @PathVariable Integer categoryId,
            @Valid @ModelAttribute("category") CategoryDto categoryDto,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "admin/category_update_form";
        }
        categoryDto.setId(categoryId);
        categoryService.updateCategory(categoryDto.getId(), categoryMapper.toEntity(categoryDto));
        return "redirect:/categories/all";
    }

    @GetMapping("/moderate/{categoryId}/delete")
    public String deleteCategory(@PathVariable Integer categoryId) {
        categoryService.deleteCategory(categoryId);
        return "redirect:/categories/all";
    }
}
