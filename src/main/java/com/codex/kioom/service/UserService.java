package com.codex.kioom.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface UserService {
	Map<String, Object> userLogin(Map<String, Object> param); // 사용자 로그인
	int getUserIdYn(Map<String, Object> param); // 아이디 중복 검사
	void insertUser(Map<String, Object> param); // 병원 계정 등록

//	Map<String, Object> removeUser(String userCode);
//
//	Map<String, Object> updateUser(Map<String, Object> param);
//
//	Map<String, Object> bookmark(String hpid, String userCode);
//
//	boolean findByBookMark(String object, CustomUserDetails authUser);
//
//	List<Map<String, Object>> getUserBookMarkData(CustomUserDetails authUser);
//
//	int userListCnt(); // 관리자 메인 등록된 총 사용자 조회
//
//	int userManageListCnt(Map<String, Object> param); // 사용자 관리 총원 조회
//
//	Map<String, Object> idCheck(Map<String, Object> param); // 아이디 찾기 계정 존재 여부 확인
//
//	int idFailYnCheck(Map<String, Object> param); // 아이디 존재 여부 확인
//
//	String findId(Map<String, Object> param); // 아이디 찾기 아이디 조회
//
//	Map<String, Object> updatePassword(Map<String, Object> param); // 사용자 비밀번호 변경
//
//	Map<String, Object> userList(Map<String, Object> param);
//
//	Map<String, Object> getUserList();
//
//	Map<String, Object> getAgencyList(); // 사용자 등록 및 관리의 소속 드롭 박스
//
//	Map<String, Object> getRoleList(); // 사용자 등록 및 관리의 권한 리스트 조회
//
//	int getUserIdYn(Map<String, Object> param); // 사용자 아이디 존재 유무 확인
//
//	int getUserStatus(Map<String, Object> param); // 로그인 상태 확인
//
//	void insertUserList(Map<String, Object> param); // 관리자 사용자 등록
//
//	void modifyUser(Map<String, Object> param); // 사용자 정보 수정
//
//	void updateAllNo(Map<String, Object> param); // 사용자 리스트 일괄 거절
//
//	void updateAllYes(Map<String, Object> param); // 사용자 리스트 일괄 승인
//
//	void deleteUserList(Map<String, Object> param); // 사용자 리스트 삭제
//
//	void updateUserList(Map<String, Object> param); // 사용자 리스트 수정
//
//	Map<String, Object> userPwCheck(Map<String, Object> param); // 사용지 정보 수정 - 비밀번호 검증
//
//	Map<String, Object> ipList(Map<String, Object> param); // IP 관리 조회
//
//	int selectIpCnt(); // 페이지네이션 처리를 위한 IP LIST CNT
//
//	void insertIp(Map<String, Object> param);
//
//	void deleteIp(Map<String, Object> param);
//
//	Map<String, Object> ipDuplicationCheck(Map<String, Object> param);
//
//	// IP 접근 제어를 위한 조회
//	Map<String, Object> selectIpData();

}
