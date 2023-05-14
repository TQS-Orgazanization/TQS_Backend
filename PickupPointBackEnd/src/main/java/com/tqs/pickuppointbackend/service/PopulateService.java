package com.tqs.pickuppointbackend.service;

import com.tqs.pickuppointbackend.model.PickupPoint;
import com.tqs.pickuppointbackend.model.PickupSchedule;
import com.tqs.pickuppointbackend.model.User;
import com.tqs.pickuppointbackend.repository.PickupPointRepository;
import com.tqs.pickuppointbackend.repository.PickupScheduleRepository;
import com.tqs.pickuppointbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PopulateService {


    @Autowired
    PickupPointRepository pickupPointRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PickupScheduleRepository pickupScheduleRepository;


    public void populateDatabase() {
        // Create sample PickupPoints
        /*PickupPoint pickupPoint1 = new PickupPoint();
        pickupPoint1.setName("Pickup Point 1");
        pickupPoint1.setAddress("Address 1");
        pickupPoint1.setAvailability(true);
        pickupPoint1.setContactInfo("Contact Info 1");
        pickupPointRepository.save(pickupPoint1);

        PickupPoint pickupPoint2 = new PickupPoint();
        pickupPoint2.setName("Pickup Point 2");
        pickupPoint2.setAddress("Address 2");
        pickupPoint2.setAvailability(false);
        pickupPoint2.setContactInfo("Contact Info 2");
        pickupPointRepository.save(pickupPoint2);

        // Create sample Users
        User user1 = new User();
        user1.setName("User 1");
        user1.setEmail("user1@example.com");
        user1.setPhone("1234567890");
        user1.setAddress("User Address 1");
        userRepository.save(user1);

        User user2 = new User();
        user2.setName("User 2");
        user2.setEmail("user2@example.com");
        user2.setPhone("9876543210");
        user2.setAddress("User Address 2");
        userRepository.save(user2);

        // Create sample PickupSchedules
        PickupSchedule pickupSchedule1 = new PickupSchedule();
        pickupSchedule1.setPickupPoint(pickupPoint1);
        pickupSchedule1.setUser(user1);
        pickupSchedule1.setStartTime(new Date().getTime());
        pickupSchedule1.setEndTime(new Date().getTime() + 7L * 24 * 60 * 60 * 1000);  // add 1 week
        pickupSchedule1.setCode(00000L);
        pickupScheduleRepository.save(pickupSchedule1);

        PickupSchedule pickupSchedule2 = new PickupSchedule();
        pickupSchedule2.setPickupPoint(pickupPoint2);
        pickupSchedule2.setUser(user2);
        pickupSchedule2.setStartTime(new Date().getTime());
        pickupSchedule2.setEndTime(new Date().getTime() + 7L * 24 * 60 * 60 * 1000);  // add 1 week
        pickupSchedule1.setCode(00000L);
        pickupScheduleRepository.save(pickupSchedule2);
        */
    }
}
