package com.codex.kioom.dao;

import com.codex.kioom.dto.PatientDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PatientDAO {
    int getPatientListCnt(Map<String, Object> param);
    List<Map<String, Object>> getPatientList(Map<String, Object> param);
    PatientDTO selectPatientInfo(PatientDTO patientDTO);
    List<PatientDTO> selectPatientInfoList(PatientDTO patientDTO);
}
