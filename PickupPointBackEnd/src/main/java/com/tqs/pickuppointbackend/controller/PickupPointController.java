package com.tqs.pickuppointbackend.controller;


import lombok.extern.log4j.Log4j2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tqs.pickuppointbackend.model.PickupPoint;
import com.tqs.pickuppointbackend.service.PickupPointService;

@RestController
@Log4j2
public class PickupPointController {

    @Autowired
    PickupPointService pickupPointService;

    @GetMapping("/pickuppoints")
    public ResponseEntity<List<PickupPoint>> getPickupPoints(){

        return ResponseEntity.ok().body(pickupPointService.getPickupPoints());

    }

}
