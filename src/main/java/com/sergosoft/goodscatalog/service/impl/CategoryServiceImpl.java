package com.sergosoft.goodscatalog.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.sergosoft.goodscatalog.dto.category.CategoryCreationRequest;
import com.sergosoft.goodscatalog.exception.EntityNotFoundException;
import com.sergosoft.goodscatalog.mapper.CategoryMapper;
import com.sergosoft.goodscatalog.model.Category;
import com.sergosoft.goodscatalog.repository.impl.CategoryRepositoryImpl;
import com.sergosoft.goodscatalog.service.CategoryService;
import com.sergosoft.goodscatalog.exception.EntityUniqueViolationException;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepositoryImpl categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<Category> getAllCategories() {
        log.debug("Fetching all categories");
        List<Category> categories = (ArrayList<Category>) categoryRepository.findAll();
        log.info("Retrieved {} categories", categories.size());
        return categories;
    }

    @Override
    public Category getCategoryById(Integer id) {
        log.debug("Fetching category by ID: {}", id);
        Category category = findCategoryOrThrow(id);
        log.info("Retrieved category with ID: {}", id);
        return category;
    }

    @Override
    @Transactional
    public Category addCategory(CategoryCreationRequest categoryCreationRequest) {
        String categoryName = categoryCreationRequest.getName();
        log.debug("Adding new category with name: {}", categoryName);

        if (categoryRepository.existsByName(categoryName) || categoryRepository.existsByName(categoryName.toLowerCase())) {
            String errorMessage = "Field 'name' for a new Category must be unique.";
            log.error(errorMessage);
            throw new EntityUniqueViolationException(errorMessage);
        }

        Category category = categoryRepository.save(categoryMapper.toEntity(categoryCreationRequest));
        log.info("Added new category with ID: {}", category.getId());
        return category;
    }

    @Override
    public Category updateCategory(Integer id, Category category) {
        log.debug("Updating category with ID: {}", id);
        ensureCategoryExists(id);
        Category updatedCategory = categoryRepository.save(category);
        log.info("Updated category with ID: {}", id);
        return updatedCategory;
    }

    @Override
    public void deleteCategory(Integer id) {
        log.debug("Deleting category with ID: {}", id);
        ensureCategoryExists(id);

        categoryRepository.deleteById(id);
        log.info("Deleted category with ID: {}", id);
    }

    private void ensureCategoryExists(Integer id) {
        if (!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException("No category with ID: " + id + " found.");
        }
    }

    private Category findCategoryOrThrow(Integer id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No category with ID: " + id + " found."));
    }
}
