package com.tqs.pickuppointbackend.controller;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.tqs.pickuppointbackend.model.PickupPoint;
import com.tqs.pickuppointbackend.service.PickupPointService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;

@WebMvcTest(PickupPointController.class)
public class PickupPointControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PickupPointService pickupPointService;

    @Test
    void getPickupPointsTest() throws Exception {

        PickupPoint pickupPoint = new PickupPoint(1L, "MEGA PICKUP POINT", "Esgueira, Aveiro", true, "megapickuppoint@gmail.com", null);
        List<PickupPoint> pickupPoints = new ArrayList<>();
        pickupPoints.add(pickupPoint);

        when(pickupPointService.getPickupPoints()).thenReturn(pickupPoints);

        mvc.perform(
            get("/pickuppoints").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].point_id", is(1)))
            .andExpect(jsonPath("$[0].name", is("MEGA PICKUP POINT")))
            .andExpect(jsonPath("$[0].address", is("Esgueira, Aveiro")))
            .andExpect(jsonPath("$[0].availability", is(true)))
            .andExpect(jsonPath("$[0].contactInfo", is("megapickuppoint@gmail.com")));

    }

    @Test
    void getPickupPointTest() throws Exception {

        PickupPoint pickupPoint = new PickupPoint(1L, "MEGA PICKUP POINT", "Esgueira, Aveiro", true, "megapickuppoint@gmail.com", null);

        when(pickupPointService.getPickupPointById(1)).thenReturn(pickupPoint);

        mvc.perform(
            get("/pickuppoint/1").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.point_id", is(1)))
            .andExpect(jsonPath("$.name", is("MEGA PICKUP POINT")))
            .andExpect(jsonPath("$.address", is("Esgueira, Aveiro")))
            .andExpect(jsonPath("$.availability", is(true)))
            .andExpect(jsonPath("$.contactInfo", is("megapickuppoint@gmail.com")));

    }

    

}
