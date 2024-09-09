package com.sergosoft.goodscatalog.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Product {
    private Long id;
    private String name;
    private Double price;
    private String description;
    private List<String> imgURL;
    private Category category;
}
