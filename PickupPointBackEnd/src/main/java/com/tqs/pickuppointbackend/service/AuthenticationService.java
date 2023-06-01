package com.tqs.pickuppointbackend.service;


import com.tqs.pickuppointbackend.constants.UserType;
import com.tqs.pickuppointbackend.controller.model.LoginResponse;
import com.tqs.pickuppointbackend.exceptions.ResourceNotFoundException;
import com.tqs.pickuppointbackend.model.User;
import com.tqs.pickuppointbackend.model.UserSession;
import com.tqs.pickuppointbackend.repository.SessionRepository;
import com.tqs.pickuppointbackend.repository.UserRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Optional;
import java.util.Random;
import java.util.Date;


@Service
public class AuthenticationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SessionRepository sessionRepository;

    @Transactional
    public LoginResponse login(String email, String password) {

        // Check if user exists with the given email
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return null;
        }

        // Check if the provided password matches the stored password
        if (!user.getPassword().equals(password)) {
            return null;
        }

        Random random = new Random();
        Date date = new Date();
        long millis = date.getTime();

        UserSession session = UserSession.builder()
                .token(generateRandomToken(15))
                .expirationDate(millis)
                .user(user)
                .build();

        sessionRepository.save(session);

        return session.toLoginRespose();
    }


    public UserSession validateToken(String token) throws ResourceNotFoundException {
        Optional<UserSession> session = sessionRepository.findByToken(token);

        if (session.isEmpty()){
            throw new ResourceNotFoundException("Invalid token");
        }
        return session.get();
    }

    public static String generateRandomToken(int length) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] tokenBytes = new byte[length];
        secureRandom.nextBytes(tokenBytes);
        String token = Base64.getUrlEncoder().withoutPadding().encodeToString(tokenBytes);
        return token.substring(0, length);
    }

    public boolean hasAcess(String token, UserType userType) throws ResourceNotFoundException {

        UserSession session = validateToken(token);
        User user = session.getUser();

        if (user.getUserType() == UserType.ADMIN){
            return true;
        }
        else{
            return user.getUserType() == userType;
        }

    }

}
