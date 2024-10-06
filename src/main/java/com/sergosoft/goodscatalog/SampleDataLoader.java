package com.sergosoft.goodscatalog;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import com.sergosoft.goodscatalog.dto.category.CategoryCreationRequest;
import com.sergosoft.goodscatalog.dto.product.ProductRequest;
import com.sergosoft.goodscatalog.dto.user.UserRegisterRequest;
import com.sergosoft.goodscatalog.model.Category;
import com.sergosoft.goodscatalog.model.Product;
import com.sergosoft.goodscatalog.model.user.UserEntity;
import com.sergosoft.goodscatalog.model.user.UserRole;
import com.sergosoft.goodscatalog.service.CategoryService;
import com.sergosoft.goodscatalog.service.ProductService;
import com.sergosoft.goodscatalog.service.UserService;

@Component
@RequiredArgsConstructor
public class SampleDataLoader implements ApplicationRunner {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final UserService userService;

    @Override
    public void run(ApplicationArguments args) {
        //
        // Categories
        //
        Category electronicsCategory = categoryService.addCategory(new CategoryCreationRequest(
                "Electronics",
                "Sample description for category Electronics",
                null,
                new ArrayList<>(),
                new ArrayList<>()
        ));

        Category computersCategory = categoryService.addCategory(new CategoryCreationRequest(
                "Computers",
                "Sample description for category Computers",
                electronicsCategory.getId(),
                new ArrayList<>(),
                new ArrayList<>()
        ));

        Category laptopsCategory = categoryService.addCategory(new CategoryCreationRequest(
                "Laptops",
                "Sample description for category Laptops",
                computersCategory.getId(),
                new ArrayList<>(),
                new ArrayList<>()
        ));


        //
        // Products (All laptops)
        //
        Product exampleLaptop1 = productService.createProduct(new ProductRequest(
                "HP Victus 16 r0014ua",
                "Powerful laptop with 32GB DDR5 RAM, I5 13500H, RTX4050",
                35999D,
                List.of("https://content.rozetka.com.ua/goods/images/big/14840350.jpg"),
                laptopsCategory.getId()
        ));

        Product exampleLaptop2 = productService.createProduct(new ProductRequest(
                "HP ProBook 4540s",
                "Old school laptop with 16GB DDR3 RAM, I7 3632QM and mobile graphics by AMD",
                11999D,
                List.of("https://content.rozetka.com.ua/goods/images/big/14840350.jpg"),
                laptopsCategory.getId()
        ));

        //
        // Users
        //
        userService.registerUser(new UserRegisterRequest(
                "user",
                "123123123Aa$"
        ));

        UserEntity admin = userService.registerUser(new UserRegisterRequest(
                "admin",
                "123123123Aa$"
        ));

        userService.changeRole(admin.getId(), UserRole.ADMIN);
    }
}
