package com.sergosoft.goodscatalog.dto.category;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryCreationRequest {

    @NotBlank(message = "Name is mandatory")
    @Length(max = 255, message = "Max length for name is 255.")
    private String name;

    @NotBlank(message = "Description name is mandatory.")
    @Length(max = 255, message = "Max length for description is 255.")
    private String description;

    private Integer parentCategoryId;

    @NotNull
    private List<@NotNull Long> subCategoriesIds;

    @NotNull
    private List<@NotNull Long> productsIds;
}
