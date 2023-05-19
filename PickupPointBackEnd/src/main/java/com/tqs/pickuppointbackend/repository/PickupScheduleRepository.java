package com.tqs.pickuppointbackend.repository;

import com.tqs.pickuppointbackend.model.PickupSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PickupScheduleRepository extends JpaRepository<PickupSchedule, Long> {
    // Add custom query methods if needed
}

