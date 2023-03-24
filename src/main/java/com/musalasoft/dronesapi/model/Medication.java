package com.musalasoft.dronesapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "medication")
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Min(1)
    @Max(500)
    @Column(name = "weight")
    private double weight;

    @Column(name = "code")
    private String code;

    @Column(name = "image")
    private String image;

}
