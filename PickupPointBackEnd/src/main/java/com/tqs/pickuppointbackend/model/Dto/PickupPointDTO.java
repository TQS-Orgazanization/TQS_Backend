package com.tqs.pickuppointbackend.model.Dto;



import lombok.*;

import java.util.List;

import com.tqs.pickuppointbackend.model.PickupPoint;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PickupPointDTO {

    private Long point_id;
    private String name;
    private String address;
    private boolean availability;
    private String contactInfo;
    
    // Relationships
    private List<PickupScheduleDTO> pickupSchedules;
    private Long user_id;

    public PickupPoint pickupPointFromDTO() {

        PickupPoint pickupPoint = new PickupPoint();
        pickupPoint.setName(name);
        pickupPoint.setAddress(address);
        pickupPoint.setContactInfo(contactInfo);
        pickupPoint.setAvailability(availability);
        pickupPoint.setUser_id(user_id);
        if(point_id != null) {
            pickupPoint.setPoint_id(point_id);
        }

        return pickupPoint;

    }



}



