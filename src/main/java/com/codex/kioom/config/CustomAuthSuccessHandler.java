package com.codex.kioom.config;

import com.codex.kioom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@Component
public class CustomAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Autowired
	private UserService userService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

		// 로그인할 때 아이디, 비밀번호 값 담기
//		HashMap<String, Object> loginInfo = new HashMap();
//		loginInfo.put("user_id", request.getParameter("username"));
//		loginInfo.put("login_yn", '0');
//		loginInfo.put("action_comment", "로그인 성공");

		// 로그인 이력 추가
//		logService.insertLoginLog(loginInfo, request);

		//기본 url 설정, savedRequest가 null일 경우 설정한 페이지로 보내기 위함이다.
		setDefaultTargetUrl("/");

		System.out.println("!################");

		super.onAuthenticationSuccess(request, response, authentication);
	}
}
