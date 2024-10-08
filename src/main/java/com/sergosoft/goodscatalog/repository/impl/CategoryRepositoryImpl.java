package com.sergosoft.goodscatalog.repository.impl;

import com.sergosoft.goodscatalog.model.Category;
import com.sergosoft.goodscatalog.model.Product;
import com.sergosoft.goodscatalog.repository.CategoryRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
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
        String sql = "SELECT COUNT(*) FROM categories WHERE name = ?";

        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{name}, Integer.class);

        return count != null && count > 0;
    }

    //
    // CrudRepository methods
    //
    @Override
    public <S extends Category> S save(S category) {
        if (category.getId() == null) {
            String sql = "INSERT INTO categories (name, description, parent_id) VALUES (?, ?, ?)";

            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
                ps.setString(1, category.getName());
                ps.setString(2, category.getDescription());
                if (category.getParent() != null) {
                    ps.setInt(3, category.getParent().getId());
                } else {
                    ps.setNull(3, java.sql.Types.INTEGER);
                }
                return ps;
            }, keyHolder);

            category.setId(keyHolder.getKey().intValue());
        } else {
            String updateSql = "UPDATE categories SET name = ? WHERE id = ?";
            jdbcTemplate.update(updateSql, category.getName(), category.getId());
        }

        return category;
    }

    // unused
    @Override
    public <S extends Category> Iterable<S> saveAll(Iterable<S> entities) {
        throw new UnsupportedOperationException("Method 'save' is not implemented yet.");
    }

    @Override
    public Optional<Category> findById(Integer id) {
        String sql = "SELECT * FROM categories WHERE id = ?";
        try {
            Category category = jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
                Category parentCategory = null;

                Integer parentId = rs.getObject("parent_id", Integer.class);
                if (parentId != null) {
                    parentCategory = findById(parentId).orElse(null);
                }
                return new Category(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        parentCategory,
                        new ArrayList<>(),
                        new ArrayList<>()
                );
            });

            assert category != null;
            category.setSubCategories(findSubCategoriesByParentId(category.getId()));

            category.setProducts(findProductsByCategoryId(category.getId()));

            return Optional.of(category);

        } catch (EmptyResultDataAccessException e) {
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
                new ArrayList<>(),
                null
        ));
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM categories WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);

        if (rowsAffected == 0) {
            throw new IllegalArgumentException("Category with id " + id + " does not exist.");
        }
    }

    @Override
    public boolean existsById(Integer id) {
        String sql = "SELECT COUNT(*) FROM categories WHERE id = ?";

        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{id}, Integer.class);

        return count != null && count > 0;
    }

    @Override
    public Iterable<Category> findAll() {
        List<Category> allCategories = new ArrayList<>();
        // ToDo
        return allCategories;
    }

    // unused
    @Override
    public Iterable<Category> findAllById(Iterable<Integer> integers) {
        throw new UnsupportedOperationException("Method 'save' is not implemented yet.");
    }

    // unused
    @Override
    public long count() {
        throw new UnsupportedOperationException("Method 'save' is not implemented yet.");
    }

    @Override
    public void delete(Category entity) {
        // ToDo
    }

    // unused
    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        throw new UnsupportedOperationException("Method 'save' is not implemented yet.");
    }

    // unused
    @Override
    public void deleteAll(Iterable<? extends Category> entities) {
        throw new UnsupportedOperationException("Method 'save' is not implemented yet.");
    }

    // unused
    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Method 'save' is not implemented yet.");
    }
}
