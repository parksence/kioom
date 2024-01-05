package com.codex.kioom.service;

import com.codex.kioom.dao.PatientDAO;
import com.codex.kioom.dto.PatientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientDAO patientDAO;

    @Override
    public List<Map<String, Object>> getPatientList(Map<String, Object> param) {
        List<Map<String, Object>> list = patientDAO.getPatientList(param);
        return list;
    }

    @Override
    public int getPatientListCnt(Map<String, Object> param) {
        int totalCnt = patientDAO.getPatientListCnt(param);
        return totalCnt;
    }

    @Override
    public PatientDTO selectPatientInfo(PatientDTO patientDTO) {
        PatientDTO patientInfo = patientDAO.selectPatientInfo(patientDTO);
        return patientInfo;
    }

    @Override
    public List<PatientDTO> selectPatientInfoList(PatientDTO patientDTO) {
        List<PatientDTO> patientInfolist = patientDAO.selectPatientInfoList(patientDTO);
        return patientInfolist;
    }


}
