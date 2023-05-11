package com.tqs.pickuppointbackend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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

    // Getters and setters

    // Relationships
    // One-to-Many relationship with PickupSchedule
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<PickupSchedule> pickupSchedules;
}
