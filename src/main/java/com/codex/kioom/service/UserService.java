package com.codex.kioom.service;

import com.codex.kioom.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface UserService {
	Map<String, Object> userLogin(Map<String, Object> param); // 사용자 로그인
	int getUserIdYn(Map<String, Object> param); // 아이디 중복 검사
	void insertUser(Map<String, Object> param); // 병원 계정 등록
	void insertExcelUser(Map<String, Object> param); // 병원 계정 등록
	int updateUser(Map<String, Object> param); // 병원 정보 수정
	List<Map<String, Object>> getHospitalList(Map<String, Object> param);
	void deleteHospitalList(Map<String, Object> param);
	int getHospitalListCnt(Map<String, Object> param);
	Map<String, Object> selectUserInfo(Map<String, Object> param);
	UserDTO selectUserInfo(UserDTO userDTO);
	List<String> selectUserId();

}
