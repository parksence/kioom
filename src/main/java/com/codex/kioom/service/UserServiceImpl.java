package com.codex.kioom.service;

import com.codex.kioom.dao.UserDAO;
import com.codex.kioom.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Map<String, Object> userLogin(Map<String, Object> param) {

		if(!param.isEmpty()) {
			// 비밀번호 암호화
			String pw = (String) param.get("password").toString();
			pw = passwordEncoder.encode(pw);

			param.put("userPw", pw);
		}
		Map<String, Object> userInfo = userDAO.userChk(param);

		return userInfo;
	}

	// 아이디 중복 검사
	@Override
	public int getUserIdYn(Map<String, Object> param) {
		return userDAO.getUserIdYn(param);
	}

	// 사용자 등록
	@Override
	public void insertUser(Map<String, Object> param) {
		param.put("h_pw", passwordEncoder.encode(param.get("password").toString()));
		userDAO.insertUser(param);
	}

	@Override
	public void insertExcelUser(Map<String, Object> param) {
		userDAO.insertExcelUser(param);
	}

	@Override
	public int updateUser(Map<String, Object> param) {

		if(!param.get("password").equals("")) {
			param.put("h_pw", passwordEncoder.encode(param.get("password").toString()));
		}

		// 업데이트 결과 회신
		int result = userDAO.updateUser(param);

		return result;
	}

	@Override
	public List<Map<String, Object>> getHospitalList(Map<String, Object> param) {
		List<Map<String, Object>> list = userDAO.getHospitalList(param);
		return list;
	}

	@Override
	public void deleteHospitalList(Map<String, Object> param) {
		
		for (int i=1; i < param.size(); i++) {
			if(param.containsKey("hospital_"+i)) {
				param.put("h_id", param.get("hospital_id"+i).toString());

				// 병원 정보 삭제
				userDAO.deleteHospitalList(param);
			}
		}
	}

	@Override
	public int getHospitalListCnt(Map<String, Object> param) {
		int totalCnt = userDAO.getHospitalListCnt(param);
		return totalCnt;
	}

	@Override
	public Map<String, Object> selectUserInfo(Map<String, Object> param) {
		Map<String, Object> userInfo = userDAO.selectUserInfo(param);
		return userInfo;
	}

	@Override
	public UserDTO selectUserInfo(UserDTO userDTO) {
		UserDTO userInfo = userDAO.selectUserInfoDto(userDTO);
		return userInfo;
	}

	@Override
	public List<String> selectUserId() {
		List<String> userInfo = userDAO.selectUserId();
		return userInfo;
	}

}
