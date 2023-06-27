package com.tqs.pickuppointbackend.model.Dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PickupScheduleDTO {
    private Long id;
    private Long code;
    private Boolean availability;
    private Long pickupPointId;
    private Long userId;
}