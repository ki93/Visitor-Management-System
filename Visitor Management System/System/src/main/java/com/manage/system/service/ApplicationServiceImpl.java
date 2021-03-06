package com.manage.system.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.manage.system.dto.ApplicationDTO;
import com.manage.system.mapper.ApplicationMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {
	
	private final ApplicationMapper applicationMapper;
	
	@Override
	public ApplicationDTO getApplication(Integer application_num) {
		
		return applicationMapper.selectApplication(application_num);
	}

	@Override
	public void registerApplication(ApplicationDTO applicationDTO) {
		applicationMapper.insertApplication(applicationDTO);
	}

	@Override
	public void modifyApplication(ApplicationDTO applicationDTO) {
		applicationMapper.updateApplication(applicationDTO);
	}

	@Override
	public List<ApplicationDTO> getAllUserApplication(String user_id) {
		return applicationMapper.selectAllUserApplication(user_id);
	}

	@Override
	public List<ApplicationDTO> getAllApplication() {
		return applicationMapper.selectAllApplication();
	}

}
