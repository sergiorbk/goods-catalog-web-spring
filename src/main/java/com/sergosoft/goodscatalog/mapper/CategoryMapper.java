package com.sergosoft.goodscatalog.mapper;

import com.sergosoft.goodscatalog.dto.CategoryCreationRequest;
import com.sergosoft.goodscatalog.dto.CategoryDto;
import com.sergosoft.goodscatalog.exception.EntityNotFoundException;
import com.sergosoft.goodscatalog.model.Category;
import com.sergosoft.goodscatalog.repository.CategoryRepository;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Optional;

@Component
public class CategoryMapper implements Mapper<Category, CategoryDto> {

    private final CategoryRepository categoryRepository;

    public CategoryMapper(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryDto toDto(Category category) {
        if(category == null){
            return null;
        }
        return new CategoryDto(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.getParent() == null ? null : category.getParent().getId(),
                category.getSubCategories(),
                category.getProducts()
        );
    }

    public Category toEntity(CategoryDto dto) {
        if(dto == null){
            return null;
        }
        Optional<Category> parentCategory = categoryRepository.findById(dto.getParentId());
        return new Category(
                dto.getId(),
                dto.getName(),
                dto.getDescription(),
                parentCategory.orElse(null),
                dto.getSubCategories(),
                dto.getProducts()
        );
    }

    public Category toEntity(CategoryCreationRequest categoryCreationRequest){
        if(categoryCreationRequest == null){
            return null;
        }

        Category parentCategoryById = null;
        if(categoryCreationRequest.getParentCategoryId() != null) {
            parentCategoryById = categoryRepository.findById(categoryCreationRequest.getParentCategoryId())
                    .orElseThrow(() -> new EntityNotFoundException("Category not found"));
        }

        return new Category(
                null,
                categoryCreationRequest.getName(),
                categoryCreationRequest.getDescription(),
                parentCategoryById,
                new ArrayList<>(),
                new ArrayList<>()
        );
    }
}