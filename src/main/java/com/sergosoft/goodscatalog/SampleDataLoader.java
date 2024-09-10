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
import java.util.Collections;
import java.util.List;

@Component
public class SampleDataLoader implements ApplicationRunner {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Category subCategory = new Category(
                12L,
                "Bob",
                "Dorosliy",
                null,
                null,
                null
        );

        Product product = new Product(
                "Phone",
                "Good",
                123D,
                new ArrayList<String>(){
                    {add("http:/bodka"); add("http:/serhii"); add("http:/bober");}
                },
                null
        );

        Category electronicsCategory = categoryRepository.saveCategory(new Category(
                1L,
                "Electronics",
                "Sample description",
                subCategory,
                new ArrayList<>(Collections.singletonList(subCategory)),
                new ArrayList<>(Collections.singletonList(product))
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
