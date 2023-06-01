package com.tqs.pickuppointbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tqs.pickuppointbackend.model.Statistics;
import com.tqs.pickuppointbackend.service.StatisticsService;

@RestController
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;
    
    @GetMapping("/statistics")
    public ResponseEntity<Statistics> getPickupPointNonAvailable() {

        return ResponseEntity.ok().body(statisticsService.getStats());

    }

}
