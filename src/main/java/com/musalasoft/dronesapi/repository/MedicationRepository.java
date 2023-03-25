package com.musalasoft.dronesapi.repository;

import com.musalasoft.dronesapi.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepository extends JpaRepository<Medication, Long> {

    Medication findByCode(String code);

}
