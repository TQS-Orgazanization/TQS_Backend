package com.tqs.pickuppointbackend.controller;

import java.util.List;

import javax.validation.Valid;

import com.tqs.pickuppointbackend.constants.UserType;
import com.tqs.pickuppointbackend.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tqs.pickuppointbackend.exceptions.ResourceNotFoundException;
import com.tqs.pickuppointbackend.model.Notification;
import com.tqs.pickuppointbackend.model.Dto.NotificationDTO;
import com.tqs.pickuppointbackend.service.NotificationService;

@RestController
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private AuthenticationService authenticationService;


    @GetMapping("/notifications/{user_id}")
    public ResponseEntity<List<Notification>> getNotificationsByUserId(@PathVariable(value = "user_id") long userId, @RequestParam String token) throws ResourceNotFoundException {

        if (authenticationService.hasAcess(token, UserType.ADMIN)){
            return ResponseEntity.ok().body(notificationService.getNotificationsByUserId(userId));
        }
        throw new ResourceNotFoundException("The user dont have acess");

    }
    
    @PostMapping("/notification")
    public ResponseEntity<Notification> addNotfication(@Valid @RequestBody NotificationDTO notificationDTO) throws ResourceNotFoundException {

        return ResponseEntity.ok().body(notificationService.addNotification(notificationDTO));

    }

    @DeleteMapping("/notification/{id}")
    public ResponseEntity<Notification> deleteNotificationById(@PathVariable(value = "id") long id) throws ResourceNotFoundException {

        return ResponseEntity.ok().body(notificationService.deleteNotificationById(id));
    
    }


}
