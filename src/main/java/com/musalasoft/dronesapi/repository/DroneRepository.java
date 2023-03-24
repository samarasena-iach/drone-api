package com.musalasoft.dronesapi.repository;

import com.musalasoft.dronesapi.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneRepository extends JpaRepository<Drone, Long> {
}
