package com.sergosoft.goodscatalog;

import com.sergosoft.goodscatalog.dto.category.CategoryCreationRequest;
import com.sergosoft.goodscatalog.dto.product.ProductCreationRequest;
import com.sergosoft.goodscatalog.dto.user.UserRegisterRequest;
import com.sergosoft.goodscatalog.model.Category;
import com.sergosoft.goodscatalog.model.user.UserEntity;
import com.sergosoft.goodscatalog.model.user.UserRole;
import com.sergosoft.goodscatalog.service.CategoryService;
import com.sergosoft.goodscatalog.service.ProductService;
import com.sergosoft.goodscatalog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SampleDataLoader implements ApplicationRunner {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final UserService userService;

    @Override
    public void run(ApplicationArguments args) {
        Category electronicsCategory = categoryService.addCategory(new CategoryCreationRequest(
                "Electronics",
                "Sample description for category Electronics",
                null,
                new ArrayList<>(),
                new ArrayList<>()
        ));



        productService.createProduct(new ProductCreationRequest(
                "Fridge",
                "The coldest fridge in the world",
                10999D,
                List.of("https://content.rozetka.com.ua/goods/images/big/14840350.jpg"),
                electronicsCategory.getId()
        ));

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
