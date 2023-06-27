package com.tqs.pickuppointbackend.service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tqs.pickuppointbackend.exceptions.ResourceNotFoundException;
import com.tqs.pickuppointbackend.model.Notification;
import com.tqs.pickuppointbackend.model.PickupPoint;
import com.tqs.pickuppointbackend.model.PickupSchedule;
import com.tqs.pickuppointbackend.model.User;
import com.tqs.pickuppointbackend.model.Dto.NotificationDTO;
import com.tqs.pickuppointbackend.model.Dto.PickupPointDTO;
import com.tqs.pickuppointbackend.model.Dto.PickupScheduleDTO;
import com.tqs.pickuppointbackend.repository.PickupPointRepository;
import com.tqs.pickuppointbackend.repository.UserRepository;

public class Utils {

    @Autowired
    PickupPointRepository pickupPointRepository;

    @Autowired
    UserRepository userRepository;

    public PickupPoint pickupPointFromDTO(PickupPointDTO pickupPointDTO) {

        PickupPoint pickupPoint = new PickupPoint();
        pickupPoint.setName(pickupPointDTO.getName());
        pickupPoint.setAddress(pickupPointDTO.getAddress());
        pickupPoint.setContactInfo(pickupPointDTO.getContactInfo());
        pickupPoint.setAvailability(pickupPointDTO.isAvailability());
        pickupPoint.setUser_id(pickupPointDTO.getUser_id());
        System.out.print(pickupPointDTO.getPoint_id());
        if(pickupPointDTO.getPoint_id() != null) {
            pickupPoint.setPoint_id(pickupPointDTO.getPoint_id());
        }

        return pickupPoint;

    }
    
    
    
    public Notification notificationFromDTO(NotificationDTO notificationDTO) {

        Notification notification = new Notification();
        notification.setMessage(notificationDTO.getMessage());
        notification.setUserId(notificationDTO.getUserId());


        return notification;

    }


    public PickupSchedule pickupScheduleFromDTO(PickupScheduleDTO pickupScheduleDTO) throws ResourceNotFoundException {

        PickupSchedule pickupSchedule = new PickupSchedule();
        //pickupSchedule.setCode(pickupScheduleDTO.getCode());
        //pickupSchedule.setAvailability(pickupScheduleDTO.getAvailability());

        PickupPoint pickupPoint = pickupPointRepository.findById(pickupScheduleDTO.getPickupPointId()).orElseThrow(() -> new ResourceNotFoundException("Pickup Point Not Found!"));
        User user = userRepository.findById(pickupScheduleDTO.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User Not Found!"));

        pickupSchedule.setPickupPoint(pickupPoint);
        pickupSchedule.setUser(user);


        if(pickupScheduleDTO.getId() != null) {
            pickupSchedule.setId(pickupScheduleDTO.getId());
        }

        return pickupSchedule;

    }

    public static String generateRandomCode() throws NoSuchAlgorithmException{
        Random rand = SecureRandom.getInstanceStrong();
        int code = rand.nextInt(900000) + 100000; // Generates a random number between 100000 and 999999
        return String.valueOf(code);
    }

    
}
