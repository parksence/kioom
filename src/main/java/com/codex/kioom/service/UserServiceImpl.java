package com.codex.kioom.service;

import com.codex.kioom.dao.UserDAO;
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


//	@Override
//	public Map<String, Object> userList(Map<String, Object> param) {
//		List<Map<String, Object>> result = userDao.userList(param);
//
//		return Maps.json("S-1", "ok", result);
//	}
//
//	// 사용자 등록 및 관리의 소속 데이터 조회
//	@Override
//	public Map<String, Object> getAgencyList() {
//		List<Map<String, Object>> result = userDao.getAgencyList();
//
//		return Maps.json("S-1", "ok", result);
//	}
//
//	@Override
//	public Map<String, Object> getRoleList() {
//		List<Map<String, Object>> result = userDao.getRoleList();
//
//		return Maps.json("S-1", "ok", result);
//	}
//
//	@Override
//	public int getUserStatus(Map<String, Object> param) {
//
//		Boolean loginStatus = null; // 비밀번호 실패 여부
//		String user_pw = param.get("user_pw").toString();
//		String userPw = userDao.userPwCheck(param);
//
//		loginStatus = passwordEncoder.matches(user_pw, userPw);
//		System.out.println("loginStatus = " + loginStatus);
//
//		if(loginStatus == true) {
//			return userDao.getUserStatus(param);
//		} else {
//			return 2; // 비밀번호 틀림
//		}
//	}
//
//	@Override
//	public int userListCnt() {
//		int result = userDao.userListCnt();
//		return result;
//	}
//
//	@Override
//	public int userManageListCnt(Map<String, Object> param) {
//		int result = userDao.userManageListCnt(param);
//		return result;
//	}
//
//	// 이름과 이메일 체크
//	@Override
//	public Map<String, Object> idCheck(Map<String, Object> param) {
//		Map<String, Object> result_object = new HashMap<>();
//
//		System.out.println("param = " + param.toString());
//
//		try {
//			int result = userDao.idCheck(param);
//			result_object.put("code", result);
//			result_object.put("msg", "정상 처리 되었습니다.");
//
//			if(result == 0) {
//				throw new Exception();
//			}
//
//		} catch (Exception e) {
//			result_object.put("code", 0);
//			result_object.put("msg", "아이디 찾기 오류 : 아이디가 존재하지 않습니다.");
//		}
//
//		return result_object;
//	}
//
//	@Override
//	public String findId(Map<String, Object> param) {
//
//		String result = userDao.findId(param);
//		return result;
//	}
//
//
//	@Override
//	public Map<String, Object> removeUser(String userCode) {
//
//		userDao.removeUser(userCode);
//		return Maps.json("S-1", userCode + "번 회원이 삭제 되었습니다.");
//	}
//

