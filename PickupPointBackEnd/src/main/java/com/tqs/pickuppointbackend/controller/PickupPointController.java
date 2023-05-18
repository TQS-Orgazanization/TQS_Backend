package com.tqs.pickuppointbackend.controller;


import lombok.extern.log4j.Log4j2;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tqs.pickuppointbackend.exceptions.ResourceNotFoundException;
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

    @GetMapping("/pickuppoint/{id}")
    public ResponseEntity<PickupPoint> getPickupScheduleById(@PathVariable(value = "id") long id) throws ResourceNotFoundException {

        return ResponseEntity.ok().body(pickupPointService.getPickupPointById(id));

    }

    @PostMapping("/pickuppoint")
    public ResponseEntity<PickupPoint> addPickupPoint(@Valid @RequestBody PickupPoint pickupPoint) throws ResourceNotFoundException {

        return ResponseEntity.ok().body(pickupPointService.addPickupPoint(pickupPoint));

    }

    @PutMapping("/pickuppoint/{id}")
    public ResponseEntity<PickupPoint> updatePickupPoint(@PathVariable(value = "id") Long id, @Valid @RequestBody PickupPoint pickupPointDetails) throws ResourceNotFoundException {
        return ResponseEntity.ok(pickupPointService.updatePickupPoint(pickupPointDetails));
    }

    @DeleteMapping("/pickuppoint/{id}")
    public ResponseEntity<PickupPoint> deletePickupPointById(@PathVariable(value = "id") long id) throws ResourceNotFoundException {

        return ResponseEntity.ok().body(pickupPointService.deletePickupPointById(id));
    
    }
}
