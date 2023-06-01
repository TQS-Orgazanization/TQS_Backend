package com.tqs.pickuppointbackend.model.DTO;

import org.junit.jupiter.api.Test;

import com.tqs.pickuppointbackend.model.Dto.PickupScheduleDTO;

import static org.junit.jupiter.api.Assertions.*;

public class PickupScheduleDTOTest {

    @Test
    public void testPickupScheduleDTOClass() {
        Long id = 1L;
        Long code = 123L;
        Boolean availability = true;
        Long pickupPointId = 2L;
        Long userId = 3L;

        PickupScheduleDTO pickupScheduleDTO = new PickupScheduleDTO(id, code, availability, pickupPointId, userId);

        assertEquals(id, pickupScheduleDTO.getId());
        assertEquals(code, pickupScheduleDTO.getCode());
        assertEquals(availability, pickupScheduleDTO.getAvailability());
        assertEquals(pickupPointId, pickupScheduleDTO.getPickupPointId());
        assertEquals(userId, pickupScheduleDTO.getUserId());
    }

    @Test
    public void testPickupScheduleDTOSetters() {
        Long id = 1L;
        Long code = 123L;
        Boolean availability = true;
        Long pickupPointId = 2L;
        Long userId = 3L;

        PickupScheduleDTO pickupScheduleDTO = new PickupScheduleDTO(userId, userId, availability, userId, userId);

        pickupScheduleDTO.setId(id);
        pickupScheduleDTO.setCode(code);
        pickupScheduleDTO.setAvailability(availability);
        pickupScheduleDTO.setPickupPointId(pickupPointId);
        pickupScheduleDTO.setUserId(userId);

        assertEquals(id, pickupScheduleDTO.getId());
        assertEquals(code, pickupScheduleDTO.getCode());
        assertEquals(availability, pickupScheduleDTO.getAvailability());
        assertEquals(pickupPointId, pickupScheduleDTO.getPickupPointId());
        assertEquals(userId, pickupScheduleDTO.getUserId());
    }
}
