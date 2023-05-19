package com.tqs.pickuppointbackend.repository;

import com.tqs.pickuppointbackend.model.PickupPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PickupPointRepository extends JpaRepository<PickupPoint, Long> {
    // Add custom query methods if needed
}
