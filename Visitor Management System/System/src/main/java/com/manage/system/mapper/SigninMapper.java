package com.manage.system.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.manage.system.dto.CustomUserDetails;

@Mapper
public interface SigninMapper {
	public String userSignin(String user_id);
	public CustomUserDetails getUserById (String user_id);
	public CustomUserDetails findByUsername (String user_name);
	public void save (CustomUserDetails customUserDetails);
}
