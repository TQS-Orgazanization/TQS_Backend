package com.tqs.pickuppointbackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tqs.pickuppointbackend.exceptions.ResourceNotFoundException;
import com.tqs.pickuppointbackend.model.PickupPoint;
import com.tqs.pickuppointbackend.model.PickupSchedule;
import com.tqs.pickuppointbackend.model.User;
import com.tqs.pickuppointbackend.repository.PickupPointRepository;
import com.tqs.pickuppointbackend.repository.PickupScheduleRepository;
import com.tqs.pickuppointbackend.repository.UserRepository;
import com.tqs.pickuppointbackend.service.PickupScheduleDto;

@Service
public class PickupScheduleService {
    
    @Autowired
    PickupScheduleRepository pickupScheduleRepository;

    @Autowired
    PickupPointRepository pickupPointRepository;

    @Autowired
    UserRepository userRepository;

    public List<PickupSchedule> getPickupSchedules() {
        return pickupScheduleRepository.findAll();
    }

    public PickupSchedule getPickupScheduleById(long id) throws ResourceNotFoundException {
        return pickupScheduleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pickup Schedule Not Found!"));
    }

    public PickupSchedule addPickupSchedule(PickupScheduleDto pickupScheduleDto) throws ResourceNotFoundException{

        Long pickupPointId = pickupScheduleDto.getPickupPointId();
        Long userId = pickupScheduleDto.getUserId();
        Long code = pickupScheduleDto.getCode();
        Long start_time = pickupScheduleDto.getStartTime();
        Long end_time = pickupScheduleDto.getEndTime();


        
        // Retrieve the PickupPoint and User objects from the database using the IDs
        PickupPoint pickupPoint = pickupPointRepository.findById(pickupPointId).orElseThrow(() -> new ResourceNotFoundException("Pickup Point not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));


        PickupSchedule pickupSchedule = new PickupSchedule();
        pickupSchedule.setPickupPoint(pickupPoint);
        pickupSchedule.setUser(user);
        pickupSchedule.setCode(code);
        pickupSchedule.setStartTime(start_time);
        pickupSchedule.setEndTime(end_time);


        return pickupScheduleRepository.save(pickupSchedule);
    }
}
