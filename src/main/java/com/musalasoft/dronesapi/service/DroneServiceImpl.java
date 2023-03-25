package com.musalasoft.dronesapi.service;

import com.musalasoft.dronesapi.dto.request.RequestDTO_DroneRegistration;
import com.musalasoft.dronesapi.dto.response.ResponseDTO_CheckAvailableDronesForLoading;
import com.musalasoft.dronesapi.dto.response.ResponseDTO_DroneRegistration;
import com.musalasoft.dronesapi.exception.DispatchServiceException;
import com.musalasoft.dronesapi.exception.DroneServiceException;
import com.musalasoft.dronesapi.model.Drone;
import com.musalasoft.dronesapi.repository.DroneRepository;
import com.musalasoft.dronesapi.utility.Constant;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
                ResponseDTO_DroneRegistration responseDTO_droneRegistration = new ResponseDTO_DroneRegistration();
                responseDTO_droneRegistration.setId(newDrone.getId());
                responseDTO_droneRegistration.setSerialNumber(newDrone.getSerialNumber());
                responseDTO_droneRegistration.setModel(newDrone.getModel());
                responseDTO_droneRegistration.setName(newDrone.getName());
                responseDTO_droneRegistration.setWeightLimit(newDrone.getWeightLimit());
                responseDTO_droneRegistration.setBatteryCapacity(newDrone.getBatteryCapacity());
                responseDTO_droneRegistration.setState(newDrone.getState());
                return responseDTO_droneRegistration;
            }
        } catch (Exception ex) {
            log.error("DroneService::registerDrone exception {}", ex.getMessage());
        }

        log.info("DroneService::registerDrone execution ended.");
        return null;
    }

    @Override
    public ResponseDTO_CheckAvailableDronesForLoading checkAvailableDronesForLoading() throws DroneServiceException{
        try {
            log.info("DroneService::checkAvailableDronesForLoading execution started.");
            log.debug("DroneService::checkAvailableDronesForLoading request parameters");

            List<Drone> listOfIdleDrones = droneRepository.findAllByState(Constant.IDLE);

            if (listOfIdleDrones.isEmpty()) {
                throw new DroneServiceException("NO DRONES ARE AVAILABLE FOR LOADING!");
            }

            ResponseDTO_CheckAvailableDronesForLoading responseDTO_checkAvailableDronesForLoading = new ResponseDTO_CheckAvailableDronesForLoading();
            responseDTO_checkAvailableDronesForLoading.setDroneList(listOfIdleDrones);

            log.info("DroneService::checkAvailableDronesForLoading execution ended");
            return responseDTO_checkAvailableDronesForLoading;
        } catch (Exception ex) {
            log.error("DroneService::checkAvailableDronesForLoading exception {}", ex.getMessage());
            ex.printStackTrace();
        }

        log.info("DroneService::checkAvailableDronesForLoading execution ended with null return");
        return null;
    }
}
