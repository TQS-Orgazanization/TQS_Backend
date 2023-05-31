package com.tqs.pickuppointbackend.model.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PickupScheduleDTO {
    private Long id;
    private Long code;
    private Boolean availability;
    private Long pickupPointId;
    private Long userId;
}