package com.toyproject.shopping.controller;

import java.util.Map;

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

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping("/register")
public class RegisterController {
	Logger logger = LoggerFactory.getLogger(RegisterController.class);
	@Autowired(required=false)
	private RegisterLogic registerLogic = null;
	
	/****************************** [[ 회원가입 페이지로 이동 ]] ******************************/
	@GetMapping("/registerForm")
	public String registerForm() {
		return "register";
	}
	
	/****************************** [[ 아이디 중복체크 ]] ******************************/
	@GetMapping("/registerSelect")
	public ModelAndView registerSelect(@RequestParam Map<String,Object> pMap, HttpSession session, ModelAndView mav) {
		int result = 0;
		result = registerLogic.registerSelect(pMap);
		session.setAttribute("result", result);
		mav.setViewName("idCheck");
		return mav;
	}

	/****************************** [[ 회원가입 ]] ******************************/
	@PostMapping("/registerInsert")
	public Object registerInsert(@RequestParam Map<String,Object> pMap) {
		int result = 0;
		String path = null;
		result = registerLogic.registerInsert(pMap); 
		logger.info("컨트롤러의 result: " +result);
		if(result == 1) { // 회원가입 성공하면 Insert
			path = "loginform"; //회원가입 성공시 로그인페이지로 이동
		} else { 
			path = "redirect:registerForm";
		}
		return path;
	}
}
