package com.musalasoft.dronesapi.controller;

import com.musalasoft.dronesapi.dto.APIResponse;
import com.musalasoft.dronesapi.dto.request.RequestDTO_DroneRegistration;
import com.musalasoft.dronesapi.dto.response.ResponseDTO_DroneRegistration;
import com.musalasoft.dronesapi.service.DroneService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/drones")
@AllArgsConstructor
@Slf4j
public class DroneController {
    public static final String SUCCESS = "Success";
    private DroneService droneService;

    // REGISTERING A DRONE
    @PostMapping(path = "/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<APIResponse> registerDrone(
            @Valid @NotNull @RequestBody RequestDTO_DroneRegistration requestDTO_droneRegistration) {
        log.info("DroneController::registerDrone request body {}", requestDTO_droneRegistration);

        ResponseDTO_DroneRegistration responseDTO_droneRegistration = droneService.registerDrone(requestDTO_droneRegistration);

        APIResponse<ResponseDTO_DroneRegistration> responseDTO = APIResponse.<ResponseDTO_DroneRegistration>builder()
                .status(SUCCESS)
                .message("SUCCESSFULLY REGISTERED DRONE!")
                .results(responseDTO_droneRegistration)
                .timestamp(java.time.LocalDateTime.now())
                .build();

        log.info("DroneController::registerDrone response {}", responseDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    // CHECKING AVAILABLE DRONES FOR LOADING
    // CHECK DRONE BATTERY LEVEL FOR A GIVEN DRONE
}
