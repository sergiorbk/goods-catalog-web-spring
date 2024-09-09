package com.sergosoft.goodscatalog;

import com.sergosoft.goodscatalog.model.Category;
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
        Category electronicsCategory = categoryRepository.saveCategory(new Category(
                1L,
                "Electronics",
                "Sample description",
                null,
                new ArrayList<>(),
                new ArrayList<>()
        ));

//        productRepository.saveProduct(new Product(
//                12L,
//                "Fridge",
//                "The coldest fridge in the world",
//                123d,
//                new ArrayList<>(),
//                electronicsCategory
//        ));
    }
}
