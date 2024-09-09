package com.sergosoft.goodscatalog.repository;

import com.sergosoft.goodscatalog.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<Product> findAllProducts();
    Optional<Product> findProductById(Long id);
    Product saveProduct(Product product);
    Product updateProduct(Long id, Product product);
    boolean deleteProduct(Long id);
}
