package com.tqs.pickuppointbackend.service;

import com.tqs.pickuppointbackend.exceptions.ResourceNotFoundException;
import com.tqs.pickuppointbackend.model.PickupPoint;
import com.tqs.pickuppointbackend.model.Dto.PickupPointDTO;
import com.tqs.pickuppointbackend.repository.PickupPointRepository;
import com.tqs.pickuppointbackend.service.PickupPointService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PickupPointServiceTest {


    @Mock
    private PickupPointRepository pickupPointRepository;

    @InjectMocks
    private PickupPointService pickupPointService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetPickupPoints() {
        List<PickupPoint> pickupPoints = new ArrayList<>();
        PickupPoint pickupPoint1 = new PickupPoint();
        pickupPoint1.setId(1L);
        pickupPoint1.setName("Pickup Point 1");
        pickupPoints.add(pickupPoint1);
        when(pickupPointRepository.findAll()).thenReturn(pickupPoints);

        List<PickupPoint> result = pickupPointService.getPickupPoints();

        Assertions.assertEquals(pickupPoints, result);
    }

    @Test
    public void testGetPickupPointById() throws ResourceNotFoundException {
        long pickupPointId = 1L;
        PickupPoint pickupPoint = new PickupPoint();
        pickupPoint.setId(pickupPointId);
        pickupPoint.setName("Pickup Point");
        when(pickupPointRepository.findById(pickupPointId)).thenReturn(Optional.of(pickupPoint));

        PickupPoint result = pickupPointService.getPickupPointById(pickupPointId);

        Assertions.assertEquals(pickupPoint, result);
    }

    @Test
    public void testGetPickupPointById_NotFound() {
        long pickupPointId = 1L;
        when(pickupPointRepository.findById(pickupPointId)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            pickupPointService.getPickupPointById(pickupPointId);
        });
    }

    @Test
    public void testAddPickupPoint() {
        PickupPointDTO pickupPointDTO = new PickupPointDTO();
        pickupPointDTO.setName("New Pickup Point");

        PickupPoint pickupPoint = new PickupPoint();
        pickupPoint.setName(pickupPointDTO.getName());

        when(pickupPointRepository.save(any(PickupPoint.class))).thenReturn(pickupPoint);

        PickupPoint result = pickupPointService.addPickupPoint(pickupPointDTO);

        Assertions.assertEquals(pickupPoint, result);
    }

    /*@Test
    public void testUpdatePickupPoint() throws ResourceNotFoundException {
        PickupPointDTO pickupPointDTO = new PickupPointDTO();
        pickupPointDTO.setPoint_id(1L);
        pickupPointDTO.setName("Updated Pickup Point");

        PickupPoint pickupPoint = new PickupPoint();
        pickupPoint.setId(pickupPointDTO.getPoint_id());
        pickupPoint.setName(pickupPointDTO.getName());

        PickupPoint existingPickupPoint = new PickupPoint();
        existingPickupPoint.setId(pickupPointDTO.getPoint_id());

        when(pickupPointRepository.findById(pickupPointDTO.getPoint_id())).thenReturn(Optional.of(existingPickupPoint));
        when(pickupPointRepository.save(any(PickupPoint.class))).thenReturn(pickupPoint);

        PickupPoint result = pickupPointService.updatePickupPoint(pickupPointDTO);

        Assertions.assertEquals(pickupPoint, result);
    }*/

    /*@Test
    public void testUpdatePickupPoint_NotFound() {
        PickupPointDTO pickupPointDTO = new PickupPointDTO();
        pickupPointDTO.setPoint_id(1L);
        pickupPointDTO.setName("Updated Pickup Point");

        when(pickupPointRepository.findById(pickupPointDTO.getPoint_id())).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            pickupPointService.updatePickupPoint(pickupPointDTO);
        });

        verify(pickupPointRepository, never()).save(any(PickupPoint.class));
    }*/
  
    @Test
    public void testDeletePickupPointById() throws ResourceNotFoundException {
        long pickupPointId = 1L;
        PickupPoint pickupPoint = new PickupPoint();
        pickupPoint.setId(pickupPointId);
        pickupPoint.setName("Pickup Point");
        when(pickupPointRepository.findById(pickupPointId)).thenReturn(Optional.of(pickupPoint));

        PickupPoint result = pickupPointService.deletePickupPointById(pickupPointId);

        Assertions.assertEquals(pickupPoint, result);
        verify(pickupPointRepository, times(1)).deleteById(pickupPointId);
    }

    @Test
    public void testDeletePickupPointById_NotFound() {
        long pickupPointId = 1L;
        when(pickupPointRepository.findById(pickupPointId)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            pickupPointService.deletePickupPointById(pickupPointId);
        });

        verify(pickupPointRepository, never()).deleteById(pickupPointId);
    }

    /*@Test
    public void testPickupPointFromDTO() {
        PickupPointDTO pickupPointDTO = new PickupPointDTO();
        pickupPointDTO.setName("New Pickup Point");

        PickupPoint result = pickupPointService.pickupPointFromDTO(pickupPointDTO);

        Assertions.assertEquals(pickupPointDTO.getName(), result.getName());
    }
    */
    
}
