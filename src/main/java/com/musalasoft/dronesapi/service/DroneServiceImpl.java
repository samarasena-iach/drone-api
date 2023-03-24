package com.musalasoft.dronesapi.service;

import com.musalasoft.dronesapi.dto.request.RequestDTO_DroneRegistration;
import com.musalasoft.dronesapi.dto.response.ResponseDTO_DroneRegistration;
import com.musalasoft.dronesapi.model.Drone;
import com.musalasoft.dronesapi.repository.DroneRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class DroneServiceImpl implements DroneService {

    private DroneRepository droneRepository;

    @Override
    public ResponseDTO_DroneRegistration register(RequestDTO_DroneRegistration requestDTO_droneRegistration) {
        try {
            log.info("DroneService:register execution started.");
            log.debug("DroneService:register request parameters {}", requestDTO_droneRegistration);

            Drone drone = new Drone();
            drone.setSerialNumber(requestDTO_droneRegistration.getSerialNumber());
            drone.setModel(requestDTO_droneRegistration.getModel());
            drone.setName(requestDTO_droneRegistration.getName());
            drone.setWeightLimit(requestDTO_droneRegistration.getWeightLimit());
            drone.setBatteryCapacity(requestDTO_droneRegistration.getBatteryCapacity());
            drone.setState(requestDTO_droneRegistration.getState());
            Drone newDrone = droneRepository.save(drone);

            if (newDrone != null) {
                ResponseDTO_DroneRegistration droneResponse = new ResponseDTO_DroneRegistration();
                droneResponse.setId(drone.getId());
                droneResponse.setSerialNumber(drone.getSerialNumber());
                droneResponse.setModel(drone.getModel());
                droneResponse.setName(drone.getName());
                droneResponse.setWeightLimit(drone.getWeightLimit());
                droneResponse.setBatteryCapacity(drone.getBatteryCapacity());
                droneResponse.setState(drone.getState());
                return droneResponse;
            }
        } catch (Exception ex) {
            log.error("DroneService:register exception {}", ex.getMessage());
        }

        log.info("DroneService:register execution ended.");
        return null;
    }
}
