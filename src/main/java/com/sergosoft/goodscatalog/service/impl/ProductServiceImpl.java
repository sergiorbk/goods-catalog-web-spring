package com.sergosoft.goodscatalog.service.impl;

import com.sergosoft.goodscatalog.model.Product;
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
    public List<Product> getAllProducts() {
        return productRepository.findAllProducts();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findProductById(id);
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.saveProduct(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return productRepository.updateProduct(id, product);
    }

    @Override
    public boolean deleteProduct(Long id) {
        return productRepository.deleteProduct(id);
    }
}
