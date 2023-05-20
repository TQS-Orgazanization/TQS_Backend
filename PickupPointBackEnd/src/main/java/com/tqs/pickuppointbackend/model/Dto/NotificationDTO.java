package com.tqs.pickuppointbackend.model.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NotificationDTO {

    private Long id;
    private String message;
    
    @JsonIgnore
    private Long userId;

}
