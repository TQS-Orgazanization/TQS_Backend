package com.tqs.pickuppointbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tqs.pickuppointbackend.model.User;
import com.tqs.pickuppointbackend.repository.UserRepository;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    private final UserRepository userRepository;

    @Autowired
    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<String> loginUser(@RequestParam String email, @RequestParam String password) {
        // Check if user exists with the given email
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }

        // Check if the provided password matches the stored password
        if (!user.getPassword().equals(password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }

        // Successful login
        return ResponseEntity.status(HttpStatus.OK).body("Login successful!");
    }
}
