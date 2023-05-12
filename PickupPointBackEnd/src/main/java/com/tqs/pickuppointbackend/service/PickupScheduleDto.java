package com.tqs.pickuppointbackend.service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PickupScheduleDto {
    private Long pickupPointId;
    private Long userId;
    private Long code;
    private Long startTime;
    private Long endTime;
}
