package com.tqs.pickuppointbackend.controller;

import com.tqs.pickuppointbackend.controller.model.LoginRequest;
import com.tqs.pickuppointbackend.controller.model.LoginResponse;
import com.tqs.pickuppointbackend.controller.model.UserResponse;
import com.tqs.pickuppointbackend.exceptions.ResourceNotFoundException;
import com.tqs.pickuppointbackend.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tqs.pickuppointbackend.model.User;
import com.tqs.pickuppointbackend.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class LoginController {

    private final UserRepository userRepository;

    @Autowired
    private AuthenticationService authenticationService;


    @Autowired
    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest request) throws ResourceNotFoundException {
        // Check if user exists with the given email

        LoginResponse token = authenticationService.login(request.getEmail(), request.getPassword());

        if (token != null){
            return ResponseEntity.status(HttpStatus.OK).body(token);
        }
        throw new ResourceNotFoundException("Invalid email or password");
        // Successful login
    }

    @GetMapping("/user")
    public ResponseEntity<UserResponse> getUserByToken(@RequestHeader("Authorization") String token) throws ResourceNotFoundException {
        // Check if user exists with the given email

        UserResponse user = authenticationService.getUserByToken(token);

        if (token != null){
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
        throw new ResourceNotFoundException("Invalid email or ");
        // Successful login
    }
}
