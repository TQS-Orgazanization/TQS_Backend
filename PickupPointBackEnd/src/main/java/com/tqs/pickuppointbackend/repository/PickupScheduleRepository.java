package com.tqs.pickuppointbackend.repository;

import com.tqs.pickuppointbackend.model.PickupPoint;
import com.tqs.pickuppointbackend.model.PickupSchedule;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PickupScheduleRepository extends JpaRepository<PickupSchedule, Long> {
    // Add custom query methods if needed
    List<PickupSchedule> findByAvailability(boolean bool);

    @Query("SELECT p FROM PickupSchedule p WHERE p.user.userId = :userId")
    List<PickupSchedule> findByUserId(long userId);

    @Query("SELECT p FROM PickupSchedule p WHERE p.pickupPoint.point_id = :id AND p.availability = :bool")
    List<PickupSchedule> findByAvailabilityByPickupPointId(boolean bool, long id);

    List<PickupSchedule> findByAvailabilityAndPickupPoint(Boolean availability, PickupPoint pickupPoint);


}

