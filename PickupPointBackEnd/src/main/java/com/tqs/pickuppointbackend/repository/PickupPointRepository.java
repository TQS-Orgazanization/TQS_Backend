package com.tqs.pickuppointbackend.repository;

import com.tqs.pickuppointbackend.model.PickupPoint;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PickupPointRepository extends JpaRepository<PickupPoint, Long> {
    // Add custom query methods if needed
    List<PickupPoint> findByAvailability(boolean True);

    @Query("SELECT p FROM PickupPoint p WHERE p.user_id = :userId")
    PickupPoint findByUserId(long userId);
}
