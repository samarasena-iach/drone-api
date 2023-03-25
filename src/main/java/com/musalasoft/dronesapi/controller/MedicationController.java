package com.musalasoft.dronesapi.controller;

import com.musalasoft.dronesapi.dto.APIResponse;
import com.musalasoft.dronesapi.dto.request.RequestDTO_SaveMedication;
import com.musalasoft.dronesapi.dto.response.ResponseDTO_SaveMedication;
import com.musalasoft.dronesapi.service.MedicationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/medications")
@AllArgsConstructor
@Slf4j
@Validated
public class MedicationController {
    public static final String SUCCESS = "Success";
    private MedicationService medicationService;

    // SAVING A MEDICATION
    @PostMapping(path = "/save", consumes = "application/json", produces = "application/json")
    public ResponseEntity<APIResponse> saveMedication(
            @Valid @NotNull @RequestBody RequestDTO_SaveMedication requestDTO_saveMedication) {
        log.info("MedicationController::saveMedication request body {}", requestDTO_saveMedication);

        ResponseDTO_SaveMedication responseDTO_saveMedication = medicationService.saveMedication(requestDTO_saveMedication);

        APIResponse<ResponseDTO_SaveMedication> responseDTO = APIResponse.<ResponseDTO_SaveMedication>builder()
                .status(SUCCESS)
                .message("SUCCESSFULLY SAVED MEDICATION!")
                .results(responseDTO_saveMedication)
                .timestamp(java.time.LocalDateTime.now())
                .build();

        log.info("MedicationController::saveMedication response {}", responseDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    // SAVING MULTIPLE MEDICATION (LIST)
    @PostMapping(path = "/save_all", consumes = "application/json", produces = "application/json")
    public ResponseEntity<APIResponse> saveAllMedication(
            @Valid @NotNull @RequestBody List<RequestDTO_SaveMedication> requestDTO_saveMedicationList) {
        log.info("MedicationController::saveAllMedication request body {}", requestDTO_saveMedicationList);

        List<ResponseDTO_SaveMedication> responseDTO_saveMedicationList = medicationService.saveAllMedication(requestDTO_saveMedicationList);

        APIResponse<List<ResponseDTO_SaveMedication>> responseDTO = APIResponse.<List<ResponseDTO_SaveMedication>>builder()
                .status(SUCCESS)
                .message("SUCCESSFULLY SAVED ALL MEDICATION!")
                .results(responseDTO_saveMedicationList)
                .timestamp(java.time.LocalDateTime.now())
                .build();

        log.info("MedicationController::saveAllMedication response {}", responseDTO_saveMedicationList);

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
}
