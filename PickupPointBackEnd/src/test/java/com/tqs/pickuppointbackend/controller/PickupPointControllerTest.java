package com.tqs.pickuppointbackend.controller;

import com.tqs.pickuppointbackend.exceptions.ResourceNotFoundException;
import com.tqs.pickuppointbackend.model.PickupPoint;
import com.tqs.pickuppointbackend.model.Dto.PickupPointDTO;
import com.tqs.pickuppointbackend.service.PickupPointService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

import static com.tqs.pickuppointbackend.constants.Constants.TEST_TOKEN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PickupPointControllerTest {

    @Mock
    private PickupPointService pickupPointService;

    @InjectMocks
    private PickupPointController pickupPointController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testGetPickupPoints() throws ResourceNotFoundException {
        PickupPoint pickupPoint1 = new PickupPoint();
        pickupPoint1.setName("Pickup Point 1");

        PickupPoint pickupPoint2 = new PickupPoint();
        pickupPoint2.setName("Pickup Point 2");

        List<PickupPoint> pickupPoints = Arrays.asList(pickupPoint1, pickupPoint2);

        when(pickupPointService.getPickupPoints()).thenReturn(pickupPoints);

        ResponseEntity<List<PickupPoint>> response = pickupPointController.getPickupPoints(TEST_TOKEN);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pickupPoints, response.getBody());
    }

    @Test
    public void testGetPickupScheduleById() throws ResourceNotFoundException {
        long pickupPointId = 1L;
        PickupPoint pickupPoint = new PickupPoint();
        pickupPoint.setName("Pickup Point");

        when(pickupPointService.getPickupPointById(pickupPointId)).thenReturn(pickupPoint);

        ResponseEntity<PickupPoint> response = pickupPointController.getPickupScheduleById(pickupPointId, TEST_TOKEN);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pickupPoint, response.getBody());
    }

    @Test
    public void testAddPickupPoint() throws ResourceNotFoundException {
        PickupPointDTO pickupPointDTO = new PickupPointDTO();
        pickupPointDTO.setName("New Pickup Point");

        PickupPoint addedPickupPoint = new PickupPoint();
        addedPickupPoint.setName("New Pickup Point");

        when(pickupPointService.addPickupPoint(pickupPointDTO)).thenReturn(addedPickupPoint);

        ResponseEntity<PickupPoint> response = pickupPointController.addPickupPoint(pickupPointDTO, TEST_TOKEN);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(addedPickupPoint, response.getBody());
    }

    /*
    @Test
    public void testUpdatePickupPoint() throws ResourceNotFoundException {
        long pickupPointId = 1L;
        PickupPointDTO pickupPointDTO = new PickupPointDTO();
        pickupPointDTO.setName("Updated Pickup Point");

        PickupPoint updatedPickupPoint = new PickupPoint();
        updatedPickupPoint.setName("Updated Pickup Point");

        when(pickupPointService.updatePickupPoint(pickupPointDTO)).thenReturn(updatedPickupPoint);

        ResponseEntity<PickupPoint> response = pickupPointController.updatePickupPoint(pickupPointId, pickupPointDTO, TEST_TOKEN);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedPickupPoint, response.getBody());
    }*/

    @Test
    public void testDeletePickupPointById() throws ResourceNotFoundException {
        long pickupPointId = 1L;
        PickupPoint deletedPickupPoint = new PickupPoint();
        deletedPickupPoint.setName("Pickup Point");

        when(pickupPointService.deletePickupPointById(pickupPointId)).thenReturn(deletedPickupPoint);

        ResponseEntity<PickupPoint> response = pickupPointController.deletePickupPointById(pickupPointId, TEST_TOKEN);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(deletedPickupPoint, response.getBody());
    }
}
