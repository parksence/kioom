package com.codex.kioom.config;

import com.codex.kioom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@Component
public class CustomAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	@Autowired
	private UserService userService;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

//		// 로그인할 때 아이디, 비밀번호 값 담기
//		HashMap<String, Object> loginInfo = new HashMap();
//		loginInfo.put("user_id", request.getParameter("username"));
//		loginInfo.put("user_pw", request.getParameter("password"));
//
//		// 아이디 존재 여부 체크하여 있으면 : 1 없으면 : 0
//		int userIdCheckYn = userService.idFailYnCheck(loginInfo);
//		int userStatus; // 로그인 실패 상태 값
//
//		if(userIdCheckYn == 1) {
//
//			// 0 : 성공, 2 : 비밀번호 틀림, 3 : 승인 대기,  4 : 승인 거절
//			userStatus = userService.getUserStatus(loginInfo);
//
//		} else {
//			userStatus = 1; // 아이디 없음
//		}

//		loginInfo.put("login_yn", userStatus);
//		loginInfo.put("action_comment", "로그인 실패");
//
//		// 로그인 이력 추가
//		logService.insertLoginLog(loginInfo, request);

//		setDefaultFailureUrl("/login?error=true&exception=" + userStatus);
		setDefaultFailureUrl("/login?error=true&exception=");
		super.onAuthenticationFailure(request, response, exception);
	}
}
