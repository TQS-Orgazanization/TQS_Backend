package com.tqs.pickuppointbackend.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.tqs.pickuppointbackend.model.PickupPoint;

public class PickupPointTest {


    PickupPoint pickupPoint = new PickupPoint(1L, "MEGA PICKUP POINT", "Esgueira, Aveiro", true, "megapickuppoint@gmail.com", null);

    @Test
    void pickupPointTest() {

        assertEquals(1L, pickupPoint.getPoint_id());
        assertEquals("MEGA PICKUP POINT", pickupPoint.getName());
        assertEquals("Esgueira, Aveiro", pickupPoint.getAddress());
        assertEquals(true, pickupPoint.isAvailability());
        assertEquals("megapickuppoint@gmail.com", pickupPoint.getContactInfo());
        assertEquals(null, pickupPoint.getPickupSchedules());

    }


    
}
