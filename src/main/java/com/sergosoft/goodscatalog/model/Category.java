package com.sergosoft.goodscatalog.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Category {

    private Long id;
    private String name;
    private String description;
    private Category parent;
    private List<Category> subCategories;
    private List<Product> products;
}
