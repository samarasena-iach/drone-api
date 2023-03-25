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
public class RequestDTO_SaveMedication {
    @NotBlank(message = "NAME should not be blank")
    @NotNull(message = "NAME should not be null")
    private String name;

    @NotBlank(message = "CODE should not be blank")
    @NotNull(message = "CODE should not be null")
    private String code;

    @NotNull
    private double weight;

    private String image;
}
