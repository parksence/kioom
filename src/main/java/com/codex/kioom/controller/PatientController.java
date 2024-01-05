package com.codex.kioom.controller;

import com.codex.kioom.common.Pagination;
import com.codex.kioom.config.security.auth.PrincipalDetails;
import com.codex.kioom.dto.PatientDTO;
import com.codex.kioom.service.PatientService;
import com.codex.kioom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/patient", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView patient(ModelAndView modelView, Model model, @AuthenticationPrincipal PrincipalDetails authUser
                              , @RequestParam Map<String, Object> param, @RequestParam(defaultValue = "1") int page) throws Exception {

        // 병원 이름
        modelView.addObject("h_name", (String) authUser.getUsername());
        // 권한 코드
        modelView.addObject("role_cd", (String) authUser.getRoleCd());

        // 최고관리자가 아니면 해당 병원 데이터만 조회하기 위함
        if(!authUser.getRoleNm().equals("최고관리자")) {
            String hospital_nm = (String) authUser.getName();
            param.put("h_name", hospital_nm);
        }

        // 총 게시물 수
        int totalCnt = patientService.getPatientListCnt(param);

        // 생성인자로 총 게시물 수, 현재 페이지를 전달
        Pagination pagination = new Pagination(totalCnt, page);

        // DB select start index
        int startIndex = pagination.getStartIndex();
        // 페이지 당 보여지는 게시글의 최대 개수
        int pageSize = pagination.getPageSize();

        param.put("startIndex", startIndex);
        param.put("pageSize", pageSize);

        // 병원 목록 조회
        modelView.addObject("patient_list", patientService.getPatientList(param));
        modelView.addObject("totalCnt", totalCnt);
        modelView.addObject("pagination", pagination);
        modelView.addObject("page_active", page);

        modelView.setViewName("/web/user/patient_m");

        return modelView;
    }

    // 환자 관리 검색 시 처리
    @RequestMapping(value = "/patient/list", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ModelAndView getPatient(ModelAndView modelView, Model model, @AuthenticationPrincipal PrincipalDetails authUser
                                 , @RequestParam Map<String, Object> param, @RequestParam(defaultValue = "1") int page) throws Exception {

        // 병원 이름
        modelView.addObject("h_name", authUser.getUsername().toString());
        // 권한 코드
        modelView.addObject("role_cd", authUser.getRoleCd().toString());

        // 총 게시물 수
        int totalCnt = patientService.getPatientListCnt(param);

        // 생성인자로 총 게시물 수, 현재 페이지를 전달
        Pagination pagination = new Pagination(totalCnt, page);

        int startIndex = pagination.getStartIndex(); // DB select start index
        int pageSize = pagination.getPageSize(); // 페이지 당 보여지는 게시글의 최대 개수

        param.put("startIndex", startIndex);
        param.put("pageSize", pageSize);

        modelView.addObject("patient_list", patientService.getPatientList(param));
        modelView.addObject("totalCnt", totalCnt);
        modelView.addObject("pagination", pagination);
        modelView.addObject("page_active", page);

        modelView.setViewName("/web/user/patient_d");
        return modelView;
    }

    // 환자 이름 선택시 환자관리 화면으로 이동
    @RequestMapping(value = "/patient/{patient_id}", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView hospitalManagement(Model model, HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> param,
                                           ModelAndView modelView, @AuthenticationPrincipal PrincipalDetails authUser, @PathVariable String patient_id) throws IOException {

        // 권한 코드
        modelView.addObject("role_cd", (String) authUser.getRoleCd());
        // 로그인한 사용자 아이디
        modelView.addObject("user_id", (String) authUser.getUsername());

        // 환자 아이디 전달
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setPATIENT_ID(patient_id);

        // 선택한 환자 정보 조회
        PatientDTO patient_info = patientService.selectPatientInfo(patientDTO);

        // 생체 정보 기록 리스트 조회
        List<PatientDTO> patient_list = patientService.selectPatientInfoList(patientDTO);

        modelView.addObject("patient_info", patient_info);
        modelView.addObject("patient_list", patient_list);

        modelView.setViewName("/web/user/patient_d");
        return modelView;
    }
}
