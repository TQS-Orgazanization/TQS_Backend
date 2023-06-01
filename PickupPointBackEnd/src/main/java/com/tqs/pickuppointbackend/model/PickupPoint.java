package com.tqs.pickuppointbackend.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    @JsonIgnore
    @OneToMany(mappedBy = "pickupPoint", cascade = CascadeType.ALL)
    private List<PickupSchedule> pickupSchedules;
    
    // Setter for id field
    public void setId(Long id) {
        this.point_id = id;
    }
}
