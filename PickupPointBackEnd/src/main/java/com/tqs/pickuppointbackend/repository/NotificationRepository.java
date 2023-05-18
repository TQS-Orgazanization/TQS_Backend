package com.tqs.pickuppointbackend.repository;

import com.tqs.pickuppointbackend.model.Notification;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    // Add custom query methods if needed
    //List<Notification> findNotificationByUserId(long user_id);

    @Query(value = "SELECT * FROM notification WHERE user_id = :userId", nativeQuery = true)
    List<Notification> findAllByUserId(@Param("userId") Long userId);

}
