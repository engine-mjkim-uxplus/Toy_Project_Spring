package com.toyproject.shopping.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.toyproject.shopping.logic.RegisterLogic;
import com.util.HashMapBinder;

@Controller
@RequestMapping("/register")
public class RegisterController {
	Logger logger = LoggerFactory.getLogger(RegisterController.class);
	@Autowired(required=false)
	private RegisterLogic registerLogic = null;
	
	@GetMapping("/registerForm")
	public String registerForm() {
		logger.info("Controller의 registerForm 호출 성공");
		return "register";
	}
	
	@GetMapping("/registerSelect")
	public Object registerSelect(@RequestParam Map<String,Object> pMap, HttpSession session) {
		logger.info("Controller의 registerSelect 호출 성공");
		int result = 0;
		ModelAndView mav = new ModelAndView();
		result = registerLogic.registerSelect(pMap);
		session.setAttribute("result", result);
		mav.setViewName("idCheck");
		return mav;
	}
	@PostMapping("/registerInsert")
	public Object registerInsert(@RequestParam Map<String,Object> pMap) {
		logger.info("Controller의 registerInsert 호출 성공");
		int result = 0;
		String path = null;
		result = registerLogic.registerInsert(pMap); 
		logger.info("컨트롤러의 result: " +result);
		if(result == 1) { // 회원가입 성공하면 Insert
			path = "redirect:/login/loginForm";
		} else { 
			path = "redirect:registerForm";
		}
		return path;
	}
}
