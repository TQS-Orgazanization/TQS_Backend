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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tqs.pickuppointbackend.exceptions.ResourceNotFoundException;
import com.tqs.pickuppointbackend.model.PickupSchedule;
import com.tqs.pickuppointbackend.model.Dto.PickupScheduleDTO;
import com.tqs.pickuppointbackend.service.PickupScheduleService;

@RestController
@Log4j2
public class PickupScheduleController {

    @Autowired
    PickupScheduleService pickupScheduleService;

    @GetMapping("/pickupschedules")
    public ResponseEntity<List<PickupSchedule>> getPickupSchedules(){

        return ResponseEntity.ok().body(pickupScheduleService.getPickupSchedules());

    }

    @GetMapping("/pickupschedule/{id}")
    public ResponseEntity<PickupSchedule> getPickupScheduleById(@PathVariable(value = "id") long id) throws ResourceNotFoundException {

        return ResponseEntity.ok().body(pickupScheduleService.getPickupScheduleById(id));

    }

    @PostMapping("/pickupschedule")
    public ResponseEntity<PickupSchedule> addPickupSchedule(@RequestBody PickupScheduleDTO pickupScheduleDTO) throws ResourceNotFoundException {

        return ResponseEntity.ok().body(pickupScheduleService.addPickupSchedule(pickupScheduleDTO));
    
    }

    @DeleteMapping("/pickupschedule/{id}")
    public ResponseEntity<PickupSchedule> deletePickupScheduleById(@PathVariable(value = "id") long id) throws ResourceNotFoundException {

        return ResponseEntity.ok().body(pickupScheduleService.deletePickupScheduleById(id));
    
    }



}
