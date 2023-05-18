package com.tqs.pickuppointbackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tqs.pickuppointbackend.exceptions.ResourceNotFoundException;
import com.tqs.pickuppointbackend.model.Notification;
import com.tqs.pickuppointbackend.repository.NotificationRepository;

@Service
public class NotificationService {

    @Autowired
    NotificationRepository notificationRepository;

    public List<Notification> getNotificationsByUserId(long id) {
        return notificationRepository.findAllByUserId(id);
    }

    public Notification addNotification(Notification notification) throws ResourceNotFoundException{

        return notificationRepository.save(notification);
    
    }

}
