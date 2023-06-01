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

        PickupPointDTO pickupPointDTO = new PickupPointDTO(pointId, name, address, availability, contactInfo, pickupSchedules);

        assertEquals(pointId, pickupPointDTO.getPointId());
        assertEquals(name, pickupPointDTO.getName());
        assertEquals(address, pickupPointDTO.getAddress());
        assertEquals(availability, pickupPointDTO.isAvailability());
        assertEquals(contactInfo, pickupPointDTO.getContactInfo());
        assertEquals(pickupSchedules, pickupPointDTO.getPickupSchedules());
    }

    @Test
    public void testPickupPointDTOClassWithEmptyConstructor() {
        PickupPointDTO pickupPointDTO = new PickupPointDTO();

        assertNull(pickupPointDTO.getPointId());
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

        PickupPointDTO pickupPointDTO = new PickupPointDTO();

        pickupPointDTO.setPointId(pointId);
        pickupPointDTO.setName(name);
        pickupPointDTO.setAddress(address);
        pickupPointDTO.setAvailability(availability);
        pickupPointDTO.setContactInfo(contactInfo);
        pickupPointDTO.setPickupSchedules(pickupSchedules);

        assertEquals(pointId, pickupPointDTO.getPointId());
        assertEquals(name, pickupPointDTO.getName());
        assertEquals(address, pickupPointDTO.getAddress());
        assertEquals(availability, pickupPointDTO.isAvailability());
        assertEquals(contactInfo, pickupPointDTO.getContactInfo());
        assertEquals(pickupSchedules, pickupPointDTO.getPickupSchedules());
    }
}
