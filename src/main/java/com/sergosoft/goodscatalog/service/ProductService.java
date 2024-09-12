package com.sergosoft.goodscatalog.service;

import java.util.List;

import com.sergosoft.goodscatalog.dto.product.ProductCreationRequest;
import com.sergosoft.goodscatalog.model.Product;

public interface ProductService {

    List<Product> getAllProducts();

    Product getProductById(Long id);

    Product createProduct(ProductCreationRequest productCreationRequest);

    void updateProduct(Long id, Product product);

    void deleteProduct(Long id);
}
