package com.sergosoft.goodscatalog.repository.impl;

import com.sergosoft.goodscatalog.model.Product;
import com.sergosoft.goodscatalog.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Deprecated(forRemoval = true)
public class ProductRepositoryFake implements ProductRepository {

    private Map<Long, Product> products = new HashMap<>();

    @Override
    public List<Product> findAllProducts() {
        // ToDo
        return List.of();
    }

    @Override
    public Product findProductById(Long id) {
        // ToDo
        return null;
    }

    @Override
    public Product saveProduct(Product product) {
        // ToDo
        return null;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        // ToDo
        return null;
    }

    @Override
    public boolean deleteProduct(Long id) {
        // ToDo
        return false;
    }
}
