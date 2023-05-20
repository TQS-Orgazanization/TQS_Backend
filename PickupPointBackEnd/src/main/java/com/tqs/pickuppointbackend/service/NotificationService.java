package com.tqs.pickuppointbackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tqs.pickuppointbackend.exceptions.ResourceNotFoundException;
import com.tqs.pickuppointbackend.model.Notification;
import com.tqs.pickuppointbackend.model.Dto.NotificationDTO;
import com.tqs.pickuppointbackend.repository.NotificationRepository;

@Service
public class NotificationService {

    @Autowired
    NotificationRepository notificationRepository;

    public List<Notification> getNotificationsByUserId(long id) {
        return notificationRepository.findAllByUserId(id);
    }

    public Notification addNotification(NotificationDTO notificationDTO) throws ResourceNotFoundException{

        Notification notification = notificationFromDTO(notificationDTO);

        return notificationRepository.save(notification);
    
    }

    public Notification deleteNotificationById(long id) throws ResourceNotFoundException {
        Notification notification = notificationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Notification Not Found!"));
        
        notificationRepository.deleteById(id);

        return notification;
        
    }

    public Notification notificationFromDTO(NotificationDTO notificationDTO) {

        Notification notification = new Notification();
        notification.setMessage(notificationDTO.getMessage());

        return notification;

    }


}

