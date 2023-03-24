package com.musalasoft.dronesapi.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO_DroneRegistration {
    private Long id;
    private String serialNumber;
    private String model;
    private String name;
    private double weightLimit;
    private double batteryCapacity;
    private String state;
}
