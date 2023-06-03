package com.tqs.pickuppointbackend.controller.model;

import com.tqs.pickuppointbackend.constants.UserType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class UserResponse {
    private Long userId;

    private String name;

    private String email;

    private String phone;

    private String address;

    private String password;

    private UserType userType;
}
