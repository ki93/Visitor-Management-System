package com.manage.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.manage.system.service.TestService;

@RestController
public class HelloRestController {
	@RequestMapping("/")
	public String index() {
		return "helloworld1111!";
	}
	@Autowired 
	TestService testService; 
	@RequestMapping(value = "/test") 
	public ModelAndView test() throws Exception{
		ModelAndView mav = new ModelAndView("test");
		String testList = testService.userSignin("admin"); 
		mav.addObject("list", testList); 
		return mav; 
		}

}
