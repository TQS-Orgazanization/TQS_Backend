package com.tqs.pickuppointbackend.service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;

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

    public PickupSchedule addPickupSchedule(PickupScheduleDTO pickupScheduleDTO) throws ResourceNotFoundException, NoSuchAlgorithmException{

        PickupSchedule pickupSchedule = pickupScheduleFromDTO(pickupScheduleDTO);
        
        String code = generateRandomCode();
        pickupSchedule.setCode(Long.parseLong(code));

        return pickupScheduleRepository.save(pickupSchedule);
        
    }

    public List<PickupSchedule> getPickupScheduleUserById(long id) {

        return pickupScheduleRepository.findByUserId(id);
    }

    public List<PickupSchedule> getAvailablePickupSchedules() throws ResourceNotFoundException {
        return pickupScheduleRepository.findByAvailability(true);
    }


    public List<PickupSchedule> getAvailablePickupSchedulesByPickupPointId(long id) throws ResourceNotFoundException {
        return pickupScheduleRepository.findByAvailabilityByPickupPointId(true, id);
    }

    public List<PickupSchedule> getNonAvailablePickupSchedules() throws ResourceNotFoundException {
        return pickupScheduleRepository.findByAvailability(false);
    }

    public List<PickupSchedule> getNonAvailablePickupSchedulesByPickupPointId(long id) throws ResourceNotFoundException {
        return pickupScheduleRepository.findByAvailabilityByPickupPointId(false, id);
    }

    public PickupSchedule updatePickupSchedule(PickupScheduleDTO pickupScheduleDTO) throws ResourceNotFoundException {

        PickupSchedule pickupSchedule = pickupScheduleFromDTO(pickupScheduleDTO);

        PickupSchedule existingPickupSchedule = pickupScheduleRepository.findById(pickupSchedule.getId()).orElseThrow(() -> new ResourceNotFoundException("Pickup Schedule Not Found!"));
        
        if (existingPickupSchedule == null){ return null; }

        existingPickupSchedule.setCode(pickupSchedule.getCode());
        existingPickupSchedule.setAvailability(pickupSchedule.getAvailability());
        
        return pickupScheduleRepository.save(existingPickupSchedule);
    }

    public PickupSchedule deletePickupScheduleById(long id) throws ResourceNotFoundException {
        PickupSchedule pickupSchedule = pickupScheduleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pickup Schedule Not Found!"));
        
        pickupScheduleRepository.deleteById(id);

        return pickupSchedule;
        
    }

    public PickupSchedule pickupScheduleFromDTO(PickupScheduleDTO pickupScheduleDTO) throws ResourceNotFoundException {

        PickupSchedule pickupSchedule = new PickupSchedule();
        pickupSchedule.setCode(pickupScheduleDTO.getCode());
        pickupSchedule.setAvailability(pickupScheduleDTO.getAvailability());


        PickupPoint pickupPoint = pickupPointRepository.findById(pickupScheduleDTO.getPickupPointId()).orElseThrow(() -> new ResourceNotFoundException("Pickup Point Not Found!"));
        User user = userRepository.findById(pickupScheduleDTO.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User Not Found!"));

        pickupSchedule.setPickupPoint(pickupPoint);
        pickupSchedule.setUser(user);


        /*if(pickupScheduleDTO.getId() != null) {
            pickupSchedule.setId(pickupScheduleDTO.getId());
        }*/

        return pickupSchedule;

    }


    public static String generateRandomCode() throws NoSuchAlgorithmException{
        Random rand = SecureRandom.getInstanceStrong();
        int code = rand.nextInt(900000) + 100000; // Generates a random number between 100000 and 999999
        return String.valueOf(code);
    }

}
