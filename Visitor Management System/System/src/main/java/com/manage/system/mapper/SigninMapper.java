package com.manage.system.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SigninMapper {
	public String userSignin(String userId);
}