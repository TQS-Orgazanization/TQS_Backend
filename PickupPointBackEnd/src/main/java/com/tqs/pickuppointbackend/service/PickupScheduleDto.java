package com.tqs.pickuppointbackend.service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PickupScheduleDto {
    private Long pickupPointId;
    private Long userId;
    private Long code;
    private String startTime;
    private String endTime;
}
