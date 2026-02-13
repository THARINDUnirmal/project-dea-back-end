package com.example.even_hub_backend.controllers;


import com.example.even_hub_backend.dto.JwtResponse;
import com.example.even_hub_backend.dto.LoginRequest;
import com.example.even_hub_backend.dto.SignupRequest;
import com.example.even_hub_backend.models.User;
import com.example.even_hub_backend.repository.UserRepository;
import com.example.even_hub_backend.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") 
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

   
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
        if (userRepository.findByUserName(signUpRequest.getUserName()).isPresent()) {
            return ResponseEntity.badRequest().body("Error: Username is already taken!");
        }

      
        if (userRepository.findByUserEmail(signUpRequest.getUserEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }

        User user = new User();
        user.setUserName(signUpRequest.getUserName());
        user.setUserEmail(signUpRequest.getUserEmail());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));

       
        user.setRole(signUpRequest.getRole() == null ? "USER" : signUpRequest.getRole());

        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully!");
    }

   
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

     
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

     
        org.springframework.security.core.userdetails.User userDetails =
                (org.springframework.security.core.userdetails.User) authentication.getPrincipal();

       
        String jwt = jwtUtils.generateTokenFromUsername(userDetails.getUsername());

      
        com.example.even_hub_backend.models.User user = userRepository.findByUserName(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User Not Found"));

       
        return ResponseEntity.ok(new JwtResponse(
                jwt,
                user.getId(),
                user.getUserName(),
                user.getUserEmail(),
                user.getRole()
        ));
    }
}
