package com.musalasoft.dronesapi.service;

import com.musalasoft.dronesapi.dto.request.RequestDTO_SaveMedication;
import com.musalasoft.dronesapi.dto.response.ResponseDTO_SaveMedication;
import com.musalasoft.dronesapi.exception.DroneServiceException;
import com.musalasoft.dronesapi.exception.MedicationServiceException;
import com.musalasoft.dronesapi.model.Medication;
import com.musalasoft.dronesapi.repository.MedicationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class MedicationServiceImpl implements MedicationService {

    private MedicationRepository medicationRepository;

    @Override
    public ResponseDTO_SaveMedication saveMedication(RequestDTO_SaveMedication requestDTO_saveMedication) throws MedicationServiceException {
        log.info("MedicationService::saveMedication execution started.");
        log.debug("MedicationService::saveMedication request parameters {}", requestDTO_saveMedication);

        if (medicationRepository.findByCode(requestDTO_saveMedication.getCode()) != null) {
            throw new DroneServiceException("EXISTING MEDICATION FOUND FOR CODE [" + requestDTO_saveMedication.getCode() + "]");
        }

        Medication medication = new Medication();
        medication.setName(requestDTO_saveMedication.getName());
        medication.setCode(requestDTO_saveMedication.getCode());
        medication.setWeight(requestDTO_saveMedication.getWeight());
        medication.setImage(requestDTO_saveMedication.getImage());
        Medication newMedication = medicationRepository.save(medication);

        ResponseDTO_SaveMedication medicationResponse = new ResponseDTO_SaveMedication();
        medicationResponse.setId(newMedication.getId());
        medicationResponse.setName(newMedication.getName());
        medicationResponse.setCode(newMedication.getCode());
        medicationResponse.setWeight(newMedication.getWeight());
        medicationResponse.setImage(newMedication.getImage());

        log.info("MedicationService::saveMedication execution ended");
        return medicationResponse;
    }

    @Override
    public List<ResponseDTO_SaveMedication> saveAllMedication(List<RequestDTO_SaveMedication> requestDTO_saveMedicationList) throws MedicationServiceException {
        log.info("MedicationService::saveAllMedication execution started.");
        log.debug("MedicationService::saveAllMedication request parameters {}", requestDTO_saveMedicationList);

        List<ResponseDTO_SaveMedication> responseDTO_saveMedicationList = new ArrayList<>();
        requestDTO_saveMedicationList.stream().forEach(requestDTO_saveMedication -> {
            if (medicationRepository.findByCode(requestDTO_saveMedication.getCode()) != null) {
                throw new DroneServiceException("EXISTING MEDICATION FOUND FOR CODE [" + requestDTO_saveMedication.getCode() + "]");
            }

            Medication medication = new Medication();
            medication.setName(requestDTO_saveMedication.getName());
            medication.setCode(requestDTO_saveMedication.getCode());
            medication.setWeight(requestDTO_saveMedication.getWeight());
            medication.setImage(requestDTO_saveMedication.getImage());
            Medication newMedication = medicationRepository.save(medication);

            ResponseDTO_SaveMedication medicationResponse = new ResponseDTO_SaveMedication();
            medicationResponse.setId(newMedication.getId());
            medicationResponse.setName(newMedication.getName());
            medicationResponse.setCode(newMedication.getCode());
            medicationResponse.setWeight(newMedication.getWeight());
            medicationResponse.setImage(newMedication.getImage());
            responseDTO_saveMedicationList.add(medicationResponse);
        });

        log.info("MedicationService::saveAllMedication execution ended.");
        return responseDTO_saveMedicationList;
    }
}
