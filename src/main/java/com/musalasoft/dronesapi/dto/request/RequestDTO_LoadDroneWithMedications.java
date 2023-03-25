package com.musalasoft.dronesapi.dto.request;

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
public class RequestDTO_LoadDroneWithMedications {
    @NotBlank(message = "SERIAL NUMBER should not be blank")
    @NotNull(message = "SERIAL NUMBER should not be null")
    private String serialNumber;

    @NotBlank(message = "CODE should not be blank")
    @NotNull(message = "CODE should not be null")
    private String code;

}
