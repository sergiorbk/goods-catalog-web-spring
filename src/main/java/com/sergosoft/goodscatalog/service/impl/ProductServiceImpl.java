package com.sergosoft.goodscatalog.service.impl;

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
import com.sergosoft.goodscatalog.repository.impl.ProductRepositoryImpl;
import com.sergosoft.goodscatalog.service.ProductService;
import com.sergosoft.goodscatalog.dto.product.ProductFilter;
import com.sergosoft.goodscatalog.service.specification.ProductSpecification;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepositoryImpl productRepository;
    private final ProductMapper productMapper;

    @Override
    public Page<Product> getFilteredProductsByPage(int page, int pageSize, ProductFilter filter) {
        log.debug("Fetching filtered products on page {} (page size {})", page, pageSize);

        Specification<Product> specification = Specification
                .where(ProductSpecification.hasPriceBetween(filter.getMinPrice(), filter.getMaxPrice()))
                .and(ProductSpecification.refersTo(filter.getCategoryId()));

        Page<Product> productsPage = productRepository.findAll(specification, PageRequest.of(page, pageSize));
        log.info("Retrieved {} products", productsPage.getSize());
        return productsPage;
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
    public Product updateProduct(Long id, ProductRequest productRequest) {
        log.debug("Updating product with ID: {}", id);
        ensureProductExists(id);

        Product product = productMapper.toEntity(productRequest);
        product.setId(id);

        Product savedProduct = productRepository.save(product);
        log.info("Updated product with ID: {}", id);
        return savedProduct;
    }

    @Override
    public void deleteProduct(Long id) {
        log.debug("Deleting product with ID: {}", id);
        ensureProductExists(id);
        productRepository.deleteById(id);
        log.info("Deleted product with ID: {}", id);
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
