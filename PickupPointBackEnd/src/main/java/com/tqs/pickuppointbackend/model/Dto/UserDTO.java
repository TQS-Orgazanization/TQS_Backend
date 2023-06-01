package com.tqs.pickuppointbackend.model.Dto;

import java.util.List;

import com.tqs.pickuppointbackend.model.PickupSchedule;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Long userId;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String password;
    private List<PickupSchedule> pickupSchedules;

    
}
