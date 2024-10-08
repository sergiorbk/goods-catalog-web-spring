package com.sergosoft.goodscatalog.service;

import com.sergosoft.goodscatalog.dto.product.ProductRequest;
import com.sergosoft.goodscatalog.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product createProduct(ProductRequest productRequest);
    Product updateProduct(Long id, ProductRequest productRequest);
    void deleteProduct(Long id);
}
