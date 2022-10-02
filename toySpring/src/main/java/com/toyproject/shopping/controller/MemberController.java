package com.toyproject.shopping.controller;

import java.util.HashMap;
import java.util.List;
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

import com.toyproject.shopping.logic.MemberLogic;
import com.util.HashMapBinder;
import com.vo.CouponVO;
import com.vo.MemberVO;
import com.vo.ProductVO;

@Controller
@RequestMapping("/member")
public class MemberController {
	Logger logger = LoggerFactory.getLogger(MemberController.class);
	@Autowired(required=false)
	private MemberLogic memberLogic = null;
	
	@GetMapping("/memberListPayment")
	public Object memberListPayment(@RequestParam Map<String,Object> pMap, HttpSession session) {
		logger.info("MemberController: memberListPayment 호출");
		
		String id = (String) session.getAttribute("mem_id");
		pMap.put("member_id", id);
		
		Object path = null;
		ModelAndView mav = new ModelAndView();
		
		if (id == null) {
			path = "redirect:/login/loginForm";
		}else {
			MemberVO mVO = memberLogic.Login(id);
			logger.info("ID :"+mVO.getMember_id()+", NAME: "+mVO.getMember_name());
			mav.addObject("member", mVO);
			mav.setViewName("mypage/orderpage"); 
			path = mav;
		}
		
		List<Map<String,Object>> orderList = null;
		orderList = memberLogic.memberListPayment(pMap);
		mav.addObject("orderList", orderList);

		return path;
	}
	
	@GetMapping("/memberListReview")
	public Object memberListReview(@RequestParam Map<String,Object> pMap, HttpSession session) {
		logger.info("MemberController: memberListReview 호출");
		
		String id = (String) session.getAttribute("mem_id");
		logger.info(id);
		
		Object path = null;
		List<Map<String,Object>> memberListReview = null;
		ModelAndView mav = new ModelAndView();
		
		if (id == null) {
			path = "redirect:/login/loginForm";
		}else {
			MemberVO mVO = memberLogic.Login(id);
			logger.info("ID :"+mVO.getMember_id()+", NAME: "+mVO.getMember_name());
			mav.addObject("member", mVO);
			memberListReview = memberLogic.memberListReview(id);
			mav.addObject("memberListReview", memberListReview);
			mav.setViewName("mypage/reviewpage"); 
			path = mav;
		}
		
		return path;
	}
	
	@GetMapping("/memberListP")
	public Object memberListP(@RequestParam Map<String,Object> pMap, HttpSession session) {
		logger.info("MemberController: memberListP 호출");

		String id = (String) session.getAttribute("mem_id");
		logger.info(id);
		
		Object path = null;
		ModelAndView mav = new ModelAndView();
		
		if (id == null) {
			path = "login/loginForm.do";
		}else {
			MemberVO mVO = memberLogic.Login(id);
			logger.info("ID :"+mVO.getMember_id()+", NAME: "+mVO.getMember_name());
			mav.addObject("member", mVO);
			mav.setViewName("mypage/personalpage"); 
			path = mav;
		}
		
		return path;
	}
	
	@GetMapping("/memberListLike")
	public Object memberListLike(@RequestParam Map<String,Object> pMap, HttpSession session) {
		logger.info("MemberController: memberListLike 호출");
		
		String id = (String) session.getAttribute("mem_id");
		logger.info(id);
		
		Object path = null;
		List<ProductVO> memberListLike = null;
		ModelAndView mav = new ModelAndView();
		
		if (id == null) {
			path = "login/loginForm.do";
		}else {
			MemberVO mVO = memberLogic.Login(id);
			mav.addObject("member", mVO);
			memberListLike = memberLogic.memberListLike(id);
			mav.addObject("memberListLike", memberListLike);
			mav.setViewName("mypage/likepage"); 
			path = mav;
		}
		return path;
	}

	@GetMapping("/memberListCoupon")
	public Object memberListCoupon(@RequestParam Map<String,Object> pMap, HttpSession session) {
		logger.info("MemberController: memberListCoupon 호출");

		String id = (String) session.getAttribute("mem_id");
		logger.info(id);
		
		Object path = null;
		List<CouponVO> memberListCoupon = null;
		List<Integer> myCouponList = null;
		ModelAndView mav = new ModelAndView();
		
		if (id == null) {
			path = "login/loginForm.do";
		}else {
			MemberVO mVO = memberLogic.Login(id);
			logger.info("ID :"+mVO.getMember_id()+", NAME: "+mVO.getMember_name());
			mav.addObject("member", mVO);
			memberListCoupon = memberLogic.memberListCoupon(id);
			mav.addObject("memberListCoupon", memberListCoupon);
			myCouponList = memberLogic.myCouponList(id);
			mav.addObject("myCouponList", myCouponList);
			mav.setViewName("mypage/couponpage");
			path = mav;
		}
		return path;
	}

	@PostMapping("/memberInsertCoupon")
	public Object memberInsertCoupon(@RequestParam Map<String,Object> pMap, HttpSession session) {
		logger.info("MemberController: memberInsertCoupon 호출");
		String id = (String) session.getAttribute("mem_id");
		logger.info(id);
		
		Object path = null;
		int result = 0;
		List<CouponVO> couponList = null;
		ModelAndView mav = new ModelAndView();
		
		if (id == null) {
			path = "login/loginForm.do";
		}else {
			MemberVO mVO = memberLogic.Login(id);
			logger.info("ID :"+mVO.getMember_id()+", NAME: "+mVO.getMember_name());
			mav.addObject("member", mVO);
			result = memberLogic.memberInsertCoupon(pMap);
			path = "redirect:memberListCoupon";
		}
		
		return path;
	}

	/************************ [[ 회원정보 수정 ]] ************************/
	@GetMapping("/memberUpdateP")
	public Object memberUpdateP(@RequestParam Map<String,Object> pMap, HttpSession session) {
		logger.info("MemberController: memberUpdateP 호출");
		String id = (String) session.getAttribute("mem_id");
		logger.info(id);
		
		Object path = null;
		ModelAndView mav = new ModelAndView();
		
		if (id == null) {
			path = "login/loginForm.do";
		}else {
			int result = 0;
			result = memberLogic.memberUpdateP(pMap); 
			path = "member/memberListP.do";
		}
		
		return path;
	}

	@GetMapping("/memberUpdateState")
	public Object memberUpdateState(@RequestParam Map<String,Object> pMap, HttpSession session) {
		
		int result = 0;
		result = memberLogic.memberUpdateState(pMap);
		
		return "redirect:memberListPayment";
	}
	
	@PostMapping("/memberDelete")
	public Object memberDelete(@RequestParam Map<String,Object> pMap, HttpSession session) {
		logger.info("MemberController: memberDelete 호출");
		String id = (String) session.getAttribute("mem_id");
		logger.info(id);
		
		Object path = null;
		
		if (id == null) {
			path = "login/loginForm.do";
		}else {
			int result = 0;
			result = memberLogic.memberDelete(pMap); 
			
			if(result == 1) { // 회원 삭제된 경우 
				session.invalidate(); // 세션 삭제 
				path = "redirect:product/productList.do"; // 메인페이지로 이동한다. 
			} else { // 회원탈퇴 실패한 경우 
				path = "redirect:memberListP";
			}
		}
		return path;
	}
	
}
