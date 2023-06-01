package com.tqs.pickuppointbackend.controller;

import com.tqs.pickuppointbackend.controller.model.LoginResponse;
import com.tqs.pickuppointbackend.exceptions.ResourceNotFoundException;
import com.tqs.pickuppointbackend.service.AuthenticationService;
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
    private AuthenticationService authenticationService;


    @Autowired
    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<String> loginUser(@RequestParam String email, @RequestParam String password) throws ResourceNotFoundException {
        // Check if user exists with the given email

        LoginResponse token = authenticationService.login(email, password);

        if (token != null){
            return ResponseEntity.status(HttpStatus.OK).body(token.getAccessToken());
        }
        throw new ResourceNotFoundException("Invalid email or password");
        // Successful login
    }
}
