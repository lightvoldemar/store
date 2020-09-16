package com.pillikan.store.db;

import com.pillikan.store.model.Category;
import com.pillikan.store.model.Product;
import com.pillikan.store.model.Role;
import com.pillikan.store.model.User;
import com.pillikan.store.repository.CategoryRepository;
import com.pillikan.store.repository.ProductRepository;
import com.pillikan.store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DbInit implements CommandLineRunner {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {
        Category footwear = new Category("footwear");
        Category headgear = new Category("headgear");

        categoryRepository.create(footwear);
        categoryRepository.create(headgear);

        Product shoes = new Product("shoes", 500, footwear);
        Product boots = new Product("boots", 1000, footwear);
        Product cap = new Product("cap", 300, headgear);

        productRepository.create(shoes);
        productRepository.create(boots);
        productRepository.create(cap);

        User user = new User("User", "user@mail.ru", encoder.encode("123456"), Role.ROLE_USER);
        User admin = new User("Admin", "admin@mail.ru", encoder.encode("123456"), Role.ROLE_USER, Role.ROLE_ADMIN);

        userRepository.create(user);
        userRepository.create(admin);
    }
}
