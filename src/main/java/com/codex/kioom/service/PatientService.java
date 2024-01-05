package com.codex.kioom.service;

import com.codex.kioom.dto.PatientDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface PatientService {

    List<Map<String, Object>> getPatientList(Map<String, Object> param);
    int getPatientListCnt(Map<String, Object> param);
    PatientDTO selectPatientInfo(PatientDTO patientDTO);
    List<PatientDTO> selectPatientInfoList(PatientDTO patientDTO);
}
