package com.musalasoft.dronesapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "medication_delivery")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicationDelivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drone", nullable = false)
    private Drone drone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medication", nullable = false)
    private Medication medication;

    @Column(name = "added_on")
    private LocalDateTime addedOn;
}
