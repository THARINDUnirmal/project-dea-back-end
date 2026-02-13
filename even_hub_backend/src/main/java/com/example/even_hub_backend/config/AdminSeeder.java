package com.example.even_hub_backend.config;

import com.example.even_hub_backend.models.User;
import com.example.even_hub_backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminSeeder {

    @Bean
    CommandLineRunner initAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            
            if (userRepository.findByUserEmail("admin@gmail.com").isEmpty()) {

                User admin = new User();
                admin.setUserName("System Admin");
                admin.setUserEmail("admin@gmail.com");

               
                admin.setPassword(passwordEncoder.encode("admin123"));

                admin.setRole("ADMIN");

                userRepository.save(admin);
                System.out.println(" Admin user created successfully!");
            } else {
                System.out.println("Admin user already exists.");
            }
        };
    }
}