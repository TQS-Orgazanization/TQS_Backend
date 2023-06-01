package com.tqs.pickuppointbackend.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class PickupPointTest {

    @Test
    public void testPickupPointClass() {
        Long pointId = 1L;
        String name = "Test Pickup Point";
        String address = "123 Main St";
        boolean availability = true;
        String contactInfo = "test@example.com";

        List<PickupSchedule> pickupSchedules = new ArrayList<>();

        PickupPoint pickupPoint = new PickupPoint(pointId, name, address, availability, contactInfo, pickupSchedules);

        assertEquals(pointId, pickupPoint.getPoint_id());
        assertEquals(name, pickupPoint.getName());
        assertEquals(address, pickupPoint.getAddress());
        assertEquals(availability, pickupPoint.isAvailability());
        assertEquals(contactInfo, pickupPoint.getContactInfo());
        assertEquals(pickupSchedules, pickupPoint.getPickupSchedules());
    }

    @Test
    public void testPickupPointSetters() {
        Long pointId = 1L;
        String name = "Test Pickup Point";
        String address = "123 Main St";
        boolean availability = true;
        String contactInfo = "test@example.com";

        List<PickupSchedule> pickupSchedules = new ArrayList<>();

        PickupPoint pickupPoint = new PickupPoint();

        pickupPoint.setPoint_id(pointId);
        pickupPoint.setName(name);
        pickupPoint.setAddress(address);
        pickupPoint.setAvailability(availability);
        pickupPoint.setContactInfo(contactInfo);
        pickupPoint.setPickupSchedules(pickupSchedules);

        assertEquals(pointId, pickupPoint.getPoint_id());
        assertEquals(name, pickupPoint.getName());
        assertEquals(address, pickupPoint.getAddress());
        assertEquals(availability, pickupPoint.isAvailability());
        assertEquals(contactInfo, pickupPoint.getContactInfo());
        assertEquals(pickupSchedules, pickupPoint.getPickupSchedules());
    }
}
