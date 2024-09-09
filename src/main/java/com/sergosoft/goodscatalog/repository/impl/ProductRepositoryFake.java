package com.sergosoft.goodscatalog.repository.impl;

import com.sergosoft.goodscatalog.model.Product;
import com.sergosoft.goodscatalog.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Qualifier("fake")
@Deprecated(forRemoval = true)
public class ProductRepositoryFake implements ProductRepository {

    private Map<Long, Product> products = new HashMap<>();
    private Long id = 0L;

    @Override
    public List<Product> findAllProducts() {
        return new ArrayList<>(products.values());
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        return Optional.ofNullable(products.get(id));
    }

    @Override
    public Product saveProduct(Product product) {
        product.setId(++id);
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
