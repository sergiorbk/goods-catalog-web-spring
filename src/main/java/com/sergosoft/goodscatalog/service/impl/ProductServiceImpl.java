package com.sergosoft.goodscatalog.service.impl;

import com.sergosoft.goodscatalog.dto.ProductCreateRequest;
import com.sergosoft.goodscatalog.dto.ProductDTO;
import com.sergosoft.goodscatalog.dto.ProductUpdateRequest;
import com.sergosoft.goodscatalog.repository.ProductRepository;
import com.sergosoft.goodscatalog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(@Qualifier("fake") ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        //TODO
        return List.of();
    }

    @Override
    public ProductDTO getProductById(Long id) {
        //TODO
        return null;
    }

    @Override
    public ProductDTO createProduct(ProductCreateRequest product) {
        //TODO
        return null;
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductUpdateRequest product) {
        //TODO
        return null;
    }

    @Override
    public boolean deleteProduct(Long id) {
        //TODO
        return false;
    }
}
