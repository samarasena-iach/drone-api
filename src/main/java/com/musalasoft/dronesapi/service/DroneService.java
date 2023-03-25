package com.musalasoft.dronesapi.service;

import com.musalasoft.dronesapi.dto.request.RequestDTO_DroneRegistration;
import com.musalasoft.dronesapi.dto.response.ResponseDTO_CheckAvailableDronesForLoading;
import com.musalasoft.dronesapi.dto.response.ResponseDTO_CheckBatteryLevel;
import com.musalasoft.dronesapi.dto.response.ResponseDTO_DroneRegistration;
import com.musalasoft.dronesapi.exception.DroneServiceException;

public interface DroneService {

    ResponseDTO_DroneRegistration registerDrone(RequestDTO_DroneRegistration requestDTO_droneRegistration);

    ResponseDTO_CheckAvailableDronesForLoading checkAvailableDronesForLoading() throws DroneServiceException;

    ResponseDTO_CheckBatteryLevel checkBatteryLevel(String serialNumber) throws DroneServiceException;

}
