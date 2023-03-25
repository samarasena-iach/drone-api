package com.musalasoft.dronesapi.repository;

import com.musalasoft.dronesapi.model.Drone;
import com.musalasoft.dronesapi.model.Medication;
import com.musalasoft.dronesapi.model.MedicationDelivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicationDeliveryRepository extends JpaRepository<MedicationDelivery, Long> {

    MedicationDelivery findByDroneAndMedication(Drone drone, Medication medication);

    List<MedicationDelivery> findAllByDrone(Drone drone);
}
