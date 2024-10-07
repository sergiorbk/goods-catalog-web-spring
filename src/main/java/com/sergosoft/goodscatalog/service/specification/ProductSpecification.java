package com.sergosoft.goodscatalog.service.specification;

import com.sergosoft.goodscatalog.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

@Slf4j
public class ProductSpecification {

    public static Specification<Product> hasPriceBetween(Double minPrice, Double maxPrice) {
        log.debug("Filtering products which have a price between {} and {}", minPrice, maxPrice);
        return (root, query, builder) -> {
            if(minPrice == null || maxPrice == null || minPrice > maxPrice) {
                log.debug("Filtering products by price range was interrupted due to the incorrect input data.");
                log.debug("Filtering returned an empty products list.");
                return builder.conjunction();
            }
            return builder.between(root.get("price"), minPrice, maxPrice);
        };
    }

    public static Specification<Product> refersTo(Integer categoryId) {
        return (root, query, criteriaBuilder) -> {
            if (categoryId == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("category").get("id"), categoryId);
        };
    }
}
