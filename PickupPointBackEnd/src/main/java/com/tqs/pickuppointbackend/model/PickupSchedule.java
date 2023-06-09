package com.tqs.pickuppointbackend.model;

import lombok.*;

import java.sql.Date;

import javax.persistence.*;

@Getter
@Setter
@Entity
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pickup_schedule")
public class PickupSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private Long code;

    @Column(name = "availability")
    private Boolean availability;

    // Relationships
    // Many-to-One relationship with PickupPoint
    // Many-to-One relationship with User

    @ManyToOne
    @JoinColumn(name = "point_id")
    private PickupPoint pickupPoint;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }
}
