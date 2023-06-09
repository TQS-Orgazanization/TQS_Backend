package com.tqs.pickuppointbackend.model.DTO;

import org.junit.jupiter.api.Test;

import com.tqs.pickuppointbackend.model.Dto.PickupPointDTO;
import com.tqs.pickuppointbackend.model.Dto.PickupScheduleDTO;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class PickupPointDTOTest {

    @Test
    public void testPickupPointDTOClass() {
        Long pointId = 1L;
        String name = "Test Pickup Point";
        String address = "Test Address";
        boolean availability = true;
        String contactInfo = "Test Contact Info";
        List<PickupScheduleDTO> pickupSchedules = new ArrayList<>();
        Long user_id = 1L;

        PickupPointDTO pickupPointDTO = new PickupPointDTO(pointId, name, address, availability, contactInfo, pickupSchedules, user_id);

        assertEquals(pointId, pickupPointDTO.getPoint_id());
        assertEquals(name, pickupPointDTO.getName());
        assertEquals(address, pickupPointDTO.getAddress());
        assertEquals(availability, pickupPointDTO.isAvailability());
        assertEquals(contactInfo, pickupPointDTO.getContactInfo());
        assertEquals(pickupSchedules, pickupPointDTO.getPickupSchedules());
    }

    @Test
    public void testPickupPointDTOClassWithEmptyConstructor() {
        PickupPointDTO pickupPointDTO = new PickupPointDTO();

        assertNull(pickupPointDTO.getPoint_id());
        assertNull(pickupPointDTO.getName());
        assertNull(pickupPointDTO.getAddress());
        assertFalse(pickupPointDTO.isAvailability());
        assertNull(pickupPointDTO.getContactInfo());
        assertNull(pickupPointDTO.getPickupSchedules());
    }

    @Test
    public void testPickupPointDTOSetters() {
        Long pointId = 1L;
        String name = "Test Pickup Point";
        String address = "Test Address";
        boolean availability = true;
        String contactInfo = "Test Contact Info";
        List<PickupScheduleDTO> pickupSchedules = new ArrayList<>();
        Long user_id = 1L;
    
        PickupPointDTO pickupPointDTO = new PickupPointDTO();

        pickupPointDTO.setPoint_id(pointId);
        pickupPointDTO.setName(name);
        pickupPointDTO.setAddress(address);
        pickupPointDTO.setAvailability(availability);
        pickupPointDTO.setContactInfo(contactInfo);
        pickupPointDTO.setPickupSchedules(pickupSchedules);
        pickupPointDTO.setUser_id(user_id);


        assertEquals(pointId, pickupPointDTO.getPoint_id());
        assertEquals(name, pickupPointDTO.getName());
        assertEquals(address, pickupPointDTO.getAddress());
        assertEquals(availability, pickupPointDTO.isAvailability());
        assertEquals(contactInfo, pickupPointDTO.getContactInfo());
        assertEquals(pickupSchedules, pickupPointDTO.getPickupSchedules());
        assertEquals(user_id, pickupPointDTO.getUser_id());
    }
}
