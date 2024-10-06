package com.sergosoft.goodscatalog.service;

import java.util.List;

import com.sergosoft.goodscatalog.dto.product.ProductRequest;
import com.sergosoft.goodscatalog.model.Product;

public interface ProductService {

    List<Product> getAllProducts();

    Product getProductById(Long id);

    Product createProduct(ProductRequest productRequest);

    void updateProduct(Long id, ProductRequest productRequest);

    void deleteProduct(Long id);
}
