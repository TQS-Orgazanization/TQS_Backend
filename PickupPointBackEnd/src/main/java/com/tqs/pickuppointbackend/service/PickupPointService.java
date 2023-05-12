package com.tqs.pickuppointbackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tqs.pickuppointbackend.exceptions.ResourceNotFoundException;
import com.tqs.pickuppointbackend.model.PickupPoint;
import com.tqs.pickuppointbackend.repository.PickupPointRepository;

@Service
public class PickupPointService {
    
    @Autowired
    PickupPointRepository pickupPointRepository;

    public List<PickupPoint> getPickupPoints() {
        return pickupPointRepository.findAll();
    }

    public PickupPoint getPickupPointById(long id) throws ResourceNotFoundException {
        return pickupPointRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pickup Point Not Found!"));
    }

    public PickupPoint addPickupPoint(PickupPoint pickupPoint) {
        return pickupPointRepository.save(pickupPoint);
    }
}
