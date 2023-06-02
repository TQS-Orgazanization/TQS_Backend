package com.tqs.pickuppointbackend.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NotificationTest {

    @Test
    public void testNotificationClass() {
        Long id = 1L;
        String message = "Test message";
        //User user = new User(); // You can provide a valid User object here if needed
        Long user_id = 1L

        Notification notification = new Notification();
        notification.setId(id);
        notification.setMessage(message);
        notification.setUserId(user_id);

        assertEquals(id, notification.getId());
        assertEquals(message, notification.getMessage());
        assertEquals(user_id, notification.getUserId());
    }
}
