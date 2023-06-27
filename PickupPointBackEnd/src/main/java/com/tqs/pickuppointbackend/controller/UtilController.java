package com.tqs.pickuppointbackend.controller;

import com.tqs.pickuppointbackend.service.PopulateService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Log4j2
public class UtilController {

    @Autowired
    PopulateService populateService;

    @GetMapping("/")
    public ResponseEntity<String> ping() {
        log.info("PING... PONG");
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<String> populate() {
        log.info("Create User");

        //populateService.populateDatabase();
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }


}