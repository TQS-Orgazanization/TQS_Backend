package com.tqs.pickuppointbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tqs.pickuppointbackend.controller.model.LoginResponse;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "userSession")
public class UserSession {
    @Id
    @Column(name = "userToken")
    private String token;

    @Column(name = "expiration")
    private Long expirationDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public LoginResponse toLoginRespose(){
        return LoginResponse.builder().accessToken(this.token).build();
    }

}
