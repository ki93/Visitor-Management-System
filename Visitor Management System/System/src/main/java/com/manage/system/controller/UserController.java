package com.manage.system.controller;

import javax.validation.Valid;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.manage.system.security.CustomUserDetails;
import com.manage.system.security.CustomUserDetailsService;
import com.manage.system.security.UserValidator;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {

	private final CustomUserDetailsService cutomUserDetailsService;
	private final UserValidator userValidator;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@RequestMapping("/")
	public String index(Model model) {
		return "index";
	}
	@RequestMapping("apply")
	public ModelAndView apply(Model model) {
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("data", "application");
		return mv;
	}
	@RequestMapping("uApplyDetail")
	public ModelAndView uApplyDetail(Model model) {
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("data", "user_application_detail");
		return mv;
	}
	@RequestMapping("uDetail")
	public ModelAndView uDetail(Model model) {
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("data", "uDetail");
		return mv;
	}
	@RequestMapping("aApplyDetail")
	public ModelAndView aApplyDetail(Model model) {
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("data", "admin_application_detail");
		mv.addObject("nData", "승인 대기");
		return mv;
	}
	@RequestMapping("aDetail")
	public ModelAndView aDetail(Model model) {
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("data", "aDetail");
		mv.addObject("nData", "승인");
		mv.addObject("refuse_reason", "코로나 4단계 격상으로 방문 제한");
		return mv;
	}
//	@Autowired 
//	TestService testService; 
//	@RequestMapping("/test") 
//	public ModelAndView test() throws Exception{
//		ModelAndView mav = new ModelAndView("test");
//		String testList = testService.userSignin("admin"); 
//		mav.addObject("list", testList); 
//		return mav; 
//		}
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
			customUserDetails.setUserType(1);
			customUserDetails.setPwd(bCryptPasswordEncoder.encode(customUserDetails.getPwd()));
			cutomUserDetailsService.save(customUserDetails);
			
//			model.addAttribute((AuthenticateAction) UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY.);
			return "forward:/";
		}

	}
}
