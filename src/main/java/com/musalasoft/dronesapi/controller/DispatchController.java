package com.musalasoft.dronesapi.controller;

import com.musalasoft.dronesapi.dto.APIResponse;
import com.musalasoft.dronesapi.dto.request.RequestDTO_LoadDroneWithMedications;
import com.musalasoft.dronesapi.dto.response.ResponseDTO_DroneRegistration;
import com.musalasoft.dronesapi.dto.response.ResponseDTO_LoadDroneWithMedications;
import com.musalasoft.dronesapi.exception.DispatchServiceException;
import com.musalasoft.dronesapi.service.DispatchService;
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
@RequestMapping("/api/v1/dispatch")
@AllArgsConstructor
@Slf4j
public class DispatchController {
    public static final String SUCCESS = "Success";
    private DispatchService dispatchService;

    // LOADING A DRONE WITH MEDICATION ITEMS
    @PostMapping(path = "/load_medications", consumes = "application/json", produces = "application/json")
    public ResponseEntity<APIResponse> loadDroneWithMedications(@Valid @NotNull @RequestBody RequestDTO_LoadDroneWithMedications requestDTO_loadDroneWithMedications) throws DispatchServiceException {
        log.info("DispatchController::loadDroneWithMedications request body {}", requestDTO_loadDroneWithMedications);

        ResponseDTO_LoadDroneWithMedications responseDTO_loadDroneWithMedications = dispatchService.loadDroneWithMedications(requestDTO_loadDroneWithMedications);

        APIResponse<ResponseDTO_LoadDroneWithMedications> responseDTO = APIResponse.<ResponseDTO_LoadDroneWithMedications>builder()
                .status(SUCCESS)
                .message("SUCCESSFULLY LOADED DRONE WITH MEDICATION(S)!")
                .results(responseDTO_loadDroneWithMedications)
                .timestamp(java.time.LocalDateTime.now())
                .build();

        log.info("DispatchController::loadDroneWithMedications response {}", responseDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    // CHECKING LOADED MEDICATION ITEMS FOR A GIVEN DRONE

}
