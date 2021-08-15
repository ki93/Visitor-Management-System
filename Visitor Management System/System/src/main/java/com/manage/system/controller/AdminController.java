package com.manage.system.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.manage.system.dto.ApplicationDTO;
import com.manage.system.service.ApplicationService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminController {
	
	private final ApplicationService applicationService;

	@RequestMapping("aApplyDetail")
	public ModelAndView aApplyDetail() {
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("data", "admin_application_detail");
		mv.addObject("List", applicationService.getAllApplication());
		mv.addObject("application", new ApplicationDTO());
		return mv;
	}
	@RequestMapping("aDetail/{application_num}")
	public ModelAndView aDetail(@PathVariable("application_num") int application_num) {
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("data", "aDetail");
		mv.addObject("apply", applicationService.getApplication(application_num));
		mv.addObject("application", new ApplicationDTO());
		return mv;
	}
	@RequestMapping("updateApplication/{application_num}")
	public String updateApplication(@PathVariable("application_num") int application_num ,ApplicationDTO applicationDTO) {
		if(applicationDTO.getRefuse_reason()!=null) {
			applicationDTO.setAdmin_id(SecurityContextHolder.getContext().getAuthentication().getName());
			applicationDTO.setApplication_state("거절");
		}else {
			applicationDTO.setAdmin_id(SecurityContextHolder.getContext().getAuthentication().getName());
			applicationDTO.setApplication_state("승인");
		}
		applicationService.modifyApplication(applicationDTO);
		return "redirect:/aApplyDetail";
	}
}