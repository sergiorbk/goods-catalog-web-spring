package com.sergosoft.goodscatalog.mapper;

import com.sergosoft.goodscatalog.dto.product.ProductCreationRequest;
import com.sergosoft.goodscatalog.dto.product.ProductDto;
import com.sergosoft.goodscatalog.exception.EntityNotFoundException;
import com.sergosoft.goodscatalog.model.Category;
import com.sergosoft.goodscatalog.model.Product;
import com.sergosoft.goodscatalog.repository.CategoryRepository;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements Mapper<Product, ProductDto> {

    private final CategoryRepository categoryRepository;

    public ProductMapper(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Product toEntity(ProductDto productDto){
        if (productDto == null){
            return null;
        }

        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Category with ID: " + productDto.getCategoryId() + " not found."));

        return new Product(
                productDto.getId(),
                productDto.getName(),
                productDto.getDescription(),
                productDto.getPrice(),
                productDto.getImagesUrls(),
                category
        );
    }

    public Product toEntity(ProductCreationRequest creationRequest) {
        if(creationRequest == null) {
            return null;
        }

        Category category = categoryRepository.findById(creationRequest.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Category with ID: " + creationRequest.getCategoryId() + " not found."));

        return new Product(
                null,
                creationRequest.getName(),
                creationRequest.getDescription(),
                creationRequest.getPrice(),
                creationRequest.getImagesUrls(),
                category
        );
    }

    public ProductDto toDto(Product product){
        if (product == null){
            return null;
        }
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getImagesUrls(),
                product.getCategory().getId()
        );
    }
}
