package com.tqs.pickuppointbackend.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PickupScheduleTest {

    private PickupSchedule pickupSchedule;

    @BeforeEach
    public void setup() {
        pickupSchedule = new PickupSchedule();
    }

    @Test
    public void testGetSetId() {
        Long id = 1L;
        pickupSchedule.setId(id);
        Assertions.assertEquals(id, pickupSchedule.getId());
    }

    @Test
    public void testGetSetCode() {
        Long code = 123L;
        pickupSchedule.setCode(code);
        Assertions.assertEquals(code, pickupSchedule.getCode());
    }

    @Test
    public void testGetSetAvailability() {
        Boolean availability = true;
        pickupSchedule.setAvailability(availability);
        Assertions.assertEquals(availability, pickupSchedule.getAvailability());
    }

    @Test
    public void testGetSetPickupPoint() {
        PickupPoint pickupPoint = new PickupPoint();
        pickupSchedule.setPickupPoint(pickupPoint);
        Assertions.assertEquals(pickupPoint, pickupSchedule.getPickupPoint());
    }

    @Test
    public void testGetSetUser() {
        User user = new User();
        pickupSchedule.setUser(user);
        Assertions.assertEquals(user, pickupSchedule.getUser());
    }
}
