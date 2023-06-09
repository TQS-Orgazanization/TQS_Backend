package com.tqs.pickuppointbackend.model;

import com.tqs.pickuppointbackend.constants.UserType;
import com.tqs.pickuppointbackend.controller.model.UserResponse;
import lombok.*;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
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

    // Lazy initialization of pickupSchedules
    public List<PickupSchedule> getPickupSchedules() {
        if (pickupSchedules == null) {
            pickupSchedules = new ArrayList<>();
        }
        return pickupSchedules;
    }
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserSession> userSessions;

    public UserResponse toResponse(){
        return UserResponse.builder()
                .userId(this.userId)
                .name(this.name)
                .email(this.email)
                .phone(this.phone)
                .address(this.address)
                .userType(this.userType)
                .build();
    }
}
