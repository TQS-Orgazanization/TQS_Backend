package com.tqs.pickuppointbackend.model.Dto;



import lombok.*;

import java.util.List;

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



}



