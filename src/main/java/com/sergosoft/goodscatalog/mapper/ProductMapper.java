package com.sergosoft.goodscatalog.mapper;

import com.sergosoft.goodscatalog.dto.ProductDto;
import com.sergosoft.goodscatalog.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toEntity(ProductDto productDto){
        if (productDto == null){
            return null;
        }
        return Product.builder()
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .imagesUrls(productDto.getImagesUrls())
                .category(productDto.getCategory())
                .build();
    }

    public ProductDto toDto(Product product){
        if (product == null){
            return null;
        }
        return ProductDto.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(product.getCategory())
                .imagesUrls(product.getImagesUrls())
                .build();
    }
}
