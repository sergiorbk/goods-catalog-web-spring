package com.sergosoft.goodscatalog.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Category {
    private Long id;
    private String name;
    private String description;
    private Category parent;
    private List<Category> subCategories;
    private List<Product> products;
}
