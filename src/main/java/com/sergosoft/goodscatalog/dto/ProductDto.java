package com.sergosoft.goodscatalog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProductDto {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private List<String> imagesUrls;
    private Long categoryId;
}
