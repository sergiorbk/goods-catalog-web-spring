package com.sergosoft.goodscatalog.controller;

import com.sergosoft.goodscatalog.mapper.CategoryMapper;
import com.sergosoft.goodscatalog.model.Category;
import com.sergosoft.goodscatalog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @GetMapping("/all")
    public String getAllCategories(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories.stream().map(categoryMapper::toDto));
        return "categories";
    }

    @GetMapping("/{categoryId}")
    public String getCategoryById(@PathVariable Long categoryId, Model model) {
        Category category = categoryService.getCategoryById(categoryId);
        model.addAttribute("category", categoryMapper.toDto(category));
        return "category";
    }

}
