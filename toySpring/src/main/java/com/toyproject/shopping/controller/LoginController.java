package com.toyproject.shopping.controller;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.toyproject.shopping.logic.LoginLogic;
import com.util.HashMapBinder;

@Controller
@RequestMapping("/login")
public class LoginController{
	Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	LoginLogic loginLogic;

	/*********************** 로그인 페이지 요청 ***********************/
	@GetMapping("/loginForm")
	public String loginForm() {
		return "loginform";
	}
	
	/**************** 로그인 요청(아이디 패스워드 확인) *****************/
	@PostMapping("/login")
	public Object login(HttpServletRequest req, Model m, RedirectAttributes rattr, HttpSession session) {
		String mem_id = "";
		Map<String,Object> pMap = new HashMap<>();
		HashMapBinder hmb = new HashMapBinder(req);
		hmb.bind(pMap);
		mem_id = (String) loginLogic.login(pMap);
		// 아이디가 없을 경우 예외 처리
		if(mem_id == null) {
			try {
				String msg =  URLEncoder.encode("id 또는 password가 일치하지 않습니다.", "utf-8");
				rattr.addFlashAttribute("msg", msg);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return "redirect:/login/loginForm";
		}
		session = req.getSession();
		session.setAttribute("mem_id", mem_id);
		return "redirect:/product/productList";
	}
	
	/************************** 로그아웃 요청 ****************************/
	@GetMapping("/logout")
	public Object logout(HttpSession session) {
		session.invalidate();
		return "redirect:/product/productList";
	}

}
