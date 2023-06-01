package com.tqs.pickuppointbackend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
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

    // Relationships
    // One-to-Many relationship with PickupSchedule
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<PickupSchedule> pickupSchedules;

    // Lazy initialization of pickupSchedules
    public List<PickupSchedule> getPickupSchedules() {
        if (pickupSchedules == null) {
            pickupSchedules = new ArrayList<>();
        }
        return pickupSchedules;
    }
}
