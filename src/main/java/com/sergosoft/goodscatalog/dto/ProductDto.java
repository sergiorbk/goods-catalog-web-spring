package com.sergosoft.goodscatalog.dto;

import com.sergosoft.goodscatalog.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductDto {
    private String name;
    private String description;
    private Double price;
    private List<String> imagesUrls;
    private Category category;
}
