package com.tqs.pickuppointbackend.controller;

import com.tqs.pickuppointbackend.model.User;
import com.tqs.pickuppointbackend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RegisterControllerTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private RegisterController registerController;

    public RegisterControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterUser_Success() {
        User user = new User();
        user.setEmail("test@example.com");

        when(userRepository.existsByEmail(user.getEmail())).thenReturn(false);

        ResponseEntity<String> response = registerController.registerUser(user);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("User registered successfully!", response.getBody());

        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testRegisterUser_EmailAlreadyExists() {
        User user = new User();
        user.setEmail("test@example.com");

        when(userRepository.existsByEmail(user.getEmail())).thenReturn(true);

        ResponseEntity<String> response = registerController.registerUser(user);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Email already exists. Registration denied.", response.getBody());

        verify(userRepository, never()).save(user);
    }
}