//
//	@Override
//	public Map<String, Object> bookmark(String hpid, String userCode) {
//		// TODO Auto-generated method stub
//
//		System.out.println("hpid : " + hpid);
//		System.out.println("userCode : " + userCode);
//
//		Map userData = new HashMap();
//
//		userData.put("hpid", hpid);
//		userData.put("userCode", userCode);
//
//		Map<String, Object> result = userDao.findByBookmarkForUser(userData);
//
//		if (result == null) {
//			userDao.bookmark(userData);
//			return Maps.json("S-1", "해당 병원이 즐겨찾기에 등록되었습니다.");
//		} else {
//			userDao.bookmarkClear(userData);
//			return Maps.json("S-2", "해당 병원의 즐겨찾기가 해제되었습니다.");
//		}
//	}
//
//	@Override
//	public boolean findByBookMark(String object, CustomUserDetails authUser) {
//		Map userData = new HashMap();
//
//		userData.put("hpid", object);
//		userData.put("userCode", authUser.getUserCode());
//
//		Map<String, Object> result = userDao.findByBookmarkForUser(userData);
//
//		if (result == null) {
//			return false;
//		} else {
//			return true;
//		}
//	}
//
//	@Override
//	public List<Map<String, Object>> getUserBookMarkData(CustomUserDetails authUser) {
//
//		Map<String, Object> map = new HashMap();
//		map.put("userCode", authUser.getUserCode());
//
//		List<Map<String, Object>> result = userDao.getUserBookMarkData(map);
//
////		System.out.println("result : " + result);
//		return result;
//	}
//
//	// 관리자 사용자 등록
//	@Override
//	public void insertUserList(Map<String, Object> param) {
//		param.put("userPw", passwordEncoder.encode(param.get("userPw").toString()));
//		userDao.insertUserList(param);
//	}
//
//	/* 사용자 정보 수정 */
//	public void modifyUser(Map<String, Object> param) {
//		userDao.modifyUser(param);
//	}
//
//	// 사용자 리스트 일괄거절
//	@Override
//	public void updateAllNo(Map<String, Object> param) {
//
//		for(int i = 0; i < param.size(); i++) {
//			if(param.containsKey("user_id"+i)) {
//				param.put("user_id", param.get("user_id"+i).toString());
//				userDao.updateAllNo(param);
//
//				// 일괄 거절 로그 추가
//				param.put("status", "거절"); // 승인 여부
//				param.put("action_comment", "일괄 거절"); // 액션 코멘트
//				logDao.insertRoleLog(param);
//			}
//		}
//	}
//
//	// 사용자 리스트 일괄승인
//	@Override
//	public void updateAllYes(Map<String, Object> param) {
//		for(int i = 0; i < param.size(); i++) {
//			if(param.containsKey("user_id"+i)) {
//				param.put("user_id", param.get("user_id"+i).toString());
//				userDao.updateAllYes(param);
//
//				// 일괄 승인 로직 추가
//				param.put("status", "승인"); // 승인 여부
//				param.put("action_comment", "일괄 승인"); // 액션 코멘트
//				logDao.insertRoleLog(param);
//			}
//		}
//	}
//
//	// 사용자 리스트 삭제
//	@Override
//	public void deleteUserList(Map<String, Object> param) {
//
//		for (int i = 0; i < param.size(); i++) {
//			if(param.containsKey("user_checked"+i)) {
//				param.put("user_id", param.get("user_id"+i).toString());
//				param.put("role_cd", param.get("role"+i).toString());
//				param.put("seungin_yn", param.get("seungin_yn"+i).toString());
//
//				userDao.deleteUserBookmark(param); // 북마크 정보 삭제
//				userDao.deleteUserList(param); // 사용자 삭제
//
//				// 권한 삭제 로그 추가
//				param.put("action_comment", "사용자 삭제로 인한 권한 삭제");
//				param.put("status", "삭제");
//
//				logDao.insertRoleLog(param);
//			}
//		}
//	}
//
//	// 사용자 리스트 수정
//	@Override
//	public void updateUserList(Map<String, Object> param) {
//		for (int i = 0; i < param.size(); i++) {
//			if(param.containsKey("user_checked"+i)) {
//				param.put("user_id", param.get("user_id"+i).toString());
//				param.put("role_cd", param.get("role"+i).toString());
//				param.put("seungin_yn", param.get("seungin_yn"+i).toString());
//
//				userDao.updateUserList(param);
//
//				// 권한 로그 추가 로직 시작
//				if (param.get("seungin_yn"+i).toString().equals("0")){
//					param.put("status", "대기");
//				} else if (param.get("seungin_yn"+i).toString().equals("1")) {
//					param.put("status", "승인");
//				}
//				else if (param.get("seungin_yn"+i).toString().equals("2")) {
//					param.put("status", "거절");
//				}
//
//				if (param.get("role"+i).toString().equals("1")){
//					param.put("action_comment", "사용자 권한 부여");
//				}else if (param.get("role"+i).toString().equals("2")){
//					param.put("action_comment", "의료진 권한 부여");
//				}else if (param.get("role"+i).toString().equals("3")){
//					param.put("action_comment", "최고 관리자 부여");
//				}else if (param.get("role"+i).toString().equals("4")){
//					param.put("action_comment", "병원정보 관리자 부여");
//				}else if (param.get("role"+i).toString().equals("5")){
//					param.put("action_comment", "중증수용 관리자 부여");
//				}
//
//				// 권한 변경 로직 추가
//				logDao.insertRoleLog(param);
//			}
//		}
//	}
//
//	// 사용자 정보 이전 비밀번호 체크
//	@Override
//	public Map<String, Object> userPwCheck(Map<String, Object> param) {
//
//		Map<String, Object> hm = new HashMap();
//		int updateYn = 0; // 업데이트 성공 여부
//
//		try {
//			// 비밀번호 체크 기능
////			String userPwEncoding = userDao.userPwCheck(param);
////			System.out.println("userPwEncoding = " + userPwEncoding);
////			boolean pwCheckYn = passwordEncoder.matches(param.get("user_password").toString(), userPwEncoding);
////			System.out.println(pwCheckYn);
//
//			String user_new_pw = param.get("user_new_password").toString();
//
//			// 사용자 정보 업데이트
//			if(!user_new_pw.isEmpty()) {
//				param.put("userPw", passwordEncoder.encode(param.get("user_new_password").toString()));
//			}
//			// 업데이트 결과 회신
//			updateYn = userDao.updateUserInfo(param);
//			if(updateYn == 1) {
//				// 성공 메시지 처리
//				hm.put("code", 1);
//				hm.put("msg", "정상적으로 변경되었습니다.");
//			} else {
//				throw new Exception();
//			}
//
//		} catch(Exception e) {
//			System.out.println("updateYn = " + updateYn);
//
//			hm.put("code", 0);
//			hm.put("msg", "사용자 정보 변경이 실패하였습니다.\n관리자에게 문의하세요.");
//		}
//
//		return hm;
//	}
//
//	// 사용자 비밀번호 변경
//	@Override
//	public Map<String, Object> updatePassword(Map<String, Object> param) {
//
//		Map<String, Object> hm = new HashMap();
//
//		try {
//			System.out.println("param = " + param.toString());
//			param.put("userPw", passwordEncoder.encode(param.get("new_password").toString()));
//
//			int result = userDao.updatePassword(param);
//			if(result == 1) {
//				hm.put("code", 1);
//				hm.put("msg", "패스워드가 정상적으로 변경되었습니다.");
//			} else {
//				throw new Exception();
//			}
//
//		} catch (Exception e) {
//			hm.put("code", 0);
//			hm.put("msg", "패스워드 변경 오류 - 관리자에게 문의해주세요.");
//		}
//
//		return hm;
//	}
//
//	// 아이디 존재 여부 확인
//	public int idFailYnCheck(Map<String, Object> param) {
//		return userDao.idFailYnCheck(param);
//	}
//
//	// IP 리스트 카운트 조회
//	@Override
//	public int selectIpCnt() {
//		return userDao.selectIpCnt();
//	}
//
//	// IP 관리 조회
//	@Override
//	public Map<String, Object> ipList(Map<String, Object> param) {
//		List<Map<String, Object>> result = userDao.selectIpList(param);
//		return Maps.selectJson(result);
//	}
//
//	// IP 관리 조회
//	@Override
//	public Map<String, Object> selectIpData() {
//		List<Map<String, Object>> result = userDao.selectIpData();
//		return Maps.selectJson(result);
//	}
//
//	// IP 관리 - 추가
//	@Override
//	public void insertIp(Map<String, Object> param) {
//		userDao.insertIp(param);
//	}
//
//	// IP 삭제
//	@Override
//	public void deleteIp(Map<String, Object> param) {
//		userDao.deleteIp(param);
//	}
//
//	// IP 중복 검사
//	@Override
//	public Map<String, Object> ipDuplicationCheck(Map<String, Object> param) {
//		Map<String, Object> result_object = new HashMap<>();
//
//		try {
//			int result = userDao.ipDuplicationCheck(param);
//			result_object.put("code", result);
//			result_object.put("msg", "이미 존재하는 IP입니다.");
//
//			if(result == 0) {
//				throw new Exception();
//			}
//
//		} catch (Exception e) {
//			result_object.put("code", 0);
//			result_object.put("msg", "정상 처리 되었습니다.");
//		}
//
//		return result_object;
//	}

}
