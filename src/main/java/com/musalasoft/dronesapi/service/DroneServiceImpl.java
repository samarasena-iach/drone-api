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
    public ResponseDTO_DroneRegistration registerDrone(RequestDTO_DroneRegistration requestDTO_droneRegistration) {
        try {
            log.info("DroneService::registerDrone execution started.");
            log.debug("DroneService::registerDrone request parameters {}", requestDTO_droneRegistration);

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
                droneResponse.setId(newDrone.getId());
                droneResponse.setSerialNumber(newDrone.getSerialNumber());
                droneResponse.setModel(newDrone.getModel());
                droneResponse.setName(newDrone.getName());
                droneResponse.setWeightLimit(newDrone.getWeightLimit());
                droneResponse.setBatteryCapacity(newDrone.getBatteryCapacity());
                droneResponse.setState(newDrone.getState());
                return droneResponse;
            }
        } catch (Exception ex) {
            log.error("DroneService::registerDrone exception {}", ex.getMessage());
        }

        log.info("DroneService::registerDrone execution ended.");
        return null;
    }
}
