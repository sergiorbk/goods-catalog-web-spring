package com.sergosoft.goodscatalog.controller.rest;

import com.sergosoft.goodscatalog.dto.product.ProductDto;
import com.sergosoft.goodscatalog.dto.product.ProductFilter;
import com.sergosoft.goodscatalog.dto.product.ProductRequest;
import com.sergosoft.goodscatalog.mapper.ProductMapper;
import com.sergosoft.goodscatalog.model.Product;
import com.sergosoft.goodscatalog.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Slf4j
public class ProductRestController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<ProductDto>>> getProductFilteredProductsByPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            ProductFilter productFilter,
            PagedResourcesAssembler<ProductDto> pagedResourcesAssembler) {

        log.info("Received request to show all products");
        Page<ProductDto> productsDtoPage = productService.getFilteredProductsByPage(page, pageSize, productFilter);
        PagedModel<EntityModel<ProductDto>> pagedModel = pagedResourcesAssembler.toModel(productsDtoPage);

        log.info("Returning products (page {}, pageSize = {})", page, pageSize);
        return ResponseEntity.ok(pagedModel);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long productId) {
        log.info("Received request to get product with ID: {}", productId);
        Product product = productService.getProductById(productId);
        ProductDto productDto = productMapper.toDto(product);
        log.info("Returning product with ID: {}", productId);
        return ResponseEntity.ok(productDto);
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
        ProductDto updatedProduct = productService.updateProduct(productId, productRequest);
        log.info("Product updated with ID: {}", productId);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/moderate/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        log.info("Received request to delete product with ID: {}", productId);
        boolean isDeleted = productService.deleteProduct(productId);
        if(isDeleted) {
            log.info("Product deleted with ID: {}", productId);
            return ResponseEntity.noContent().build();
        } else {
            log.info("Product(id={}) was not deleted: no such product found.", productId);
            return ResponseEntity.notFound().build();
        }
    }
}