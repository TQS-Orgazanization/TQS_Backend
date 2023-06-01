package com.tqs.pickuppointbackend.model.DTO;

import org.junit.jupiter.api.Test;

import com.tqs.pickuppointbackend.model.Dto.NotificationDTO;

import static org.junit.jupiter.api.Assertions.*;

public class NotificationDTOTest {

    @Test
    public void testNotificationDTOClass() {
        Long id = 1L;
        String message = "Test message";
        Long userId = 123L;

        NotificationDTO notificationDTO = new NotificationDTO(id, message, userId);

        assertEquals(id, notificationDTO.getId());
        assertEquals(message, notificationDTO.getMessage());
        assertEquals(userId, notificationDTO.getUserId());
    }
}
