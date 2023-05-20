package com.tqs.pickuppointbackend.model.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
public class PickupScheduleDTO {
    private Long id;
    private Long code;
    private Date startTime;
    private Date endTime;
    private Long pickupPointId;
    private Long userId;
}