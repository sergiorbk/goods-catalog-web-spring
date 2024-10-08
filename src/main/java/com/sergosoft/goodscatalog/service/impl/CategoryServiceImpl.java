package com.sergosoft.goodscatalog.service.impl;

import java.util.List;

import com.sergosoft.goodscatalog.exception.EntityUniqueViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.sergosoft.goodscatalog.dto.category.CategoryCreationRequest;
import com.sergosoft.goodscatalog.exception.EntityNotFoundException;
import com.sergosoft.goodscatalog.mapper.CategoryMapper;
import com.sergosoft.goodscatalog.model.Category;
import com.sergosoft.goodscatalog.repository.CategoryRepository;
import com.sergosoft.goodscatalog.service.CategoryService;
import com.sergosoft.goodscatalog.service.specification.CategorySpecifications;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<Category> getAllCategories() {
        log.debug("Fetching all categories");
        List<Category> categories = categoryRepository.findAll();
        log.info("Retrieved {} categories", categories.size());
        return categories;
    }

    //
    // Page without filtering
    //
    @Override
    public Page<Category> getPage(int page, int size) {
        log.debug("Fetching all pageable categories");
        Pageable pageable = PageRequest.of(page, size);
        return categoryRepository.findAll(pageable);
    }

    //
    // Page with filtering
    //
    @Override
    public Page<Category> filteredCategories(String name, String description, int page, int size){
        log.debug("Fetching all filtered categories");
        Specification<Category> spec = Specification.where(CategorySpecifications.hasName(name))
                .and(CategorySpecifications.hasDescription(description));
        return categoryRepository.findAll(spec, PageRequest.of(page, size));
    }

    @Override
    public Category getCategoryById(Integer id) {
        log.debug("Fetching category by ID: {}", id);
        Category category = findCategoryOrThrow(id);
        log.info("Retrieved category with ID: {}", id);
        return category;
    }

    @Override
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
    public void updateCategory(Integer id, Category category) {
        log.debug("Updating category with ID: {}", id);
        ensureCategoryExists(id);

        categoryRepository.save(category);
        log.info("Updated category with ID: {}", id);
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
