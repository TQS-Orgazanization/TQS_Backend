package com.tqs.pickuppointbackend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "pickup_point")
public class PickupPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_id")
    private Long point_id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "availability")
    private boolean availability;

    @Column(name = "contact_info")
    private String contactInfo;

    // Relationships
    // One-to-Many relationship with PickupSchedule
    @OneToMany(mappedBy = "pickupPoint", cascade = CascadeType.ALL)
    private List<PickupSchedule> pickupSchedules;
}
