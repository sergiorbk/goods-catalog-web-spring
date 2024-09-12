package com.sergosoft.goodscatalog.controller;

import com.sergosoft.goodscatalog.dto.category.CategoryCreationRequest;
import com.sergosoft.goodscatalog.dto.category.CategoryDto;
import com.sergosoft.goodscatalog.mapper.CategoryMapper;
import com.sergosoft.goodscatalog.model.Category;
import com.sergosoft.goodscatalog.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @GetMapping({"/", "/all"})
    public String getAllCategories(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories.stream().map(categoryMapper::toDto));
        return "categories";
    }

    @GetMapping("/{categoryId}")
    public String getCategoryById(@PathVariable Integer categoryId, Model model) {
        Category category = categoryService.getCategoryById(categoryId);
        model.addAttribute("category", categoryMapper.toDto(category));
        return "category";
    }

    @GetMapping("/create")
    public String createCategory(Model model) {
        model.addAttribute("category", new CategoryCreationRequest());
        return "admin/category_form";
    }

    @GetMapping("/edit/{categoryId}")
    public String editProduct(@PathVariable Integer categoryId, Model model) {
        Category category = categoryService.getCategoryById(categoryId);
        model.addAttribute("category", categoryMapper.toDto(category));
        return "admin/category_update_form";
    }

    @PostMapping("/create")
    public String createCategory(@Valid @ModelAttribute("category") CategoryCreationRequest categoryCreationRequest) {
        Category category = categoryService.addCategory(categoryCreationRequest);
        return "redirect:/categories/" + category.getId();
    }

    @PutMapping("/edit/{categoryId}")
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

    @DeleteMapping("/delete/{categoryId}")
    public String deleteCategory(@PathVariable Integer categoryId) {
        categoryService.deleteCategory(categoryId);
        return "redirect:/categories/all";
    }
}
