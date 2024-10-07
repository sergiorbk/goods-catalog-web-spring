package com.sergosoft.goodscatalog.service.specification;

import com.sergosoft.goodscatalog.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

@Slf4j
public class ProductSpecification {

    public static Specification<Product> hasPriceBetween(Double minPrice, Double maxPrice) {
        log.debug("Filtering products with a price between {} and {}", minPrice, maxPrice);

        return (root, query, builder) -> {
            if (minPrice > maxPrice) {
                log.debug("Invalid price range: minPrice is greater than maxPrice.");
                return builder.disjunction();
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
