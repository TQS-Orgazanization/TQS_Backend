package com.tqs.pickuppointbackend.model;

import com.tqs.pickuppointbackend.model.PickupSchedule;
import com.tqs.pickuppointbackend.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class UserTest {

    @Test
    public void testPickupSchedulesRelationship() {
        // Create a user
        User user = new User();
        user.setUserId(1L);
        user.setName("John");
        user.setEmail("john@example.com");
        user.setPhone("123456789");
        user.setAddress("123 Main St");
        user.setPassword("password");

        // Create pickup schedules
        PickupSchedule pickupSchedule1 = new PickupSchedule();
        pickupSchedule1.setId(1L);
        pickupSchedule1.setCode(123456L);
        pickupSchedule1.setAvailability(true);
        pickupSchedule1.setUser(user);

        PickupSchedule pickupSchedule2 = new PickupSchedule();
        pickupSchedule2.setId(2L);
        pickupSchedule2.setCode(789456L);
        pickupSchedule2.setAvailability(false);
        pickupSchedule2.setUser(user);

        // Add pickup schedules to the user
        List<PickupSchedule> pickupSchedules = new ArrayList<>();
        pickupSchedules.add(pickupSchedule1);
        pickupSchedules.add(pickupSchedule2);
        user.setPickupSchedules(pickupSchedules);

        // Check if the user's pickupSchedules list contains the expected pickup schedules
        Assertions.assertTrue(user.getPickupSchedules().contains(pickupSchedule1));
        Assertions.assertTrue(user.getPickupSchedules().contains(pickupSchedule2));
    }
}
