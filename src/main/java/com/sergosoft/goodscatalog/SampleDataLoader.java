package com.sergosoft.goodscatalog;

import com.sergosoft.goodscatalog.model.Category;
import com.sergosoft.goodscatalog.model.Product;
import com.sergosoft.goodscatalog.repository.CategoryRepository;
import com.sergosoft.goodscatalog.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class SampleDataLoader implements ApplicationRunner {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        categoryRepository.saveCategory(new Category(
                "Electronics",
                "Sample description",
                null,
                new ArrayList<>(),
                new ArrayList<>()
        ));

        productRepository.saveProduct(new Product(
                "Fridge",
                "The coldest fridge in the world",
                123d,
                new ArrayList<>(),
                null
        ));
    }
}
