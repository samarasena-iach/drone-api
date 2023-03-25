package com.musalasoft.dronesapi;

import com.musalasoft.dronesapi.model.Drone;
import com.musalasoft.dronesapi.model.Medication;
import com.musalasoft.dronesapi.repository.DroneRepository;
import com.musalasoft.dronesapi.repository.MedicationRepository;
import lombok.AllArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestRepositories {

    @Autowired
    DroneRepository droneRepository;

    @Autowired
    MedicationRepository medicationRepository;

    @Test
    public void testRegisterDrone() {
        Drone drone1 = new Drone(1L, "SN00001", "Lightweight", "DJI Air 2S", 250.0, 100.0, "IDLE");
        Drone drone2 = new Drone(2L, "SN00002", "Lightweight", "DJI Air 3S", 375.0, 100.0, "IDLE");
        Drone drone3 = new Drone(3L, "SN00003", "Middleweight", "DJI Air 5S", 495.0, 100.0, "IDLE");
        droneRepository.save(drone1);
        droneRepository.save(drone2);
        droneRepository.save(drone3);

        Iterable<Drone> drones = droneRepository.findAll();
        Assertions.assertThat(drones).extracting(Drone::getSerialNumber).contains(drone1.getSerialNumber());

        droneRepository.deleteAll();
        Assertions.assertThat(droneRepository.findAll()).isEmpty();
    }

    @Test
    public void testSaveMedication() {
        Medication medication1 = new Medication(1L, "NexoBrid", "MEDC0001", 15.0, "image/NexoBrid.jpeg");
        Medication medication2 = new Medication(2L, "Briumvi", "MEDC0002", 19.0, "image/Briumvi.jpeg");
        Medication medication3 = new Medication(3L, "Xenoview", "MEDC0003", 25.0, "image/Xenoview.jpeg");
        Medication medication4 = new Medication(4L, "Lunsumio", "MEDC0004", 10.0, "image/Lunsumio.jpeg");
        Medication medication5 = new Medication(5L, "Sunlenca", "MEDC0005", 11.0, "image/Sunlenca.jpeg");
        medicationRepository.save(medication1);
        medicationRepository.save(medication2);
        medicationRepository.save(medication3);
        medicationRepository.save(medication4);
        medicationRepository.save(medication5);

        Iterable<Medication> medications = medicationRepository.findAll();
        Assertions.assertThat(medications).extracting(Medication::getCode).contains(medication4.getCode());

        droneRepository.deleteAll();
        Assertions.assertThat(droneRepository.findAll()).isEmpty();
    }
}
