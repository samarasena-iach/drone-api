package com.musalasoft.dronesapi.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class RequestDTO_DroneRegistration {
    @NotBlank(message = "SERIAL NUMBER should not be blank")
    @NotNull(message = "SERIAL NUMBER should not be null")
    private String serialNumber;

    @NotBlank(message = "MODEL should not be blank")
    @NotNull(message = "MODEL should not be null")
    private String model;

    @NotBlank(message = "NAME should not be blank")
    @NotNull(message = "NAME should not be null")
    private String name;

    @Min(value = 1, message = "Minimum WEIGHT LIMIT is 1g")
    @Max(value = 500, message = "Maximum WEIGHT LIMIT is 500g")
    @NotNull
    private double weightLimit;

    @Min(value = 0, message = "Minimum BATTERY CAPACITY is 0%")
    @Max(value = 100, message = "Maximum BATTERY CAPACITY is 100%")
    @NotNull
    private double batteryCapacity;

    @NotBlank(message = "STATE should not be blank")
    @NotNull(message = "STATE should not be null")
    private String state;
}
