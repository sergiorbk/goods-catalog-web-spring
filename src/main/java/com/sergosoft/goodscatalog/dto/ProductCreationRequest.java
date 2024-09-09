package com.sergosoft.goodscatalog.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import java.util.List;

@Data
public class ProductCreationRequest {

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
    private Long categoryId;
}
