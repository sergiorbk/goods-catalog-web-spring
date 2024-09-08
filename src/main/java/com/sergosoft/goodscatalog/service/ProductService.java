package com.sergosoft.goodscatalog.service;

import com.sergosoft.goodscatalog.dto.ProductDTO;
import com.sergosoft.goodscatalog.dto.ProductCreateRequest;
import com.sergosoft.goodscatalog.dto.ProductUpdateRequest;

import java.util.List;

public interface ProductService {

    List<ProductDTO> getAllProducts();
    ProductDTO getProductById(Long id);
    ProductDTO createProduct(ProductCreateRequest product);
    ProductDTO updateProduct(Long id, ProductUpdateRequest product);
    boolean deleteProduct(Long id);
}
