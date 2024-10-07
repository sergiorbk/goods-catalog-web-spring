package com.sergosoft.goodscatalog.service.impl;

import java.util.List;

import com.sergosoft.goodscatalog.dto.product.ProductDto;
import com.sergosoft.goodscatalog.dto.product.ProductFilter;
import com.sergosoft.goodscatalog.service.specification.ProductSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.sergosoft.goodscatalog.dto.product.ProductRequest;
import com.sergosoft.goodscatalog.exception.EntityNotFoundException;
import com.sergosoft.goodscatalog.mapper.ProductMapper;
import com.sergosoft.goodscatalog.model.Product;
import com.sergosoft.goodscatalog.repository.ProductRepository;
import com.sergosoft.goodscatalog.service.ProductService;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public Page<ProductDto> getFilteredProductsByPage(int page, int pageSize, ProductFilter filter) {
        log.debug("Fetching filtered products on page {} (page size {})", page, pageSize);

        Specification<Product> specification = Specification
                .where(ProductSpecification.hasPriceBetween(filter.getMinPrice(), filter.getMaxPrice()))
                .and(ProductSpecification.refersTo(filter.getCategoryId()));

        Page<Product> productsPage = productRepository.findAll(specification, PageRequest.of(page, pageSize));
        Page<ProductDto> productDtoPage = productsPage.map(productMapper::toDto);

        log.info("Retrieved {} products", productDtoPage.getSize());
        return productDtoPage;
    }

    @Override
    public List<Product> getAllProducts() {
        log.debug("Fetching all products...");
        List<Product> products = productRepository.findAll();
        log.info("Retrieved {} products", products.size());
        return products;
    }

    @Override
    public Product getProductById(Long id) {
        log.debug("Fetching product by ID: {}", id);
        Product product = findProductOrThrow(id);
        log.info("Retrieved product with ID: {}", id);
        return product;
    }

    @Override
    public Product createProduct(ProductRequest request) {
        log.debug("Creating product with request: {}", request);
        Product product = productMapper.toEntity(request);
        Product savedProduct = productRepository.save(product);
        log.info("Created product with ID: {}", savedProduct.getId());
        return savedProduct;
    }

    @Override
    public ProductDto updateProduct(Long id, ProductRequest productRequest) {
        log.debug("Updating product with ID: {}", id);
        ensureProductExists(id);

        Product product = productMapper.toEntity(productRequest);
        product.setId(id);

        Product savedProduct = productRepository.save(product);
        log.info("Updated product with ID: {}", id);
        return productMapper.toDto(savedProduct);
    }

    @Override
    public boolean deleteProduct(Long id) {
        log.debug("Deleting product with ID: {}", id);
        if(!productRepository.existsById(id)) {
            log.debug("Unable to delete nonexistent product(id={})", id);
            return false;
        }
        productRepository.deleteById(id);
        log.info("Deleted product with ID: {}", id);
        return true;
    }

    private Product findProductOrThrow(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No product with ID: " + id + " found."));
    }

    private void ensureProductExists(Long id) {
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException("No product with ID: " + id + " found.");
        }
    }
}
