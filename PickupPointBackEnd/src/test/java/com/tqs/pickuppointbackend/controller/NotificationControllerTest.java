package com.tqs.pickuppointbackend.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import com.tqs.pickuppointbackend.exceptions.ResourceNotFoundException;
import com.tqs.pickuppointbackend.model.Notification;
import com.tqs.pickuppointbackend.model.Dto.NotificationDTO;
import com.tqs.pickuppointbackend.service.NotificationService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class NotificationControllerTest {

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private NotificationController notificationController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetNotificationsByUserId() {
        long userId = 1L;
        Notification notification1 = new Notification();
        notification1.setId(1L);
        notification1.setMessage("Notification 1");

        Notification notification2 = new Notification();
        notification2.setId(2L);
        notification2.setMessage("Notification 2");

        List<Notification> notifications = Arrays.asList(notification1, notification2);

        when(notificationService.getNotificationsByUserId(userId)).thenReturn(notifications);

        ResponseEntity<List<Notification>> response = notificationController.getNotificationsByUserId(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(notifications, response.getBody());
    }

    @Test
    public void testAddNotification() throws ResourceNotFoundException {
        NotificationDTO notificationDTO = new NotificationDTO(null, null, null);
        notificationDTO.setMessage("New Notification");

        Notification addedNotification = new Notification();
        addedNotification.setId(1L);
        addedNotification.setMessage("New Notification");

        when(notificationService.addNotification(notificationDTO)).thenReturn(addedNotification);

        ResponseEntity<Notification> response = notificationController.addNotfication(notificationDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(addedNotification, response.getBody());
    }

    @Test
    public void testDeleteNotificationById() throws ResourceNotFoundException {
        long notificationId = 1L;
        Notification deletedNotification = new Notification();
        deletedNotification.setId(notificationId);
        deletedNotification.setMessage("Notification");

        when(notificationService.deleteNotificationById(notificationId)).thenReturn(deletedNotification);

        ResponseEntity<Notification> response = notificationController.deleteNotificationById(notificationId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(deletedNotification, response.getBody());
    }
}
