package com.example.even_hub_backend.services;

import com.example.even_hub_backend.models.User;
import com.example.even_hub_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

 
    public User saveUser(User user) {
      
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

 
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

   
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

 
    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUserName(userDetails.getUserName());
        user.setUserEmail(userDetails.getUserEmail());
        user.setRole(userDetails.getRole());

      
        if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        }

        return userRepository.save(user);
    }
}