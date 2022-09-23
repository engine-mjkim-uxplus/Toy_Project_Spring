package com.toyproject.shopping.controller;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.toyproject.shopping.logic.LoginLogic;
import com.util.HashMapBinder;

@Controller
@RequestMapping("/login")
public class LoginController{
	Logger logger = LoggerFactory.getLogger(LoginController.class);
	LoginLogic loginLogic = new LoginLogic();

	/*********************** 로그인 페이지 요청 ***********************/
	@GetMapping("/loginForm")
	public String loginForm() {
		logger.info("LoginController => login/loginForm.do 호출 ");
		return "loginform";
	}
	/**************** 로그인 요청(아이디 패스워드 확인) *****************/
	@GetMapping("/login")
	public Object login(HttpServletRequest req, HttpServletResponse res) {
		logger.info("LoginController => login/login.do 호출 ");
		String msg= null;
		String mem_id = null;
		HttpSession session = null;
		String path = null;
		ModelAndView mv = new ModelAndView();
		Map<String,Object> pMap = new HashMap<>();
		HashMapBinder hmb = new HashMapBinder(req);
		hmb.bind(pMap);
		mem_id = (String) loginLogic.login(pMap);
		if(mem_id == null) {
			try {
				msg =  URLEncoder.encode("id 또는 password가 일치하지 않습니다.", "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			path = "login/loginForm.do?msg=" + msg;
		}else {
		session = req.getSession();
		session.setAttribute("mem_id", mem_id);
		path = "product/productList.do";
		}
		return path;
	}
	/************************** 로그아웃 요청 ****************************/
	public Object logout(HttpServletRequest req, HttpServletResponse res) {
		logger.info("LoginController => login/logout.do 호출 ");
		HttpSession session = req.getSession();
		session.invalidate();
		ModelAndView mv = new ModelAndView();
		String path = "product/productList.do"; // redirect
		return path;
	}

}
