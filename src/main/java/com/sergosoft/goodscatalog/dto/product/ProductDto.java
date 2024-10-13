package com.sergosoft.goodscatalog.dto.product;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private List<String> imagesUrls;
    private Integer categoryId;
}
