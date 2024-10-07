package com.sergosoft.goodscatalog.dto.product;

import jakarta.validation.constraints.Min;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ProductFilter {
    @Min(0)
    private Double minPrice = 0d;

    @Min(0)
    private Double maxPrice = Double.MAX_VALUE;

    @Min(0)
    private Integer categoryId;
}
