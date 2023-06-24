package com.tqs.pickuppointbackend.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import com.tqs.pickuppointbackend.exceptions.ResourceNotFoundException;
import com.tqs.pickuppointbackend.model.Dto.NotificationDTO;
import com.tqs.pickuppointbackend.model.Dto.PickupPointDTO;
import com.tqs.pickuppointbackend.model.Dto.PickupScheduleDTO;
import com.tqs.pickuppointbackend.service.Utils;

public class UtilsTest {
    
    Utils utils = new Utils();

    @Test
    public void testGenerateRandomCode() {


    }

    @Test
    public void testPickupPointDTO() {
        PickupPointDTO pickupPointDTO = new PickupPointDTO();
        
        pickupPointDTO.setName("Name");
        pickupPointDTO.setAddress("Address");
        pickupPointDTO.setAvailability(false);
        pickupPointDTO.setContactInfo("contactinfo");

        PickupPoint pickupPoint = utils.pickupPointFromDTO(pickupPointDTO);

        assertEquals("Name", pickupPoint.getName());
        assertEquals("Address", pickupPoint.getAddress());
        assertEquals(false, pickupPoint.isAvailability());
        assertEquals("contactinfo", pickupPoint.getContactInfo());


    }

    @Test
    public void testNotificationDTO() {
        NotificationDTO notificationDTO = new NotificationDTO();
        
        notificationDTO.setMessage("Message");

        Notification notification = utils.notificationFromDTO(notificationDTO);

        assertEquals("Message", notification.getMessage());

    }
}
