package com.tqs.pickuppointbackend.service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tqs.pickuppointbackend.exceptions.ResourceNotFoundException;
import com.tqs.pickuppointbackend.model.PickupPoint;
import com.tqs.pickuppointbackend.model.PickupSchedule;
import com.tqs.pickuppointbackend.model.User;
import com.tqs.pickuppointbackend.model.Dto.NotificationDTO;
import com.tqs.pickuppointbackend.model.Dto.PickupScheduleDTO;
import com.tqs.pickuppointbackend.repository.NotificationRepository;
import com.tqs.pickuppointbackend.repository.PickupPointRepository;
import com.tqs.pickuppointbackend.repository.PickupScheduleRepository;
import com.tqs.pickuppointbackend.repository.UserRepository;


@Service
@Log4j2
public class PickupScheduleService {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    PickupScheduleRepository pickupScheduleRepository;

    @Autowired
    PickupPointRepository pickupPointRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    NotificationService notificationService;

    Utils utils = new Utils();

    public List<PickupSchedule> getPickupSchedules() {
        return pickupScheduleRepository.findAll();
    }

    public PickupSchedule getPickupScheduleById(long id) throws ResourceNotFoundException {
        return pickupScheduleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pickup Schedule Not Found!"));
    }

    public PickupSchedule addPickupSchedule(PickupScheduleDTO pickupScheduleDTO) throws ResourceNotFoundException, NoSuchAlgorithmException{


        //PickupSchedule pickupSchedule = utils.pickupScheduleFromDTO(pickupScheduleDTO);
        
        //String code = Utils.generateRandomCode();
        //pickupSchedule.setCode(Long.parseLong(code));

        Optional<PickupPoint> pickup = pickupPointRepository.findById(pickupScheduleDTO.getPickupPointId());
        Optional<User> user = userRepository.findById(pickupScheduleDTO.getUserId());

        if (pickup.isEmpty() || user.isEmpty()){
            throw new ResourceNotFoundException("PickupPoint or User not found");
        }

        PickupSchedule pickSchedule = PickupSchedule.builder().pickupPoint(pickup.get()).user(user.get()).availability(true).build();

        log.info("End:" + pickSchedule);
        return pickupScheduleRepository.save(pickSchedule);
    }

    public List<PickupSchedule> getPickupScheduleUserById(long id) {

        return pickupScheduleRepository.findByUserId(id);
    }

    public List<PickupSchedule> getAvailablePickupSchedules() throws ResourceNotFoundException {
        return pickupScheduleRepository.findByAvailability(true);
    }


    public List<PickupSchedule> getAvailablePickupSchedulesByPickupPointId(long id) throws ResourceNotFoundException {
        Optional<PickupPoint> pickupoint = pickupPointRepository.findById(id);

        if (pickupoint.isEmpty()){
            throw new ResourceNotFoundException("PickupPoint not found");
        }

        return pickupScheduleRepository.findByAvailabilityAndPickupPoint(true, pickupoint.get());
    }

    public List<PickupSchedule> getNonAvailablePickupSchedules() throws ResourceNotFoundException {
        return pickupScheduleRepository.findByAvailability(false);
    }

    public List<PickupSchedule> getNonAvailablePickupSchedulesByPickupPointId(long id) throws ResourceNotFoundException {

        Optional<PickupPoint> pickupoint = pickupPointRepository.findById(id);

        if (pickupoint.isEmpty()){
            throw new ResourceNotFoundException("PickupPoint not found");
        }

        return pickupScheduleRepository.findByAvailabilityAndPickupPoint(false, pickupoint.get());
    }

    public PickupSchedule updatePickupSchedule(Long id, PickupScheduleDTO pickupScheduleDTO) throws ResourceNotFoundException {

        //PickupSchedule pickupSchedule = utils.pickupScheduleFromDTO(pickupScheduleDTO);
        //PickupSchedule existingPickupSchedule = pickupScheduleRepository.findById(pickupSchedule.getId()).orElseThrow(() -> new ResourceNotFoundException("Pickup Schedule Not Found!"));
        
        //if (existingPickupSchedule == null){ return null; }

        //existingPickupSchedule.setCode(pickupSchedule.getCode());
        //existingPickupSchedule.setAvailability(pickupSchedule.getAvailability());

        Optional<PickupSchedule> pointSchedule = pickupScheduleRepository.findById(id);

        if (pointSchedule.isEmpty()){ throw new ResourceNotFoundException("PickupPoint not found"); }

        PickupSchedule pick = pointSchedule.get();
        pick.setAvailability(pickupScheduleDTO.getAvailability());

        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setMessage("Package Received");

        notificationService.addNotification(notificationDTO);

        return pickupScheduleRepository.save(pick);
    }

    public PickupSchedule deletePickupScheduleById(long id) throws ResourceNotFoundException {
        PickupSchedule pickupSchedule = pickupScheduleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pickup Schedule Not Found!"));
        
        pickupScheduleRepository.deleteById(id);

        return pickupSchedule;
        
    }

    /*
    public PickupSchedule pickupScheduleFromDTO(PickupScheduleDTO pickupScheduleDTO) throws ResourceNotFoundException {

        PickupSchedule pickupSchedule = new PickupSchedule();
        pickupSchedule.setCode(pickupScheduleDTO.getCode());
        pickupSchedule.setAvailability(pickupScheduleDTO.getAvailability());
        System.out.println(pickupScheduleDTO.getId());

        PickupPoint pickupPoint = pickupPointRepository.findById(pickupScheduleDTO.getPickupPointId()).orElseThrow(() -> new ResourceNotFoundException("Pickup Point Not Found!"));
        User user = userRepository.findById(pickupScheduleDTO.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User Not Found!"));

        pickupSchedule.setPickupPoint(pickupPoint);
        pickupSchedule.setUser(user);


        if(pickupScheduleDTO.getId() != null) {
            pickupSchedule.setId(pickupScheduleDTO.getId());
        }

        return pickupSchedule;

    }
     */

    
}
