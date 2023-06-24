package com.tqs.pickuppointbackend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.tqs.pickuppointbackend.controller.model.UpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tqs.pickuppointbackend.exceptions.ResourceNotFoundException;
import com.tqs.pickuppointbackend.model.PickupPoint;
import com.tqs.pickuppointbackend.model.PickupSchedule;
import com.tqs.pickuppointbackend.model.Dto.NotificationDTO;
import com.tqs.pickuppointbackend.model.Dto.PickupPointDTO;
import com.tqs.pickuppointbackend.repository.PickupPointRepository;

@Service
public class PickupPointService {
    
    @Autowired
    PickupPointRepository pickupPointRepository;

    @Autowired
    NotificationService notificationService;

    Utils utils = new Utils();



    public List<PickupPoint> getPickupPoints() {
        return pickupPointRepository.findAll();
    }

    public PickupPoint getPickupPointById(long id) throws ResourceNotFoundException {
        return pickupPointRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pickup Point Not Found!"));
    }

    public PickupPoint getPickupPointByUserId(long id) throws ResourceNotFoundException {
        PickupPoint pick = pickupPointRepository.findByUserId(id);
        if (pick != null) {
            return pick;
        }
        throw new ResourceNotFoundException("This pickup doesn't exist");
    }

    public List<PickupPoint> getAvailablePickupPoints() throws ResourceNotFoundException {
        return pickupPointRepository.findByAvailability(true);
    }

    public List<PickupPoint> getNonAvailablePickupPoints() throws ResourceNotFoundException {
        return pickupPointRepository.findByAvailability(false);
    }

    public PickupPoint addPickupPoint(PickupPointDTO pickupPointDTO) {

        PickupPoint pickupPoint = utils.pickupPointFromDTO(pickupPointDTO);
        pickupPoint.setPickupSchedules(new ArrayList<PickupSchedule>());

        return pickupPointRepository.save(pickupPoint);
    }

    public PickupPoint updatePickupPoint(Long id, UpdateRequest request) throws ResourceNotFoundException {

        PickupPoint pickupPoint = utils.pickupPointFromDTO(pickupPointDTO);
        PickupPoint existingPickupPoint = pickupPointRepository.findById(pickupPoint.getPoint_id()).orElseThrow(() -> new ResourceNotFoundException("Pickup Point Not Found!"));
        
        if (existingPickupPoint == null){ return null; }

        existingPickupPoint.setAvailability(request.isAvailability());

        /*
        existingPickupPoint.setName(pickupPoint.getName());
        existingPickupPoint.setAddress(pickupPoint.getAddress());
        existingPickupPoint.setContactInfo(pickupPoint.getContactInfo());
        existingPickupPoint.setAvailability(pickupPoint.isAvailability());
        existingPickupPoint.setUser_id(pickupPoint.getUser_id());*/

        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setMessage("ACP accepted");
        notificationDTO.setUserId(existingPickupPoint.getUser_id());

        notificationService.addNotification(notificationDTO);

     
        return pickupPointRepository.save(existingPickupPoint);
    }

    public PickupPoint deletePickupPointById(long id) throws ResourceNotFoundException {
        PickupPoint pickupPoint = pickupPointRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pickup Point Not Found!"));
        
        pickupPointRepository.deleteById(id);

        return pickupPoint;
        
    }

    /*
    public PickupPoint pickupPointFromDTO(PickupPointDTO pickupPointDTO) {

        PickupPoint pickupPoint = new PickupPoint();
        pickupPoint.setName(pickupPointDTO.getName());
        pickupPoint.setAddress(pickupPointDTO.getAddress());
        pickupPoint.setContactInfo(pickupPointDTO.getContactInfo());
        pickupPoint.setAvailability(pickupPointDTO.isAvailability());
        pickupPoint.setUser_id(pickupPointDTO.getUser_id());
        System.out.print(pickupPointDTO.getPoint_id());
        if(pickupPointDTO.getPoint_id() != null) {
            pickupPoint.setPoint_id(pickupPointDTO.getPoint_id());
        }

        return pickupPoint;

    }
    */

}
