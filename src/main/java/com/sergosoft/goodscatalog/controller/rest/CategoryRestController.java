package com.sergosoft.goodscatalog.controller.rest;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.sergosoft.goodscatalog.dto.category.CategoryCreationRequest;
import com.sergosoft.goodscatalog.dto.category.CategoryDto;
import com.sergosoft.goodscatalog.mapper.CategoryMapper;
import com.sergosoft.goodscatalog.model.Category;
import com.sergosoft.goodscatalog.service.CategoryService;

@RestController("restCategoryController")
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
@Slf4j
public class CategoryRestController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        log.info("Received request to get all categories.");
        List<Category> categories = categoryService.getAllCategories();
        List<CategoryDto> categoryDtoList = categories.stream().map(categoryMapper::toDto).toList();
        return new ResponseEntity<>(categoryDtoList, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer categoryId) {
        log.info("Received request to get category with id {}", categoryId);
        Category category = categoryService.getCategoryById(categoryId);
        CategoryDto categoryDto = categoryMapper.toDto(category);
        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }

    @PostMapping("/moderate")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryCreationRequest categoryCreationRequest) {
        log.info("Received request to create category {}", categoryCreationRequest.getName());
        Category category = categoryService.addCategory(categoryCreationRequest);
        CategoryDto categoryDto = categoryMapper.toDto(category);
        log.info("Category created with ID: {}", category.getId());
        return new ResponseEntity<>(categoryDto, HttpStatus.CREATED);
    }

    @PutMapping("/moderate/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(
            @PathVariable Integer categoryId,
            @RequestBody  CategoryCreationRequest categoryCreationRequest,
            BindingResult bindingResult){

        log.info("Received request to update category with id {}", categoryId);
        if (bindingResult.hasErrors()) {
            log.warn("Validation errors occurred during category update: {}", bindingResult.getAllErrors());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Category category = categoryMapper.toEntity(categoryCreationRequest);
        category.setId(categoryId);
        Category updatedCategory = categoryService.updateCategory(category.getId(), category);
        log.info("Category updated with ID: {}", categoryId);

        CategoryDto updatedCategoryDto = categoryMapper.toDto(updatedCategory);
        return new ResponseEntity<>(updatedCategoryDto, HttpStatus.OK);
    }

    @DeleteMapping("/moderate/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer categoryId) {
        log.info("Received request to delete category with id {}", categoryId);
        categoryService.deleteCategory(categoryId);
        log.info("Category deleted with ID: {}", categoryId);
        return ResponseEntity.ok().build();
    }
}
