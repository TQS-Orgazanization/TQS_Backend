package com.tqs.pickuppointbackend.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.tqs.pickuppointbackend.exceptions.ResourceNotFoundException;
import com.tqs.pickuppointbackend.model.PickupPoint;
import com.tqs.pickuppointbackend.model.PickupSchedule;
import com.tqs.pickuppointbackend.model.User;
import com.tqs.pickuppointbackend.model.Dto.PickupScheduleDTO;
import com.tqs.pickuppointbackend.repository.PickupPointRepository;
import com.tqs.pickuppointbackend.repository.PickupScheduleRepository;
import com.tqs.pickuppointbackend.repository.UserRepository;

class PickupScheduleServiceTest {

    @Mock
    private PickupScheduleRepository pickupScheduleRepository;

    @Mock
    private PickupPointRepository pickupPointRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PickupScheduleService pickupScheduleService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetPickupSchedules_ReturnsListOfPickupSchedules() {
        // Arrange
        List<PickupSchedule> expectedSchedules = new ArrayList<>();
        expectedSchedules.add(new PickupSchedule());
        expectedSchedules.add(new PickupSchedule());
        when(pickupScheduleRepository.findAll()).thenReturn(expectedSchedules);

        // Act
        List<PickupSchedule> actualSchedules = pickupScheduleService.getPickupSchedules();

        // Assert
        assertEquals(expectedSchedules, actualSchedules);
        verify(pickupScheduleRepository, times(1)).findAll();
    }

    @Test
    public void testGetPickupScheduleById_ExistingId_ReturnsPickupSchedule() throws ResourceNotFoundException {
        // Arrange
        long id = 1L;
        PickupSchedule expectedSchedule = new PickupSchedule();
        when(pickupScheduleRepository.findById(id)).thenReturn(Optional.of(expectedSchedule));

        // Act
        PickupSchedule actualSchedule = pickupScheduleService.getPickupScheduleById(id);

        // Assert
        assertEquals(expectedSchedule, actualSchedule);
        verify(pickupScheduleRepository, times(1)).findById(id);
    }

    @Test
    public void testGetPickupScheduleById_NonExistingId_ThrowsResourceNotFoundException() {
        // Arrange
        long id = 1L;
        when(pickupScheduleRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            pickupScheduleService.getPickupScheduleById(id);
        });
        verify(pickupScheduleRepository, times(1)).findById(id);
    }

    @Test
    public void testAddPickupSchedule_ValidPickupScheduleDTO_ReturnsSavedPickupSchedule() throws ResourceNotFoundException {
        // Arrange
        PickupScheduleDTO pickupScheduleDTO = new PickupScheduleDTO(null, null, null, null, null);
        pickupScheduleDTO.setCode(123L);
        pickupScheduleDTO.setAvailability(true);
        pickupScheduleDTO.setPickupPointId(1L);
        pickupScheduleDTO.setUserId(1L);
    
        PickupPoint pickupPoint = new PickupPoint();
        when(pickupPointRepository.findById(pickupScheduleDTO.getPickupPointId())).thenReturn(Optional.of(pickupPoint));
    
        User user = new User();
        when(userRepository.findById(pickupScheduleDTO.getUserId())).thenReturn(Optional.of(user));
    
        PickupSchedule savedPickupSchedule = new PickupSchedule();
        when(pickupScheduleRepository.save(any(PickupSchedule.class))).thenReturn(savedPickupSchedule);
    
        // Act
        PickupSchedule result = pickupScheduleService.addPickupSchedule(pickupScheduleDTO);
    
        // Assert
        assertEquals(savedPickupSchedule, result);
    
        verify(pickupPointRepository, times(1)).findById(pickupScheduleDTO.getPickupPointId());
        verify(userRepository, times(1)).findById(pickupScheduleDTO.getUserId());
        verify(pickupScheduleRepository, times(1)).save(any(PickupSchedule.class));
    }
    

    @Test
    public void testDeletePickupScheduleById_ExistingId_ReturnsDeletedPickupSchedule()
            throws ResourceNotFoundException {
        // Arrange
        long id = 1L;
        PickupSchedule expectedSchedule = new PickupSchedule();
        when(pickupScheduleRepository.findById(id)).thenReturn(Optional.of(expectedSchedule));

        // Act
        PickupSchedule actualSchedule = pickupScheduleService.deletePickupScheduleById(id);

        // Assert
        assertEquals(expectedSchedule, actualSchedule);
        verify(pickupScheduleRepository, times(1)).findById(id);
        verify(pickupScheduleRepository, times(1)).deleteById(id);
    }

    @Test
    public void testDeletePickupScheduleById_NonExistingId_ThrowsResourceNotFoundException() {
        // Arrange
        long id = 1L;
        when(pickupScheduleRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            pickupScheduleService.deletePickupScheduleById(id);
        });
        verify(pickupScheduleRepository, times(1)).findById(id);
        verify(pickupScheduleRepository, times(0)).deleteById(anyLong());
    }

    @Test
public void testPickupScheduleFromDTO_ValidDTO_ReturnsMappedPickupSchedule() throws ResourceNotFoundException {
    // Arrange
    PickupScheduleDTO pickupScheduleDTO = new PickupScheduleDTO(null, null, null, null, null);
    pickupScheduleDTO.setCode(123L);
    pickupScheduleDTO.setAvailability(true);
    pickupScheduleDTO.setPickupPointId(1L);
    pickupScheduleDTO.setUserId(1L);

    PickupPoint pickupPoint = new PickupPoint();
    when(pickupPointRepository.findById(pickupScheduleDTO.getPickupPointId())).thenReturn(Optional.of(pickupPoint));

    User user = new User();
    when(userRepository.findById(pickupScheduleDTO.getUserId())).thenReturn(Optional.of(user));

    // Act
    PickupSchedule pickupSchedule = pickupScheduleService.pickupScheduleFromDTO(pickupScheduleDTO);

    // Assert
    assertEquals(pickupScheduleDTO.getCode(), pickupSchedule.getCode());
    assertEquals(pickupScheduleDTO.getAvailability(), pickupSchedule.getAvailability());
    assertEquals(pickupPoint, pickupSchedule.getPickupPoint());
    assertEquals(user, pickupSchedule.getUser());

    verify(pickupPointRepository, times(1)).findById(pickupScheduleDTO.getPickupPointId());
    verify(userRepository, times(1)).findById(pickupScheduleDTO.getUserId());
    }
}
