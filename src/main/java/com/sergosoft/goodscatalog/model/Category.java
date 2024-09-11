package com.sergosoft.goodscatalog.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Category {

    public Category(
            String name,
            String description,
            Category parent,
            List<Product> products,
            List<Category> subCategories
    ) {
        this.name = name;
        this.description = description;
        this.parent = parent;
        this.products = products;
        this.subCategories = subCategories;
    }

    private Long id;
    private String name;
    private String description;
    private Category parent;
    private List<Category> subCategories;
    private List<Product> products;
}
