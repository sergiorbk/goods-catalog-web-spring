package com.sergosoft.goodscatalog.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Product {

    public Product(String name, String description, Double price, List<String> imagesUrls, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imagesUrls = imagesUrls;
        this.category = category;
    }

    private Long id;
    private String name;
    private String description;
    private Double price;
    private List<String> imagesUrls;
    private Category category;
}
