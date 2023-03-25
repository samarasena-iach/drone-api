package com.musalasoft.dronesapi.service;

import com.musalasoft.dronesapi.dto.request.RequestDTO_DroneRegistration;
import com.musalasoft.dronesapi.dto.request.RequestDTO_LoadDroneWithMedications;
import com.musalasoft.dronesapi.dto.response.ResponseDTO_DroneRegistration;
import com.musalasoft.dronesapi.dto.response.ResponseDTO_LoadDroneWithMedications;

public interface DroneService {

    ResponseDTO_DroneRegistration registerDrone(RequestDTO_DroneRegistration requestDTO_droneRegistration);

}
