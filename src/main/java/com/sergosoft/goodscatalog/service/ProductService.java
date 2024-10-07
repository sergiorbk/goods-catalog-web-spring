package com.sergosoft.goodscatalog.service;

import java.util.List;

import com.sergosoft.goodscatalog.dto.product.ProductDto;
import com.sergosoft.goodscatalog.dto.product.ProductFilter;
import com.sergosoft.goodscatalog.dto.product.ProductRequest;
import com.sergosoft.goodscatalog.model.Product;
import org.springframework.data.domain.Page;

public interface ProductService {

    Page<ProductDto> getFilteredProductsByPage(int page, int pageSize, ProductFilter filter);

    @Deprecated(forRemoval = true)
    List<Product> getAllProducts();

    Product getProductById(Long id);

    Product createProduct(ProductRequest productRequest);

    ProductDto updateProduct(Long id, ProductRequest productRequest);

    boolean deleteProduct(Long id);
}
