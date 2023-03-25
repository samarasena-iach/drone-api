package com.musalasoft.dronesapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "drone")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Drone {

    // For a list of Drones (As dummy data)
    // https://www.pcmag.com/picks/the-best-drones

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "serial_number", length = 100)
    private String serialNumber;

    @Column(name = "model")
    private String model;

    @Column(name = "name")
    private String name;

    @Min(1)
    @Max(500)
    @Column(name = "weight_limit")
    private double weightLimit;

    @Min(0)
    @Max(100)
    @Column(name = "battery_capacity")
    private double batteryCapacity;

    @Column(name = "state")
    private String state;

}
