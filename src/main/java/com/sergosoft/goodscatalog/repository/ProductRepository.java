package com.sergosoft.goodscatalog.repository;

import com.sergosoft.goodscatalog.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository {

    List<Product> findAllProducts();
    Product findProductById(Long id);
    Product saveProduct(Product product);
    Product updateProduct(Long id, Product product);
    boolean deleteProduct(Long id);
}
