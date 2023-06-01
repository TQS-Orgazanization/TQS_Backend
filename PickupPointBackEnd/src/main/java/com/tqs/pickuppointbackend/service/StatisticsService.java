package com.tqs.pickuppointbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tqs.pickuppointbackend.model.Statistics;
import com.tqs.pickuppointbackend.model.User;
import com.tqs.pickuppointbackend.repository.PickupPointRepository;
import com.tqs.pickuppointbackend.repository.PickupScheduleRepository;
import com.tqs.pickuppointbackend.repository.UserRepository;

@Service
public class StatisticsService {
    

    @Autowired
    private PickupPointRepository pickupPointRepository;

    @Autowired
    private PickupScheduleRepository pickupScheduleRepository;

    @Autowired
    private UserRepository userRepository;

    public int getNoPickupPoints() {
        return pickupPointRepository.findAll().size();
    }

    public int getNoPickupSchedules() {
        return pickupScheduleRepository.findAll().size();
    }

    public int getNoUsers() {
        return userRepository.findAll().size();
    }

    public Statistics getStats() {
        Statistics statistics = new Statistics();

        statistics.setNo_acps(getNoPickupPoints());
        statistics.setNo_schedules(getNoPickupSchedules());
        statistics.setNo_users(getNoUsers());

        return statistics;
    }


}
