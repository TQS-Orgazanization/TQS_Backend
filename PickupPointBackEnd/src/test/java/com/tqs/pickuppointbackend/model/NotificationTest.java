package com.tqs.pickuppointbackend.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NotificationTest {

    @Test
    public void testNotificationClass() {
        Long id = 1L;
        String message = "Test message";
        User user = new User(); // You can provide a valid User object here if needed

        Notification notification = new Notification();
        notification.setId(id);
        notification.setMessage(message);
        notification.setUser(user);

        assertEquals(id, notification.getId());
        assertEquals(message, notification.getMessage());
        assertEquals(user, notification.getUser());
    }
}
