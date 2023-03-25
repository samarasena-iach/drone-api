package com.musalasoft.dronesapi.service;

import com.musalasoft.dronesapi.dto.request.RequestDTO_LoadDroneWithMedications;
import com.musalasoft.dronesapi.dto.response.ResponseDTO_LoadDroneWithMedications;
import com.musalasoft.dronesapi.exception.DispatchServiceException;
import com.musalasoft.dronesapi.model.Drone;
import com.musalasoft.dronesapi.model.Medication;
import com.musalasoft.dronesapi.model.MedicationDelivery;
import com.musalasoft.dronesapi.repository.DroneRepository;
import com.musalasoft.dronesapi.repository.MedicationDeliveryRepository;
import com.musalasoft.dronesapi.repository.MedicationRepository;
import com.musalasoft.dronesapi.utility.Constant;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class DispatchServiceImpl implements DispatchService {
    private final DroneRepository droneRepository;
    private final MedicationRepository medicationRepository;
    private final MedicationDeliveryRepository medicationDeliveryRepository;

    @Override
    public ResponseDTO_LoadDroneWithMedications loadDroneWithMedications(RequestDTO_LoadDroneWithMedications requestDTO_loadDroneWithMedications) throws DispatchServiceException {
        try {
            log.info("DispatchService::loadDroneWithMedications execution started.");
            log.debug("DispatchService::loadDroneWithMedications request parameters {}", requestDTO_loadDroneWithMedications);

            // Preload Medications Data

            Drone drone = droneRepository.findBySerialNumber(requestDTO_loadDroneWithMedications.getSerialNumber());
            Medication medication = medicationRepository.findByCode(requestDTO_loadDroneWithMedications.getCode());

            double droneBatteryCapacity;
            if (drone == null) {
                throw new DispatchServiceException("NO EXISTING DRONE FOR SERIAL NUMBER [" + requestDTO_loadDroneWithMedications.getSerialNumber() + "]");
            } else {
                droneBatteryCapacity = drone.getBatteryCapacity();
            }

            if (medication == null) {
                throw new DispatchServiceException("NO EXISTING MEDICATION FOR CODE [" + requestDTO_loadDroneWithMedications.getCode() + "]");
            }

            boolean existingMedicationInDrone = medicationDeliveryRepository.findByDroneAndMedication(drone, medication) != null;
            if (existingMedicationInDrone) {
                throw new DispatchServiceException("DRONE IS ALREADY LOADED WITH THE MEDICATION [" + medication.getCode() + "]");
            }

            boolean isDroneWeightLimitExceeded = isDroneWeightLimitExceeded(drone, medication);
            boolean isDroneBatteryLow = droneBatteryCapacity < Constant.DRONE_BATTERY_CAPACITY;

            if (isDroneBatteryLow) {
                throw new DispatchServiceException("DRONE'S BATTERY CAPACITY IS LOW");
            }

            if (!isDroneWeightLimitExceeded) {
                // UPDATING DRONE STATE FROM 'IDLE' TO 'LOADING'
                droneRepository.updateDroneState("LOADING", requestDTO_loadDroneWithMedications.getSerialNumber());

                MedicationDelivery medicationDelivery = new MedicationDelivery();
                medicationDelivery.setDrone(drone);
                medicationDelivery.setMedication(medication);
                medicationDelivery.setAddedOn(java.time.LocalDateTime.now());
                MedicationDelivery newMedicationDelivery = medicationDeliveryRepository.save(medicationDelivery);

                // UPDATING DRONE STATE FROM 'LOADING' TO 'LOADED'
                droneRepository.updateDroneState("LOADED", drone.getSerialNumber());

                ResponseDTO_LoadDroneWithMedications medicationDeliveryResponse = new ResponseDTO_LoadDroneWithMedications();
                medicationDeliveryResponse.setId(newMedicationDelivery.getId());
                medicationDeliveryResponse.setSerialNumber(newMedicationDelivery.getDrone().getSerialNumber());
                medicationDeliveryResponse.setCode(newMedicationDelivery.getMedication().getCode());

                log.info("DispatchService::loadDroneWithMedications execution ended");
                return medicationDeliveryResponse;
            }
        } catch (Exception ex) {
            log.error("DispatchService::loadDroneWithMedications exception {}", ex.getMessage());
            ex.printStackTrace();
        }

        log.info("DispatchService::loadDroneWithMedications execution ended with null return");
        return null;
    }

    public boolean isDroneWeightLimitExceeded(Drone drone, Medication loadedMedication) throws DispatchServiceException {
        boolean isDroneWeightLimitExceeded = false;

        List<MedicationDelivery> alreadyExistingMedicationsInDrone = medicationDeliveryRepository.findAllByDrone(drone);
        double existingMedicationsWeight = alreadyExistingMedicationsInDrone.stream().mapToDouble(existingMedication -> existingMedication.getMedication().getWeight()).sum();

        if ((existingMedicationsWeight + loadedMedication.getWeight()) > drone.getWeightLimit()) {
            isDroneWeightLimitExceeded = true;
            throw new DispatchServiceException("DRONE'S MAXIMUM WEIGHT LIMIT HAS REACHED!");
        }

        return isDroneWeightLimitExceeded;
    }
}
