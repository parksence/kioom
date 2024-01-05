package com.codex.kioom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class PatientDTO {
    private int IDX;
    private String H_NAME;
    private String PATIENT_ID;
    private String PATIENT_NAME;
    private String GENDER;
    private String BIRTH;
    private String TEST_DATE;
    private String TEST_TIME;
    private String TEST_NAME;
    private String POSITION;
    private int PULSE_RATE;
    private int PULSE_POWER;
    private int PULSE_DEPTH;
    private int PULSE_SHAPE;
    private int R_AIX;
    private int SBP;
    private int DBP;
    private int MBP;
    private int SV;
    private int SVI;
    private int CO;
    private int SVRI;

    private PatientDTO patientDTO;
    private List<PatientDTO> patientDTOList;

}
