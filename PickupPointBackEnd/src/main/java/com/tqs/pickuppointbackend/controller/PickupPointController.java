package com.tqs.pickuppointbackend.controller;


import com.tqs.pickuppointbackend.constants.UserType;
import com.tqs.pickuppointbackend.controller.model.UpdateRequest;
import com.tqs.pickuppointbackend.service.AuthenticationService;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tqs.pickuppointbackend.exceptions.ResourceNotFoundException;
import com.tqs.pickuppointbackend.model.PickupPoint;
import com.tqs.pickuppointbackend.model.Dto.PickupPointDTO;
import com.tqs.pickuppointbackend.service.PickupPointService;

@RestController
@Log4j2
public class PickupPointController {

    @Autowired
    private PickupPointService pickupPointService;

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/pickuppoints")
    public ResponseEntity<List<PickupPoint>> getPickupPoints(@RequestHeader("Authorization") String token) throws ResourceNotFoundException {

        if (authenticationService.hasAcess(token, UserType.CLIENT)){
            return ResponseEntity.ok().body(pickupPointService.getPickupPoints());
        }
        throw new ResourceNotFoundException("The user dont have acess");

    }

    @GetMapping("/pickuppoint/{id}")
    public ResponseEntity<PickupPoint> getPickupScheduleById(@PathVariable(value = "id") long id, @RequestHeader("Authorization") String token) throws ResourceNotFoundException {

        if (authenticationService.hasAcess(token, UserType.ACP)){
            return ResponseEntity.ok().body(pickupPointService.getPickupPointById(id));
        }
        throw new ResourceNotFoundException("The user dont have acess");

    }

    @GetMapping("/pickuppoint/user/{user_id}")
    public ResponseEntity<PickupPoint> getPickupPointByuserId(@PathVariable(value = "user_id") long id, @RequestHeader("Authorization") String token) throws ResourceNotFoundException {

        if (authenticationService.hasAcess(token, UserType.ACP)){
            return ResponseEntity.ok().body(pickupPointService.getPickupPointByUserId(id));
        }
        throw new ResourceNotFoundException("The user don't have acess");

    }

    @GetMapping("/pickuppoints/available")
    public ResponseEntity<List<PickupPoint>> getPickupPointById(@RequestHeader("Authorization") String token) throws ResourceNotFoundException {

        return ResponseEntity.ok().body(pickupPointService.getAvailablePickupPoints());

    }

    @GetMapping("/pickuppoints/nonavailable")
    public ResponseEntity<List<PickupPoint>> getPickupPointNonAvailable(@RequestHeader("Authorization") String token) throws ResourceNotFoundException {
            return ResponseEntity.ok().body(pickupPointService.getNonAvailablePickupPoints());
    }

    @PostMapping("/pickuppoint")
    public ResponseEntity<PickupPoint> addPickupPoint(@Valid @RequestBody PickupPointDTO pickupPointDTO,  @RequestHeader("Authorization") String token) throws ResourceNotFoundException {

        if (authenticationService.hasAcess(token, UserType.ACP)){
            return ResponseEntity.ok().body(pickupPointService.addPickupPoint(pickupPointDTO));
        }
        throw new ResourceNotFoundException("The user dont have acess");
    }

    @PutMapping("/pickuppoint/{id}")
    public ResponseEntity<PickupPoint> updatePickupPoint(@PathVariable(value = "id") Long id, @Valid @RequestBody UpdateRequest request, @RequestHeader("Authorization") String token) throws ResourceNotFoundException {
        log.info(id);
        log.info(request);

        if (authenticationService.hasAcess(token, UserType.ACP)){
            return ResponseEntity.ok(pickupPointService.updatePickupPoint(id, request));
        }
        throw new ResourceNotFoundException("The user dont have acess");

    }

    @DeleteMapping("/pickuppoint/{id}")
    public ResponseEntity<PickupPoint> deletePickupPointById(@PathVariable(value = "id") Long id,  @RequestHeader("Authorization") String token) throws ResourceNotFoundException {

        if (authenticationService.hasAcess(token, UserType.ACP)){
            return ResponseEntity.ok().body(pickupPointService.deletePickupPointById(id));
        }
        throw new ResourceNotFoundException("The user dont have acess");

    }
    
}
