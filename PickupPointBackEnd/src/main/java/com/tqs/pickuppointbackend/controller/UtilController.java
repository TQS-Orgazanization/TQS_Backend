package com.tqs.pickuppointbackend.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Log4j2
public class UtilController {

    @GetMapping("/")
    public ResponseEntity<String> ping() {
        log.info("PING... PONG");
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }
}