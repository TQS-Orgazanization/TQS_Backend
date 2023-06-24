package com.tqs.pickuppointbackend.model.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tqs.pickuppointbackend.model.Notification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDTO {

    private Long id;
    private String message;
    
    @JsonIgnore
    private Long userId;

    public Notification notificationFromDTO() {

        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setUserId(userId);

        return notification;

    }

}
