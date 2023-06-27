package com.tqs.pickuppointbackend.controller.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequest {

    private String email;

    private String password;


}
