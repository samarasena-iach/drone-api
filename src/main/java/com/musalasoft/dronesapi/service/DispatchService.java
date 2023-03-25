package com.musalasoft.dronesapi.service;

import com.musalasoft.dronesapi.dto.request.RequestDTO_LoadDroneWithMedications;
import com.musalasoft.dronesapi.dto.response.ResponseDTO_LoadDroneWithMedications;
import com.musalasoft.dronesapi.exception.DispatchServiceException;

public interface DispatchService {

    ResponseDTO_LoadDroneWithMedications loadDroneWithMedications(RequestDTO_LoadDroneWithMedications requestDTO_loadDroneWithMedications) throws DispatchServiceException;

}
