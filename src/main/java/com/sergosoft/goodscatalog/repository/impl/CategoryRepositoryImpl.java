package com.sergosoft.goodscatalog.repository.impl;

import com.sergosoft.goodscatalog.model.Category;
import com.sergosoft.goodscatalog.model.Product;
import com.sergosoft.goodscatalog.repository.CategoryRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    private final JdbcTemplate jdbcTemplate;

    public CategoryRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean existsByName(String name) {
        // ToDo
        return false;
    }

    //
    // CrudRepository methods
    //
    @Override
    public <S extends Category> S save(S entity) {
        // ToDo
        return null;
    }

    @Override
    public <S extends Category> Iterable<S> saveAll(Iterable<S> entities) {
        // ToDo
        return null;
    }

    @Override
    public Optional<Category> findById(Integer id) {
        String sql = "SELECT * FROM categories WHERE id = ?";
        try {
            Category category = jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
                Category parentCategory = null;

                // check if parent category exists
                Integer parentId = rs.getObject("parent_id", Integer.class);
                if (parentId != null) {
                    parentCategory = findById(parentId).orElse(null); // Рекурсивно отримуємо батьківську категорію
                }
                return new Category(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        parentCategory,  // Категорія-батько
                        new ArrayList<>(),  // Список підкатегорій (заповнимо пізніше)
                        new ArrayList<>()   // Список продуктів (заповнимо пізніше)
                );
            });

            // getting subcategories
            assert category != null;
            category.setSubCategories(findSubCategoriesByParentId(category.getId()));

            // getting products for category
            category.setProducts(findProductsByCategoryId(category.getId()));

            return Optional.of(category);

        } catch (EmptyResultDataAccessException e) {
            // if category was not found
            return Optional.empty();
        }
    }

    private List<Category> findSubCategoriesByParentId(Integer parentId) {
        String sql = "SELECT * FROM categories WHERE parent_id = ?";
        return jdbcTemplate.query(sql, new Object[]{parentId}, (rs, rowNum) -> new Category(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("description"),
                null,
                new ArrayList<>(),
                new ArrayList<>()
        ));
    }

    private List<Product> findProductsByCategoryId(Integer categoryId) {
        String sql = "SELECT * FROM products WHERE category_id = ?";
        return jdbcTemplate.query(sql, new Object[]{categoryId}, (rs, rowNum) -> new Product(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getDouble("price"),
                new ArrayList<>(),  // Список зображень продукту
                null
        ));
    }

    @Override
    public boolean existsById(Integer integer) {
        // ToDo
        return false;
    }

    @Override
    public Iterable<Category> findAll() {
        List<Category> allCategories = new ArrayList<>();
        // ToDo
        return allCategories;
    }

    @Override
    public Iterable<Category> findAllById(Iterable<Integer> integers) {
        // ToDo
        return null;
    }

    @Override
    public long count() {
        // ToDo
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {
        // ToDo
    }

    @Override
    public void delete(Category entity) {
        // ToDo
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        // ToDo
    }

    @Override
    public void deleteAll(Iterable<? extends Category> entities) {
        // ToDo
    }

    @Override
    public void deleteAll() {
        // ToDo
    }
}
