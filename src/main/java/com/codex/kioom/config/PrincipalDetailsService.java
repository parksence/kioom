package com.sh.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sh.dao.UserDao;
import com.sh.dto.CustomUserDetails;
import com.sh.dto.UserDTO;

@Service
public class SecurityService implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		System.out.println("[ SecurityServive ] : " + username);

		ArrayList<UserDTO> userAuthes = null;

		userAuthes = userDao.findByUserID(username);

//		System.out.println("userAuthes : " + userAuthes);

		if (userAuthes.size() == 0) {
			throw new UsernameNotFoundException("User " + username + " Not Found");
		}

		// Index 0 out of bounds for length

		return new CustomUserDetails(userAuthes);

	}

}
