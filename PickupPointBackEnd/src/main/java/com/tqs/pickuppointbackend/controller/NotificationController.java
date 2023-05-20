package com.tqs.pickuppointbackend.controller;

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
import com.tqs.pickuppointbackend.model.Notification;
import com.tqs.pickuppointbackend.model.Dto.NotificationDTO;
import com.tqs.pickuppointbackend.service.NotificationService;

@RestController
public class NotificationController {

    @Autowired
    private NotificationService notificationService;


    @GetMapping("/notifications/{user_id}")
    public ResponseEntity<List<Notification>> getNotificationsByUserId(@PathVariable(value = "user_id") long userId){

        return ResponseEntity.ok().body(notificationService.getNotificationsByUserId(userId));

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
