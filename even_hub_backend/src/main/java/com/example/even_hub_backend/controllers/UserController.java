package com.example.even_hub_backend.controllers;

import com.example.even_hub_backend.models.User;
import com.example.even_hub_backend.repository.UserRepository;
import com.example.even_hub_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

 
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'ROLE_ADMIN')")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User userDetails, Authentication authentication) {
       
        String currentUserName = authentication.getName();
        User currentUser = userRepository.findByUserName(currentUserName)
                .orElseThrow(() -> new RuntimeException("User not found"));

       
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equalsIgnoreCase("ADMIN") ||
                        a.getAuthority().equalsIgnoreCase("ROLE_ADMIN"));

      
        if (isAdmin || currentUser.getId().equals(id)) {
            User updatedUser = userService.updateUser(id, userDetails);
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Permission Denied!");
        }
    }

 
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id, Authentication authentication) {
        String currentUserName = authentication.getName();
        User currentUser = userRepository.findByUserName(currentUserName)
                .orElseThrow(() -> new RuntimeException("User not found"));

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equalsIgnoreCase("ADMIN") ||
                        a.getAuthority().equalsIgnoreCase("ROLE_ADMIN"));

      
        if (isAdmin || currentUser.getId().equals(id)) {
            userService.deleteUser(id);
            return ResponseEntity.ok("Removed Done!");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Permission Denied!");
        }
    }

 
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }
}