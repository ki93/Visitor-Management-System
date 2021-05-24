package com.manage.system.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.manage.system.mapper.SigninMapper;

public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private SigninMapper signinMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		CustomUserDetails users = signinMapper.getUserById(username);
		if(users == null) {
			 throw new UsernameNotFoundException("username " + username + " not found");
		}
		System.out.println("**************Found user***************");
		System.out.println("id : " + users.getUsername());
		return users;
	}

}