package com.sergosoft.goodscatalog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Data
public class CategoryCreationRequest {

    @NotBlank(message = "Name is mandatory")
    @Length(max = 255, message = "Max length for name is 255.")
    private String name;

    @NotBlank(message = "Description name is mandatory.")
    @Length(max = 255, message = "Max length for description is 255.")
    private String description;

    @NotNull
    private Long parentCategoryId;

    @NotNull
    private List<@NotNull Long> subCategoriesIds;

    @NotNull
    private List<@NotNull Long> productsIds;
}
