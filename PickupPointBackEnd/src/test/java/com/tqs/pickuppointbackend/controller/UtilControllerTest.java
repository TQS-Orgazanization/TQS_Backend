package com.tqs.pickuppointbackend.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UtilControllerTest {

    @Test
    public void testPing() {
        UtilController utilController = new UtilController();

        ResponseEntity<String> response = utilController.ping();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("OK", response.getBody());
    }
}
