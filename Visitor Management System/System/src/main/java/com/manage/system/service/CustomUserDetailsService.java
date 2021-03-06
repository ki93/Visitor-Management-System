package com.manage.system.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.manage.system.dto.CustomUserDetails;
import com.manage.system.mapper.SigninMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{

	private final SigninMapper signinMapper;
	
	@Override
	public UserDetails loadUserByUsername(String user_name) throws UsernameNotFoundException {
		CustomUserDetails users = signinMapper.getUserById(user_name);
		if(users == null) {
			 throw new UsernameNotFoundException("username " + user_name + " not found");
		}
		return users;
	}
	
	public void save(CustomUserDetails customUserDetails) {
		signinMapper.save(customUserDetails);
	}
}
