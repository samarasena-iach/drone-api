package com.musalasoft.dronesapi.service;

import com.musalasoft.dronesapi.dto.request.RequestDTO_SaveMedication;
import com.musalasoft.dronesapi.dto.response.ResponseDTO_SaveMedication;
import com.musalasoft.dronesapi.exception.MedicationServiceException;

import java.util.List;

public interface MedicationService {

    ResponseDTO_SaveMedication saveMedication(RequestDTO_SaveMedication requestDTO_saveMedication) throws MedicationServiceException;

    List<ResponseDTO_SaveMedication> saveAllMedication(List<RequestDTO_SaveMedication> requestDTO_saveMedicationList) throws MedicationServiceException;

}
