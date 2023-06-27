package com.tqs.pickuppointbackend.controller.model;

import com.tqs.pickuppointbackend.constants.UserType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class RegisterRequest {

    private String email;

    private String password;

    private UserType userType;
}
