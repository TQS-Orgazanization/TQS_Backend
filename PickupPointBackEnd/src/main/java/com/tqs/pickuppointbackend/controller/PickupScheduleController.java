package com.tqs.pickuppointbackend.controller;


import com.tqs.pickuppointbackend.constants.UserType;
import com.tqs.pickuppointbackend.service.AuthenticationService;
import lombok.extern.log4j.Log4j2;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tqs.pickuppointbackend.exceptions.ResourceNotFoundException;
import com.tqs.pickuppointbackend.model.PickupSchedule;
import com.tqs.pickuppointbackend.model.Dto.PickupScheduleDTO;
import com.tqs.pickuppointbackend.service.PickupScheduleService;

@RestController
@Log4j2
public class PickupScheduleController {

    @Autowired
    PickupScheduleService pickupScheduleService;

    @Autowired
    private AuthenticationService authenticationService;


    @GetMapping("/pickupschedules")
    public ResponseEntity<List<PickupSchedule>> getPickupSchedules(@RequestHeader("Authorization") String token) throws ResourceNotFoundException {

        if (authenticationService.hasAcess(token, UserType.ACP)){
            return ResponseEntity.ok().body(pickupScheduleService.getPickupSchedules());
        }
        throw new ResourceNotFoundException("The user dont have acess");

    }

    @GetMapping("/pickupschedules/user/{id}")
    public ResponseEntity<List<PickupSchedule>> getPickupSchedulesByuserId(@PathVariable(value = "id") long id, @RequestHeader("Authorization") String token) throws ResourceNotFoundException {

        if (authenticationService.hasAcess(token, UserType.CLIENT)){
            return ResponseEntity.ok().body(pickupScheduleService.getPickupScheduleUserById(id));
        }
        throw new ResourceNotFoundException("The user dont have acess");

    }

    @GetMapping("/pickupschedule/{id}")
    public ResponseEntity<PickupSchedule> getPickupScheduleById(@PathVariable(value = "id") long id,  @RequestHeader("Authorization") String token) throws ResourceNotFoundException {

        if (authenticationService.hasAcess(token, UserType.ACP)){
            return ResponseEntity.ok().body(pickupScheduleService.getPickupScheduleById(id));
        }
        throw new ResourceNotFoundException("The user dont have acess");

    }

    @PostMapping("/pickupschedule")
    public ResponseEntity<PickupSchedule> addPickupSchedule(@RequestBody PickupScheduleDTO pickupScheduleDTO, @RequestHeader("Authorization") String token) throws ResourceNotFoundException, NoSuchAlgorithmException {
        log.info("Start addPickupSchedule: " + pickupScheduleDTO);

        if (authenticationService.hasAcess(token, UserType.CLIENT)){
            return ResponseEntity.ok().body(pickupScheduleService.addPickupSchedule(pickupScheduleDTO));
        }
        throw new ResourceNotFoundException("The user dont have acess");

    }

    @GetMapping("/pickupschedules/available")
    public ResponseEntity<List<PickupSchedule>> getPickupScheduleByAvailabilty(@RequestHeader("Authorization") String token) throws ResourceNotFoundException {

        if (authenticationService.hasAcess(token, UserType.ACP)){
            return ResponseEntity.ok().body(pickupScheduleService.getAvailablePickupSchedules());
        }
        throw new ResourceNotFoundException("The user dont have acess");

    }

    @GetMapping("/pickupschedules/available/{id}")
    public ResponseEntity<List<PickupSchedule>> getPickupScheduleByAvailabiltyByPickupPointId(@PathVariable(value = "id") long id, @RequestHeader("Authorization") String token) throws ResourceNotFoundException {

        if (authenticationService.hasAcess(token, UserType.ACP)){
            return ResponseEntity.ok().body(pickupScheduleService.getAvailablePickupSchedulesByPickupPointId(id));
        }
        throw new ResourceNotFoundException("The user dont have acess");

    }

    @GetMapping("/pickupschedules/nonavailable/{id}")
    public ResponseEntity<List<PickupSchedule>> getPickupScheduleByNonAvailabiltyByPickupPointId(@PathVariable(value = "id") long id, @RequestHeader("Authorization") String token) throws ResourceNotFoundException {

        if (authenticationService.hasAcess(token, UserType.ACP)){
            return ResponseEntity.ok().body(pickupScheduleService.getNonAvailablePickupSchedulesByPickupPointId(id));
        }
        throw new ResourceNotFoundException("The user dont have acess");

    }

    @GetMapping("/pickupschedules/nonavailable")
    public ResponseEntity<List<PickupSchedule>> getPickupScheduleByNonAvailabilty(@RequestHeader("Authorization") String token) throws ResourceNotFoundException {
        if (authenticationService.hasAcess(token, UserType.ACP)){
            return ResponseEntity.ok().body(pickupScheduleService.getNonAvailablePickupSchedules());
        }
        throw new ResourceNotFoundException("The user dont have acess");

    }

    @PutMapping("/pickupschedule/{id}")
    public ResponseEntity<PickupSchedule> updatePickupSchedule(@PathVariable(value = "id") Long id, @Valid @RequestBody PickupScheduleDTO pickupScheduleDTO, @RequestHeader("Authorization") String token) throws ResourceNotFoundException {
        if (authenticationService.hasAcess(token, UserType.ACP)){
            return ResponseEntity.ok(pickupScheduleService.updatePickupSchedule(pickupScheduleDTO));
        }
        throw new ResourceNotFoundException("The user dont have acess");
    }

    @DeleteMapping("/pickupschedule/{id}")
    public ResponseEntity<PickupSchedule> deletePickupScheduleById(@PathVariable(value = "id") long id, @RequestHeader("Authorization") String token) throws ResourceNotFoundException {
        if (authenticationService.hasAcess(token, UserType.ACP)){
            return ResponseEntity.ok().body(pickupScheduleService.deletePickupScheduleById(id));
        }
        throw new ResourceNotFoundException("The user dont have acess");

    }



}
