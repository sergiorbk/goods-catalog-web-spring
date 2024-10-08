package com.sergosoft.goodscatalog.controller.rest;

import java.util.List;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sergosoft.goodscatalog.dto.product.ProductDto;
import com.sergosoft.goodscatalog.dto.product.ProductRequest;
import com.sergosoft.goodscatalog.mapper.ProductMapper;
import com.sergosoft.goodscatalog.model.Product;
import com.sergosoft.goodscatalog.service.ProductService;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Slf4j
public class ProductRestController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long productId) {
        log.info("Received request to get product with ID: {}", productId);
        Product product = productService.getProductById(productId);
        ProductDto productDto = productMapper.toDto(product);
        log.info("Returning product with ID: {}", productId);
        return ResponseEntity.ok(productDto);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        log.info("Received request to show all products.");
        List<Product> products = productService.getAllProducts();
        List<ProductDto> productsDtoList = products.stream().map(productMapper::toDto).toList();
        log.info("Returning all products.");
        return ResponseEntity.ok(productsDtoList);
    }

    @PostMapping("/moderate")
    public ResponseEntity<ProductDto> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        log.info("Received request to create a new product");
        Product createdProduct = productService.createProduct(productRequest);
        ProductDto createdProductDto = productMapper.toDto(createdProduct);
        log.info("Returning created product");
        return new ResponseEntity<>(createdProductDto, HttpStatus.CREATED);
    }

    @PutMapping("/moderate/{productId}")
    public ResponseEntity<ProductDto> updateProduct(
            @PathVariable Long productId,
            @RequestBody @Valid ProductRequest productRequest
    ) {
        log.info("Received request to update product with ID: {}", productId);
        Product updatedProduct = productService.updateProduct(productId, productRequest);
        log.info("Product updated with ID: {}", productId);
        return ResponseEntity.ok(productMapper.toDto(updatedProduct));
    }

    @DeleteMapping("/moderate/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        log.info("Received request to delete product with ID: {}", productId);
        productService.deleteProduct(productId);
        log.info("Product deleted with ID: {}", productId);
        return ResponseEntity.noContent().build();
    }
}