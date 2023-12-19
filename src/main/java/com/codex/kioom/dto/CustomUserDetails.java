package com.codex.kioom.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = 1l;

	private ArrayList<UserDTO> userDTOs;
	private UserDTO userDTO;

	public CustomUserDetails(ArrayList<UserDTO> userDTOs) {

		this.userDTOs = userDTOs;
		this.userDTO = userDTOs.get(0);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		return authorities;
	}

	public String getCreateDate() {
		return userDTO.getCREATE_DATE();
	}

	public String getUpdateDate() {
		return userDTO.getUPDATE_DATE();
	}

	// 사용자 아이디
	@Override
	public String getUsername() {
		return userDTO.getH_ID();
	}

	// 사용자 이름
	public String getName() {
		return userDTO.getH_NAME();
	}

	public String getEmail() {
		return userDTO.getEMAIL();
	}
	public String getTel() {
		return userDTO.getH_TEL();
	}

	public String getFax() {
		return userDTO.getH_FAX();
	}

	public String getLocation() {
		return userDTO.getH_LOCATION();
	}

	public String getManager() {
		return userDTO.getH_MANAGER();
	}

	public String getPhone() {
		return userDTO.getH_PHONE();
	}

	@Override
	public String getPassword() {
		return userDTO.getH_PW();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String toString() {
		return "UserDetailsDTO [userDTO=" + userDTO.toString() + "]";
	}

}