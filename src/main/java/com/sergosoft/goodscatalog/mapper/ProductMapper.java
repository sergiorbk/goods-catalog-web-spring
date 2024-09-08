package com.sergosoft.goodscatalog.mapper;

import com.sergosoft.goodscatalog.dto.ProductDTO;
import com.sergosoft.goodscatalog.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements Mapper<Product, ProductDTO> {

    @Override
    public ProductDTO toDto(Product product) {
        // ToDo
        return null;
    }

    @Override
    public Product toEntity(ProductDTO productDTO) {
        // ToDo
        return null;
    }
}
