package com.manage.system.controller;

import java.util.Arrays;
import java.util.Iterator;

import javax.validation.Valid;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.manage.system.dto.CustomUserDetails;
import com.manage.system.security.UserValidator;
import com.manage.system.service.CustomUserDetailsService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class IndexController {
	
	private final CustomUserDetailsService cutomUserDetailsService;
	private final UserValidator userValidator;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@RequestMapping("/")
	public String index(Model model) {
		String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		if(role.equals("[ROLE_ADMIN]")) {
			return "redirect:/admin/aApplyDetail";
		}else {
			return "redirect:/user/apply";
		}
	}
	
	@GetMapping("/signin")
	public String signin() {
		return "signin";
	}

	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("customUserDetails", new CustomUserDetails());
		return "signup";
	}

	@PostMapping("/signup")
	public String signup(@Valid CustomUserDetails customUserDetails, BindingResult bindingResult, Model model) {
		userValidator.validate(customUserDetails, bindingResult);
		if (bindingResult.hasErrors()) {
			return "signup"; // 실패
		} else {
			// 성공
			customUserDetails.setAuth("ROLE_USER");
//			customUserDetails.setAuth("ROLE_ADMIN");
			customUserDetails.setUser_type(1);
			customUserDetails.setPwd(bCryptPasswordEncoder.encode(customUserDetails.getPwd()));
			cutomUserDetailsService.save(customUserDetails);
			
//			model.addAttribute((AuthenticateAction) UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY.);
			return "forward:/";
		}

	}

}
