package com.codex.kioom.config;

import com.codex.kioom.config.security.auth.PrincipalDetails;
import com.codex.kioom.dao.UserDAO;
import com.codex.kioom.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

// 시큐리티 설정에서 loginProcessingUrl("/doLogin");
// /doLogin 요청이 오면 자동으로 UserDetailsService 타입으로 IoC되어 있는 loadUserByUsername 함수가 실행
@Service
public class PrincipalDetailsService implements UserDetailsService {

	@Autowired
	private UserDAO userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ArrayList<UserDTO> userAuthes = null;
		userAuthes = userDao.findByUserID(username);

		if (userAuthes.size() == 0) {
			throw new UsernameNotFoundException("User " + username + " Not Found");
		}

		return new PrincipalDetails(userAuthes);
	}

}
