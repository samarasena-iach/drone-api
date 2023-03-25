package com.musalasoft.dronesapi.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.musalasoft.dronesapi.model.Medication;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO_CheckLoadedMedications {
    private String serialNumber;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Medication> medicationList;
}
