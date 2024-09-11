package com.sergosoft.goodscatalog;

import com.sergosoft.goodscatalog.model.Category;
import com.sergosoft.goodscatalog.model.Product;
import com.sergosoft.goodscatalog.model.user.UserEntity;
import com.sergosoft.goodscatalog.model.user.UserRole;
import com.sergosoft.goodscatalog.repository.CategoryRepository;
import com.sergosoft.goodscatalog.repository.ProductRepository;
import com.sergosoft.goodscatalog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class SampleDataLoader implements ApplicationRunner {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) {

        Category electronicsCategory = categoryRepository.saveCategory(new Category(
                null,
                "Electronics",
                "Sample description",
                null,
                new ArrayList<>(),
                new ArrayList<>()
        ));

        productRepository.saveProduct(new Product(
                null,
                "Fridge",
                "The coldest fridge in the world",
                123d,
                new ArrayList<>(),
                electronicsCategory
        ));

        UserEntity simpleUser = new UserEntity(
                null,
                "sergiorbk",
                passwordEncoder.encode("pass"),
                UserRole.USER
        );
        userRepository.save(simpleUser);

        UserEntity adminUser = new UserEntity(
                null,
                "admin",
                passwordEncoder.encode("root"),
                UserRole.ADMIN
        );
        userRepository.save(adminUser);
    }
}
