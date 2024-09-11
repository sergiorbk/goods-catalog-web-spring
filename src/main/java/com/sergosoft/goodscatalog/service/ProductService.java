package com.sergosoft.goodscatalog.service;

import com.sergosoft.goodscatalog.dto.product.ProductCreationRequest;
import com.sergosoft.goodscatalog.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product createProduct(ProductCreationRequest productCreationRequest);
    Product updateProduct(Long id, Product product);
    boolean deleteProduct(Long id);
}
