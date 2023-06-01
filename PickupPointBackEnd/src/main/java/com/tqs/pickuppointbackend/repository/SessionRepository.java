package com.tqs.pickuppointbackend.repository;

import com.tqs.pickuppointbackend.model.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<UserSession, Long> {

    Optional<UserSession> findByToken(String token);
}
