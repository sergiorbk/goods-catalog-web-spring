package com.sergosoft.goodscatalog.dto.category;

import com.sergosoft.goodscatalog.model.Category;
import com.sergosoft.goodscatalog.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CategoryDto {

    private Integer id;
    private String name;
    private String description;
    private Integer parentId;
    private List<Category> subCategories;
    private List<Product> products;
}
