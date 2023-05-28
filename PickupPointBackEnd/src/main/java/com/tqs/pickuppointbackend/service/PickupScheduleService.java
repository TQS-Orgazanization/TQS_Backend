package com.tqs.pickuppointbackend.service;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tqs.pickuppointbackend.exceptions.ResourceNotFoundException;
import com.tqs.pickuppointbackend.model.PickupPoint;
import com.tqs.pickuppointbackend.model.PickupSchedule;
import com.tqs.pickuppointbackend.model.User;
import com.tqs.pickuppointbackend.model.Dto.PickupScheduleDTO;
import com.tqs.pickuppointbackend.repository.PickupPointRepository;
import com.tqs.pickuppointbackend.repository.PickupScheduleRepository;
import com.tqs.pickuppointbackend.repository.UserRepository;


@Service
public class PickupScheduleService {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    @Autowired
    PickupScheduleRepository pickupScheduleRepository;

    @Autowired
    PickupPointRepository pickupPointRepository;

    @Autowired
    UserRepository userRepository;

    public List<PickupSchedule> getPickupSchedules() {
        return pickupScheduleRepository.findAll();
    }

    public PickupSchedule getPickupScheduleById(long id) throws ResourceNotFoundException {
        return pickupScheduleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pickup Schedule Not Found!"));
    }

    public PickupSchedule addPickupSchedule(PickupScheduleDTO pickupScheduleDTO) throws ResourceNotFoundException{

        PickupSchedule pickupSchedule = pickupScheduleFromDTO(pickupScheduleDTO);
        return pickupScheduleRepository.save(pickupSchedule);
        
    }

    public PickupSchedule deletePickupScheduleById(long id) throws ResourceNotFoundException {
        PickupSchedule pickupSchedule = pickupScheduleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pickup Schedule Not Found!"));
        
        pickupScheduleRepository.deleteById(id);

        return pickupSchedule;
        
    }

    public PickupSchedule pickupScheduleFromDTO(PickupScheduleDTO pickupScheduleDTO) throws ResourceNotFoundException {

        PickupSchedule pickupSchedule = new PickupSchedule();
        pickupSchedule.setCode(pickupScheduleDTO.getCode());
        pickupSchedule.setStartTime(pickupScheduleDTO.getStartTime());
        pickupSchedule.setEndTime(pickupScheduleDTO.getEndTime());
        pickupSchedule.setAvailabilty(pickupScheduleDTO.getAvailability());


        PickupPoint pickupPoint = pickupPointRepository.findById(pickupScheduleDTO.getPickupPointId()).orElseThrow(() -> new ResourceNotFoundException("Pickup Point Not Found!"));
        User user = userRepository.findById(pickupScheduleDTO.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User Not Found!"));

        pickupSchedule.setPickupPoint(pickupPoint);
        pickupSchedule.setUser(user);


        /*if(pickupScheduleDTO.getId() != null) {
            pickupSchedule.setId(pickupScheduleDTO.getId());
        }*/

        return pickupSchedule;

    }
}
