package com.musalasoft.dronesapi.service;

import com.musalasoft.dronesapi.dto.request.RequestDTO_DroneRegistration;
import com.musalasoft.dronesapi.dto.response.ResponseDTO_DroneRegistration;

public interface DroneService {

    ResponseDTO_DroneRegistration register(RequestDTO_DroneRegistration requestDTO_droneRegistration);

}
