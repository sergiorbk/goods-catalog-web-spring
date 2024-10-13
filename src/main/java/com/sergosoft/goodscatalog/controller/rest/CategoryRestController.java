package com.sergosoft.goodscatalog.controller.rest;

import com.sergosoft.goodscatalog.dto.category.CategoryCreationRequest;
import com.sergosoft.goodscatalog.dto.category.CategoryDto;
import com.sergosoft.goodscatalog.dto.product.ProductDto;
import com.sergosoft.goodscatalog.dto.product.ProductFilter;
import com.sergosoft.goodscatalog.mapper.CategoryMapper;
import com.sergosoft.goodscatalog.model.Category;
import com.sergosoft.goodscatalog.model.Product;
import com.sergosoft.goodscatalog.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.PagedModel;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController("restCategoryController")
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
@Slf4j
public class CategoryRestController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<CategoryDto>>> getAllPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            PagedResourcesAssembler<CategoryDto> pagedAssembler) {

        log.info("Received request to show all categories");
        Page<Category> categories = categoryService.filteredCategories(name, description, page, size);
        Page<CategoryDto> categoriesDtoPage = categories.map(categoryMapper::toDto);
        if(categories.isEmpty()){
            log.info("No categories found");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        PagedModel<EntityModel<CategoryDto>> pagedModel = pagedAssembler.toModel(categoriesDtoPage);
        log.info("Retrieved {} categories", pagedModel.getContent().size());
        return new ResponseEntity<>(pagedModel, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Integer categoryId) {
        log.info("Received request to show category with id {}", categoryId);
        Category category = categoryService.getCategoryById(categoryId);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping("/moderate")
    public ResponseEntity<Category> createCategory(@RequestBody CategoryCreationRequest categoryCreationRequest) {
        log.info("Received request to create category {}", categoryCreationRequest.getName());
        Category category = categoryService.addCategory(categoryCreationRequest);
        log.info("Category created with ID: {}", category.getId());
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @PutMapping("/moderate/{categoryId}")
    public ResponseEntity<Category> updateCategory(
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
        categoryService.updateCategory(category.getId(), category);
        log.info("Category updated with ID: {}", categoryId);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @DeleteMapping("/moderate/{categoryId}")
    public ResponseEntity<Category> deleteCategory(@PathVariable Integer categoryId) {
        log.info("Received request to delete category with id {}", categoryId);
        Category category = categoryService.getCategoryById(categoryId);
        categoryService.deleteCategory(categoryId);
        log.info("Category deleted with ID: {}", categoryId);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }
}
