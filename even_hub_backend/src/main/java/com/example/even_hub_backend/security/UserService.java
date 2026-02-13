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

    // 1. අලුතින් එක් කළ saveUser Method එක (Registration සඳහා)
    public User saveUser(User user) {
        // Password එක Database එකේ save කරන්න කලින් අනිවාර්යයෙන් hash කරන්න
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // 2. සියලුම පරිශීලකයන් ලබා ගැනීම
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 3. ID එක අනුව පරිශීලකයෙකු සෙවීම
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // 4. පරිශීලකයෙකු මකා දැමීම
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // 5. පරිශීලක විස්තර යාවත්කාලීන කිරීම
    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUserName(userDetails.getUserName());
        user.setUserEmail(userDetails.getUserEmail());
        user.setRole(userDetails.getRole());

        // Password එක අලුතින් එවන්නේ නම් පමණක් hash කර update කරන්න
        if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        }

        return userRepository.save(user);
    }
}