package com.sergosoft.goodscatalog.repository.impl;

import com.sergosoft.goodscatalog.model.Product;
import com.sergosoft.goodscatalog.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("stub")
@Deprecated(forRemoval = true)
public class ProductRepositoryStub implements ProductRepository {

    @Override
    public List<Product> findAllProducts() {
        return List.of();
    }

    @Override
    public Product findProductById(Long id) {
        return null;
    }

    @Override
    public Product saveProduct(Product product) {
        return null;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public boolean deleteProduct(Long id) {
        return false;
    }
}
