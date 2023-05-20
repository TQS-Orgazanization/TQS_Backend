package com.tqs.pickuppointbackend.model.Dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PickupPointDTO {

    private Long pointId;
    private String name;
    private String address;
    private boolean availability;
    private String contactInfo;

    // Relationships
    private List<PickupScheduleDTO> pickupSchedules;



}



