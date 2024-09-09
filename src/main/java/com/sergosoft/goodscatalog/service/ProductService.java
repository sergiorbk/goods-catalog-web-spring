package com.sergosoft.goodscatalog.service;

import com.sergosoft.goodscatalog.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product createProduct(Product product);
    Product updateProduct(Long id, Product product);
    boolean deleteProduct(Long id);
}
