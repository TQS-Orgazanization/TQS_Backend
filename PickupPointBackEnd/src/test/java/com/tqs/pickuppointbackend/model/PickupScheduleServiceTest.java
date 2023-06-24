package com.tqs.pickuppointbackend.model;

import com.tqs.pickuppointbackend.exceptions.ResourceNotFoundException;
import com.tqs.pickuppointbackend.model.Dto.PickupScheduleDTO;
import com.tqs.pickuppointbackend.repository.PickupPointRepository;
import com.tqs.pickuppointbackend.repository.PickupScheduleRepository;
import com.tqs.pickuppointbackend.repository.UserRepository;
import com.tqs.pickuppointbackend.service.PickupScheduleService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PickupScheduleServiceTest {

    @Mock
    private PickupScheduleRepository pickupScheduleRepository;

    @Mock
    private PickupPointRepository pickupPointRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PickupScheduleService pickupScheduleService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    /*@Test
    public void testGetPickupSchedules() {
        List<PickupSchedule> expectedSchedules = new ArrayList<>();
        expectedSchedules.add(new PickupSchedule());
        expectedSchedules.add(new PickupSchedule());

        Mockito.when(pickupScheduleRepository.findAll()).thenReturn(expectedSchedules);

        List<PickupSchedule> actualSchedules = pickupScheduleService.getPickupSchedules();

        Assertions.assertEquals(expectedSchedules, actualSchedules);
        Mockito.verify(pickupScheduleRepository).findAll();
    }

    @Test
    public void testGetPickupScheduleById() throws ResourceNotFoundException {
        Long id = 1L;
        PickupSchedule expectedSchedule = new PickupSchedule();
        expectedSchedule.setId(id);

        Mockito.when(pickupScheduleRepository.findById(id)).thenReturn(Optional.of(expectedSchedule));

        PickupSchedule actualSchedule = pickupScheduleService.getPickupScheduleById(id);

        Assertions.assertEquals(expectedSchedule, actualSchedule);
        Mockito.verify(pickupScheduleRepository).findById(id);
    }

    @Test
    public void testGetPickupScheduleById_ThrowsResourceNotFoundException() {
        Long id = 1L;

        Mockito.when(pickupScheduleRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            pickupScheduleService.getPickupScheduleById(id);
        });

        Mockito.verify(pickupScheduleRepository).findById(id);
    }

    @Test
    public void testAddPickupSchedule() throws ResourceNotFoundException, NoSuchAlgorithmException {
        PickupScheduleDTO pickupScheduleDTO = new PickupScheduleDTO(null, null, null, null, null);
        pickupScheduleDTO.setCode(123L);
        pickupScheduleDTO.setAvailability(true);
        pickupScheduleDTO.setPickupPointId(1L);
        pickupScheduleDTO.setUserId(2L);

        PickupPoint pickupPoint = new PickupPoint();
        User user = new User();
        PickupSchedule expectedSchedule = new PickupSchedule();
        expectedSchedule.setCode(123L);
        expectedSchedule.setAvailability(true);
        expectedSchedule.setPickupPoint(pickupPoint);
        expectedSchedule.setUser(user);

        Mockito.when(pickupPointRepository.findById(pickupScheduleDTO.getPickupPointId())).thenReturn(Optional.of(pickupPoint));
        Mockito.when(userRepository.findById(pickupScheduleDTO.getUserId())).thenReturn(Optional.of(user));
        Mockito.when(pickupScheduleRepository.save(Mockito.any())).thenReturn(expectedSchedule);

        PickupSchedule actualSchedule = pickupScheduleService.addPickupSchedule(pickupScheduleDTO);

        Assertions.assertEquals(expectedSchedule, actualSchedule);
        Mockito.verify(pickupPointRepository).findById(pickupScheduleDTO.getPickupPointId());
        Mockito.verify(userRepository).findById(pickupScheduleDTO.getUserId());
        Mockito.verify(pickupScheduleRepository).save(Mockito.any());
    }

    @Test
    public void testDeletePickupScheduleById() throws ResourceNotFoundException {
        Long id = 1L;
        PickupSchedule expectedSchedule = new PickupSchedule();
        expectedSchedule.setId(id);

        Mockito.when(pickupScheduleRepository.findById(id)).thenReturn(Optional.of(expectedSchedule));

        PickupSchedule actualSchedule = pickupScheduleService.deletePickupScheduleById(id);

        Assertions.assertEquals(expectedSchedule, actualSchedule);
        Mockito.verify(pickupScheduleRepository).findById(id);
        Mockito.verify(pickupScheduleRepository).deleteById(id);
    }

    @Test
    public void testDeletePickupScheduleById_ThrowsResourceNotFoundException() {
        Long id = 1L;

        Mockito.when(pickupScheduleRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            pickupScheduleService.deletePickupScheduleById(id);
        });

        Mockito.verify(pickupScheduleRepository).findById(id);
        Mockito.verify(pickupScheduleRepository, Mockito.never()).deleteById(id);
    }

    @Test
public void testPickupScheduleFromDTO() throws ResourceNotFoundException {
    PickupScheduleDTO pickupScheduleDTO = new PickupScheduleDTO(null, null, null, null, null);
    pickupScheduleDTO.setCode(123L);
    pickupScheduleDTO.setAvailability(true);
    pickupScheduleDTO.setPickupPointId(1L);
    pickupScheduleDTO.setUserId(2L);

    PickupPoint pickupPoint = new PickupPoint();
    User user = new User();

    Mockito.when(pickupPointRepository.findById(pickupScheduleDTO.getPickupPointId())).thenReturn(Optional.of(pickupPoint));
    Mockito.when(userRepository.findById(pickupScheduleDTO.getUserId())).thenReturn(Optional.of(user));

    PickupSchedule expectedSchedule = new PickupSchedule();
    expectedSchedule.setCode(123L);
    expectedSchedule.setAvailability(true);
    expectedSchedule.setPickupPoint(pickupPoint);
    expectedSchedule.setUser(user);

    PickupSchedule actualSchedule = pickupScheduleService.pickupScheduleFromDTO(pickupScheduleDTO);

    Assertions.assertEquals(expectedSchedule.getCode(), actualSchedule.getCode());
    Assertions.assertEquals(expectedSchedule.getAvailability(), actualSchedule.getAvailability());
    Assertions.assertEquals(expectedSchedule.getPickupPoint(), actualSchedule.getPickupPoint());
    Assertions.assertEquals(expectedSchedule.getUser(), actualSchedule.getUser());

    Mockito.verify(pickupPointRepository).findById(pickupScheduleDTO.getPickupPointId());
    Mockito.verify(userRepository).findById(pickupScheduleDTO.getUserId());
}

@Test
public void testPickupScheduleFromDTO_ThrowsResourceNotFoundException() {
    PickupScheduleDTO pickupScheduleDTO = new PickupScheduleDTO(null, null, null, null, null);
    pickupScheduleDTO.setCode(123L);
    pickupScheduleDTO.setAvailability(true);
    pickupScheduleDTO.setPickupPointId(1L);
    pickupScheduleDTO.setUserId(2L);

    PickupPoint pickupPoint = new PickupPoint();

    Mockito.when(pickupPointRepository.findById(pickupScheduleDTO.getPickupPointId())).thenReturn(Optional.of(pickupPoint));
    Mockito.when(userRepository.findById(pickupScheduleDTO.getUserId())).thenReturn(Optional.empty());

    Assertions.assertThrows(ResourceNotFoundException.class, () -> {
        pickupScheduleService.pickupScheduleFromDTO(pickupScheduleDTO);
    });

    Mockito.verify(pickupPointRepository).findById(pickupScheduleDTO.getPickupPointId());
    Mockito.verify(userRepository).findById(pickupScheduleDTO.getUserId());
}*/

}
