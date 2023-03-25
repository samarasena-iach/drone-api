package com.musalasoft.dronesapi.controller;

import com.musalasoft.dronesapi.dto.APIResponse;
import com.musalasoft.dronesapi.dto.request.RequestDTO_DroneRegistration;
import com.musalasoft.dronesapi.dto.response.ResponseDTO_CheckAvailableDronesForLoading;
import com.musalasoft.dronesapi.dto.response.ResponseDTO_CheckBatteryLevel;
import com.musalasoft.dronesapi.dto.response.ResponseDTO_DroneRegistration;
import com.musalasoft.dronesapi.exception.DroneServiceException;
import com.musalasoft.dronesapi.service.DroneService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/drones")
@AllArgsConstructor
@Slf4j
@Validated
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
    @GetMapping(path = "/check_availability", produces = "application/json")
    public ResponseEntity<APIResponse> checkAvailableDronesForLoading() throws DroneServiceException {
        log.info("DroneController::checkAvailableDronesForLoading request body");

        ResponseDTO_CheckAvailableDronesForLoading responseDTO_checkAvailableDronesForLoading = droneService.checkAvailableDronesForLoading();

        APIResponse<ResponseDTO_CheckAvailableDronesForLoading> responseDTO = APIResponse.<ResponseDTO_CheckAvailableDronesForLoading>builder()
                .status(SUCCESS)
                .message("SUCCESSFULLY RETRIEVED LIST OF DRONES AVAILABLE FOR LOADING!")
                .results(responseDTO_checkAvailableDronesForLoading)
                .timestamp(java.time.LocalDateTime.now())
                .build();

        log.info("DroneController::registerDrone response {}", responseDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    // CHECK DRONE BATTERY LEVEL FOR A GIVEN DRONE
    @GetMapping(path = "/check_battery_level/{serialNumber}", produces = "application/json")
    public ResponseEntity<APIResponse> checkBatteryLevel(@PathVariable("serialNumber") String serialNumber) throws DroneServiceException {
        log.info("DispatchController::checkBatteryLevel request body {}", serialNumber);

        ResponseDTO_CheckBatteryLevel responseDTO_checkBatteryLevel = droneService.checkBatteryLevel(serialNumber);

        APIResponse<ResponseDTO_CheckBatteryLevel> responseDTO = APIResponse.<ResponseDTO_CheckBatteryLevel>builder()
                .status(SUCCESS)
                .message("SUCCESSFULLY RETRIEVED THE BATTERY CAPACITY OF DRONE [" + serialNumber + "]")
                .results(responseDTO_checkBatteryLevel)
                .timestamp(java.time.LocalDateTime.now())
                .build();

        log.info("DispatchController::checkBatteryLevel response {}", responseDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
}
