package com.codex.kioom.config;

import com.codex.kioom.dao.UserDAO;
import com.codex.kioom.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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

		return new CustomUserDetails(userAuthes);
	}

}
