package com.sergosoft.goodscatalog.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sergosoft.goodscatalog.dto.product.ProductRequest;
import com.sergosoft.goodscatalog.exception.EntityNotFoundException;
import com.sergosoft.goodscatalog.mapper.ProductMapper;
import com.sergosoft.goodscatalog.model.Product;
import com.sergosoft.goodscatalog.repository.ProductRepository;
import com.sergosoft.goodscatalog.service.ProductService;

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
                () -> new EntityNotFoundException("No product with ID: " + id + " found."));
    }

    @Override
    public Product createProduct(ProductRequest request) {
        return productRepository.save(productMapper.toEntity(request));
    }

    @Override
    public void updateProduct(Long id, ProductRequest productRequest) {
        if(!productRepository.existsById(id)) {
            throw new EntityNotFoundException("No product with ID: " + id + " found.");
        }

        Product product = productMapper.toEntity(productRequest);
        product.setId(id);

        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        if(productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("No product with ID: " + id + " found.");
        }
    }
}
