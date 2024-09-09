package com.sergosoft.goodscatalog.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Category {

    public Category(String name, String description, Category parent, List<Category> subCategories, List<Product> products) {
        this.name = name;
        this.description = description;
        this.parent = parent;
        this.subCategories = subCategories;
        this.products = products;
    }

    private Long id;
    private String name;
    private String description;
    private Category parent;
    private List<Category> subCategories;
    private List<Product> products;
}
