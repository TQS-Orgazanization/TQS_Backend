package com.tqs.pickuppointbackend.controller;

import com.tqs.pickuppointbackend.exceptions.ResourceNotFoundException;
import com.tqs.pickuppointbackend.model.PickupSchedule;
import com.tqs.pickuppointbackend.model.Dto.PickupScheduleDTO;
import com.tqs.pickuppointbackend.service.PickupScheduleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import static com.tqs.pickuppointbackend.constants.Constants.TEST_TOKEN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PickupScheduleControllerTest {

    @Mock
    private PickupScheduleService pickupScheduleService;

    @InjectMocks
    private PickupScheduleController pickupScheduleController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetPickupSchedules() throws ResourceNotFoundException {
        PickupSchedule pickupSchedule1 = new PickupSchedule();
        pickupSchedule1.setId(1L);

        PickupSchedule pickupSchedule2 = new PickupSchedule();
        pickupSchedule2.setId(2L);

        List<PickupSchedule> pickupSchedules = Arrays.asList(pickupSchedule1, pickupSchedule2);

        when(pickupScheduleService.getPickupSchedules()).thenReturn(pickupSchedules);

        ResponseEntity<List<PickupSchedule>> response = pickupScheduleController.getPickupSchedules(TEST_TOKEN);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pickupSchedules, response.getBody());
    }

    @Test
    public void testGetPickupScheduleById() throws ResourceNotFoundException {
        long pickupScheduleId = 1L;
        PickupSchedule pickupSchedule = new PickupSchedule();
        pickupSchedule.setId(pickupScheduleId);

        when(pickupScheduleService.getPickupScheduleById(pickupScheduleId)).thenReturn(pickupSchedule);

        ResponseEntity<PickupSchedule> response = pickupScheduleController.getPickupScheduleById(pickupScheduleId, TEST_TOKEN);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pickupSchedule, response.getBody());
    }

    @Test
    public void testAddPickupSchedule() throws ResourceNotFoundException, NoSuchAlgorithmException {
        PickupScheduleDTO pickupScheduleDTO = new PickupScheduleDTO(null, null, null, null, null);

        PickupSchedule addedPickupSchedule = new PickupSchedule();
        addedPickupSchedule.setId(1L);

        when(pickupScheduleService.addPickupSchedule(pickupScheduleDTO)).thenReturn(addedPickupSchedule);

        ResponseEntity<PickupSchedule> response = pickupScheduleController.addPickupSchedule(pickupScheduleDTO, TEST_TOKEN);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(addedPickupSchedule, response.getBody());
    }

    @Test
    public void testDeletePickupScheduleById() throws ResourceNotFoundException {
        long pickupScheduleId = 1L;
        PickupSchedule deletedPickupSchedule = new PickupSchedule();
        deletedPickupSchedule.setId(pickupScheduleId);

        when(pickupScheduleService.deletePickupScheduleById(pickupScheduleId)).thenReturn(deletedPickupSchedule);

        ResponseEntity<PickupSchedule> response = pickupScheduleController.deletePickupScheduleById(pickupScheduleId, TEST_TOKEN);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(deletedPickupSchedule, response.getBody());
    }
}
