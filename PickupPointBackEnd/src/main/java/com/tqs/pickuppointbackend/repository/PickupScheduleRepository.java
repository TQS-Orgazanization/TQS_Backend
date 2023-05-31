package com.tqs.pickuppointbackend.repository;

import com.tqs.pickuppointbackend.model.PickupSchedule;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PickupScheduleRepository extends JpaRepository<PickupSchedule, Long> {
    // Add custom query methods if needed
    List<PickupSchedule> findByAvailability(boolean bool);

}

