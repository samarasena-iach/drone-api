package com.musalasoft.dronesapi.scheduled_tasks;

import com.musalasoft.dronesapi.model.Drone;
import com.musalasoft.dronesapi.repository.DroneRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@SpringBootApplication
@EnableScheduling
@EnableAsync
@AllArgsConstructor
@Slf4j
public class PeriodicDroneBatteryCheck {
    private final DroneRepository droneRepository;

    @Scheduled(fixedRate = 10000)
    public void scheduleFixedRateTaskAsync() throws InterruptedException {
        log.info("ScheduledTask :: PeriodicDroneBatteryCheck :: scheduleFixedRateTaskAsync execution started.");
        List<Drone> allAvailableDrones = droneRepository.findAll();
        allAvailableDrones.stream().forEach(drone -> log.info("DRONE - SERIAL NUMBER [" + drone.getSerialNumber() + "] >>>> BATTERY CAPACITY AS OF [" + java.time.LocalDateTime.now() + "] - [" + drone.getBatteryCapacity() + "%]"));
        log.info("ScheduledTask :: PeriodicDroneBatteryCheck :: scheduleFixedRateTaskAsync execution ended.");
        Thread.sleep(5000);
    }
}
