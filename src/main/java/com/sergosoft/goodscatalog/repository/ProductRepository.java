package com.sergosoft.goodscatalog.repository;

import com.sergosoft.goodscatalog.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> { }
