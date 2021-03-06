package com.manage.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.manage.system.dto.ApplicationDTO;

@Mapper
public interface ApplicationMapper {
	public ApplicationDTO selectApplication(Integer application_num);
	public void insertApplication(ApplicationDTO applicationDTO);
	public void updateApplication(ApplicationDTO applicationDTO);
	public List<ApplicationDTO> selectAllUserApplication(String user_id);
	public List<ApplicationDTO> selectAllApplication();
}
