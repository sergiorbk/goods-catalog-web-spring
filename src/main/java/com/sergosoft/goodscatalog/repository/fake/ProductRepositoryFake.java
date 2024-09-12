package com.sergosoft.goodscatalog.repository.fake;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sergosoft.goodscatalog.model.Product;

@Repository
@Qualifier("fake")
@Deprecated(forRemoval = true)
public class ProductRepositoryFake {

    private final Map<Long, Product> products = new HashMap<>();
    private Long id = 0L;

    public List<Product> findAllProducts() {
        return new ArrayList<>(products.values());
    }

    public Optional<Product> findProductById(Long id) {
        return Optional.ofNullable(products.get(id));
    }

    public Product saveProduct(Product product) {
        product.setId(++id);
        products.put(product.getId(), product);
        return product;
    }

    public Product updateProduct(Long id, Product product) {
        products.put(id, product);
        return product;
    }

    public boolean deleteProduct(Long id) {
        return products.remove(id) != null;
    }
}
