package com.sergosoft.goodscatalog.repository.impl;

import com.sergosoft.goodscatalog.model.Product;
import com.sergosoft.goodscatalog.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Qualifier("fake")
@Deprecated(forRemoval = true)
public class ProductRepositoryFake implements ProductRepository {

    private Map<Long, Product> products = new HashMap<>();

    @Override
    public List<Product> findAllProducts() {
        return new ArrayList<>(products.values());
    }

    @Override
    public Product findProductById(Long id) {
        return products.get(id);
    }

    @Override
    public Product saveProduct(Product product) {
        products.put(product.getId(), product);
        return product;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        products.put(id, product);
        return product;
    }

    @Override
    public boolean deleteProduct(Long id) {
        return products.remove(id) != null;
    }
}
