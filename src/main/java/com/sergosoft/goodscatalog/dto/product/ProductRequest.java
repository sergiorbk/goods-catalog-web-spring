package com.sergosoft.goodscatalog.dto.product;

import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    @NotBlank(message = "Name is mandatory.")
    @Length(max = 255, message = "Max length for name is 255.")
    private String name;

    @NotBlank(message = "Description name is mandatory.")
    @Length(max = 255, message = "Max length for description is 255.")
    private String description;

    @NotNull
    @Positive(message = "Price must be greater than 0.")
    private Double price;

    @NotEmpty(message = "You must attach at least 1 image URL.")
    @Size(max = 5, message = "You are supposed to attach maximum 5 image URLs.")
    private List<
            @URL(message = "Invalid URL format of an image link.")
            @Length(max = 255, message = "Max length for image URL is 255.") String> imagesUrls;

    @NotNull
    private Integer categoryId;
}
