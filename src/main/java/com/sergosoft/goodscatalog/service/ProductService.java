package com.sergosoft.goodscatalog.service;

import com.sergosoft.goodscatalog.dto.product.ProductFilter;
import com.sergosoft.goodscatalog.dto.product.ProductRequest;
import com.sergosoft.goodscatalog.model.Product;
import org.springframework.data.domain.Page;

public interface ProductService {
    Page<Product> getFilteredProductsByPage(int page, int pageSize, ProductFilter filter);
    Product getProductById(Long id);
    Product createProduct(ProductRequest productRequest);
    Product updateProduct(Long id, ProductRequest productRequest);
    void deleteProduct(Long id);
}
