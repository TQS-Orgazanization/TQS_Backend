package com.tqs.pickuppointbackend.controller;
import com.tqs.pickuppointbackend.controller.model.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tqs.pickuppointbackend.model.User;
import com.tqs.pickuppointbackend.repository.UserRepository;

@RestController
@RequestMapping("/api/register")
public class RegisterController {

    private final UserRepository userRepository;

    @Autowired
    public RegisterController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequest registerRequest) {

        // Check if email already exists
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exists. Registration denied.");
        }

        userRepository.save(
                User.builder()
                        .email(registerRequest.getEmail())
                        .password(registerRequest.getPassword())
                        .userType(registerRequest.getUserType())
                        .build()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully!");
    }
}
