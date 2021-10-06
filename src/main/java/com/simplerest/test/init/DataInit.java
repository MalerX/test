package com.simplerest.test.init;

import com.simplerest.test.model.entity.auth.Role;
import com.simplerest.test.model.entity.auth.User;
import com.simplerest.test.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;

//Инициализация БД. Так как БД inmemory, то метод инициализации бином самый простой в реализации.
@Component
@RequiredArgsConstructor
public class DataInit implements ApplicationRunner {
    private final UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("$2a$10$mYzYmDBVxfsUcAK9zUixE.0NLg9WWpPgLV5P1R.rU8gl2xnSEh5Ee");
        Role role = new Role();
        role.setRoleName("ROLE_USER");
        admin.setRoles(new HashSet<>(List.of(role)));

        User manager = new User();
        manager.setUsername("manager");
        manager.setPassword("$2a$10$mvRY4ui6tPBP2BDxQ1zXAe6euRfaDKpkCj4dArSzCzFUCeAAlAVNa");
        manager.setRoles(new HashSet<>(List.of(role)));

        userRepository.saveAll(List.of(admin, manager));
    }
}
