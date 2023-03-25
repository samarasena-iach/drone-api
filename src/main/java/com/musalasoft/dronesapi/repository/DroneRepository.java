package com.musalasoft.dronesapi.repository;

import com.musalasoft.dronesapi.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface DroneRepository extends JpaRepository<Drone, Long> {

    Drone findBySerialNumber(String serialNumber);

    @Modifying
    @Query(value = "UPDATE Drone d SET d.state = :state WHERE d.serialNumber = :serialNumber")
    void updateDroneState(@Param("state") String state, @Param("serialNumber") String serialNumber);

}
