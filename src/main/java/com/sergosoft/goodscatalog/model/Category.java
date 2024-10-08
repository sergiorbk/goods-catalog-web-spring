package com.sergosoft.goodscatalog.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private Integer id;
    private String name;
    private String description;
    private Category parent;
    private List<Category> subCategories;
    private List<Product> products;
}
