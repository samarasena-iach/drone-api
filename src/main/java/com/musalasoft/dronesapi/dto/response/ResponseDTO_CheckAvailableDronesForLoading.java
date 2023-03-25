package com.musalasoft.dronesapi.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.musalasoft.dronesapi.model.Drone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO_CheckAvailableDronesForLoading {
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Drone> droneList;
}
