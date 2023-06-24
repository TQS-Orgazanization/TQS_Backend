package com.tqs.pickuppointbackend.service;

import com.tqs.pickuppointbackend.exceptions.ResourceNotFoundException;
import com.tqs.pickuppointbackend.model.Notification;
import com.tqs.pickuppointbackend.model.Dto.NotificationDTO;
import com.tqs.pickuppointbackend.repository.NotificationRepository;
import com.tqs.pickuppointbackend.service.NotificationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class NotificationServiceTest {

    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private NotificationService notificationService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetNotificationsByUserId() {
        long userId = 1L;
        List<Notification> notifications = new ArrayList<>();
        Notification notification1 = new Notification();
        notification1.setId(1L);
        notification1.setMessage("Notification 1");
        notification1.setId(userId);
        notifications.add(notification1);
        when(notificationRepository.findAllByUserId(userId)).thenReturn(notifications);

        List<Notification> result = notificationService.getNotificationsByUserId(userId);

        Assertions.assertEquals(notifications, result);
    }

    @Test
    public void testAddNotification() throws ResourceNotFoundException {
        NotificationDTO notificationDTO = new NotificationDTO(null, null, null);
        notificationDTO.setMessage("New Notification");

        Notification notification = new Notification();
        notification.setMessage(notificationDTO.getMessage());

        when(notificationRepository.save(any(Notification.class))).thenReturn(notification);

        Notification result = notificationService.addNotification(notificationDTO);

        Assertions.assertEquals(notification, result);
    }

    @Test
    public void testDeleteNotificationById() throws ResourceNotFoundException {
        long notificationId = 1L;
        Notification notification = new Notification();
        notification.setId(notificationId);
        notification.setMessage("Notification");
        when(notificationRepository.findById(notificationId)).thenReturn(Optional.of(notification));

        Notification result = notificationService.deleteNotificationById(notificationId);

        Assertions.assertEquals(notification, result);
        verify(notificationRepository, times(1)).deleteById(notificationId);
    }

    @Test
    public void testDeleteNotificationById_NotFound() {
        long notificationId = 1L;
        when(notificationRepository.findById(notificationId)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            notificationService.deleteNotificationById(notificationId);
        });

        verify(notificationRepository, never()).deleteById(notificationId);
    }

    /*@Test
    public void testNotificationFromDTO() {
        NotificationDTO notificationDTO = new NotificationDTO(null, null, null);
        notificationDTO.setMessage("New Notification");

        Notification result = notificationService.notificationFromDTO(notificationDTO);

        Assertions.assertEquals(notificationDTO.getMessage(), result.getMessage());
    }
    */
}
