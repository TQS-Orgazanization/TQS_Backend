package com.tqs.pickuppointbackend.model;

import com.tqs.pickuppointbackend.constants.UserType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "password")
    private String password;

    @Column(name = "userType")
    private UserType userType;

    // Getters and setters

    public String getPassword() {
        return password;
    }

    // Relationships
    // One-to-Many relationship with PickupSchedule
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<PickupSchedule> pickupSchedules;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserSession> userSessions;
}
