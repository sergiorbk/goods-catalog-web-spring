package com.sergosoft.goodscatalog.repository.impl;

import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.sergosoft.goodscatalog.model.Category;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.sergosoft.goodscatalog.model.Product;
import com.sergosoft.goodscatalog.repository.ProductRepository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProductRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //
    // CrudRepository methods
    //
    @Override
    public <S extends Product> S save(S product) {
        if (product.getId() == null) {
            // saving non-existent product
            String sql = "INSERT INTO products(name, description, price, category_id) VALUES(?, ?, ?, ?);";
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"id"});
                preparedStatement.setString(1, product.getName());
                preparedStatement.setString(2, product.getDescription());
                preparedStatement.setDouble(3, product.getPrice());

                if (product.getCategory() != null) {
                    preparedStatement.setInt(4, product.getCategory().getId());
                } else {
                    preparedStatement.setNull(4, Types.INTEGER);
                }
                return preparedStatement;
            }, keyHolder);

            // setting id
            product.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        } else {
            // updating existent product
            String sql = "UPDATE products SET name = ?, description = ?, price = ?, category_id = ? WHERE id = ?";

            jdbcTemplate.update(connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, product.getName());
                preparedStatement.setString(2, product.getDescription());
                preparedStatement.setDouble(3, product.getPrice());

                if (product.getCategory() != null) {
                    preparedStatement.setInt(4, product.getCategory().getId());
                } else {
                    preparedStatement.setNull(4, Types.INTEGER);
                }
                preparedStatement.setLong(5, product.getId()); // set id for update
                return preparedStatement;
            });
        }
        return product;
    }

    @Override
    public Optional<Product> findById(Long id) {
        String sql = "SELECT id, name, description, price, category_id FROM products WHERE id = ?";

        try {
            Product product = jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
                Product p = new Product();
                p.setId(rs.getLong("id"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getDouble("price"));

                int categoryId = rs.getInt("category_id");
                if (!rs.wasNull()) {
                    Category category = new Category();
                    category.setId(categoryId);
                    p.setCategory(category);
                }
                return p;
            });
            return Optional.ofNullable(product);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Iterable<Product> findAll() {
        String sql = "SELECT id, name, description, price, category_id FROM products";

        List<Product> products = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Product product = new Product();
            product.setId(rs.getLong("id"));
            product.setName(rs.getString("name"));
            product.setDescription(rs.getString("description"));
            product.setPrice(rs.getDouble("price"));

            int categoryId = rs.getInt("category_id");
            if (!rs.wasNull()) {
                Category category = new Category();
                category.setId(categoryId);
                product.setCategory(category);
            }
            return product;
        });

        return products;
    }

    @Override
    public boolean existsById(Long id) {
        String sql = "SELECT COUNT(*) FROM products WHERE id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{id}, Integer.class);
        return count != null && count > 0;
    }

    // unused
    @Override
    public <S extends Product> Iterable<S> saveAll(Iterable<S> entities) {
        throw new UnsupportedOperationException("Method 'save' is not implemented yet.");
    }

    // unused
    @Override
    public Iterable<Product> findAllById(Iterable<Long> longs) {
        throw new UnsupportedOperationException("Method 'save' is not implemented yet.");
    }

    // unused
    @Override
    public long count() {
        throw new UnsupportedOperationException("Method 'save' is not implemented yet.");
    }

    @Override
    public void deleteById(Long aLong) {
        // ToDo
    }

    @Override
    public void delete(Product entity) {
        // ToDo
    }

    //unused
    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        throw new UnsupportedOperationException("Method 'save' is not implemented yet.");
    }

    // unused
    @Override
    public void deleteAll(Iterable<? extends Product> entities) {
        throw new UnsupportedOperationException("Method 'save' is not implemented yet.");
    }

    //unused
    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Method 'save' is not implemented yet.");
    }
}
