package com.sergosoft.goodscatalog.service.impl;

import com.sergosoft.goodscatalog.dto.product.ProductCreationRequest;
import com.sergosoft.goodscatalog.exception.EntityNotFoundException;
import com.sergosoft.goodscatalog.mapper.ProductMapper;
import com.sergosoft.goodscatalog.model.Product;
import com.sergosoft.goodscatalog.repository.ProductRepository;
import com.sergosoft.goodscatalog.service.ProductService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("No product with ID: " + id + " not found."));
    }

    @Override
    public Product createProduct(ProductCreationRequest request) {
        return productRepository.save(productMapper.toEntity(request));
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
