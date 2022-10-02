package com.toyproject.shopping.controller;

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
import com.vo.CouponVO;
import com.vo.MemberVO;
import com.vo.ProductVO;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {
	Logger logger = LoggerFactory.getLogger(MemberController.class);
	@Autowired(required=false)
	private MemberLogic memberLogic = null;
	
	/****************************** [[ 구매내역 ]] ******************************/
	@GetMapping("/memberListPayment")
	public Object memberListPayment(@RequestParam Map<String,Object> pMap, HttpSession session, ModelAndView mav) {
		String id = (String) session.getAttribute("mem_id");
		pMap.put("member_id", id);
		
		Object path = null;
		
		if (id == null) {
			path = "loginform";
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
	
	/****************************** [[ 작성한 리뷰 조회 ]] ******************************/
	@GetMapping("/memberListReview")
	public Object memberListReview(@RequestParam Map<String,Object> pMap, HttpSession session, ModelAndView mav) {
		String id = (String) session.getAttribute("mem_id");
		logger.info(id);
		
		Object path = null;
		List<Map<String,Object>> memberListReview = null;
		
		if (id == null) {
			path = "loginform";
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
	
	/****************************** [[ 회원 정보 조회 ]] ******************************/
	@GetMapping("/memberListP")
	public Object memberListP(@RequestParam Map<String,Object> pMap, HttpSession session, ModelAndView mav) {
		String id = (String) session.getAttribute("mem_id");
		logger.info(id);
		
		Object path = null;
		
		if (id == null) {
			path = "loginform";
		}else {
			MemberVO mVO = memberLogic.Login(id);
			logger.info("ID :"+mVO.getMember_id()+", NAME: "+mVO.getMember_name());
			mav.addObject("member", mVO);
			mav.setViewName("mypage/personalpage"); 
			path = mav;
		}
		
		return path;
	}
	
	/****************************** [[ 좋아요한 목록 조회 ]] ******************************/
	@GetMapping("/memberListLike")
	public Object memberListLike(@RequestParam Map<String,Object> pMap, HttpSession session, ModelAndView mav) {
		String id = (String) session.getAttribute("mem_id");
		logger.info(id);
		
		Object path = null;
		List<ProductVO> memberListLike = null;
		
		if (id == null) {
			path = "loginform";
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

	/****************************** [[ 쿠폰목록 조회 ]] ******************************/
	@GetMapping("/memberListCoupon")
	public Object memberListCoupon(@RequestParam Map<String,Object> pMap, HttpSession session, ModelAndView mav) {
		String id = (String) session.getAttribute("mem_id");
		logger.info(id);
		
		Object path = null;
		List<CouponVO> memberListCoupon = null;
		List<Integer> myCouponList = null;
		
		if (id == null) {
			path = "loginform";
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

	/****************************** [[ 쿠폰 등록하기 ]] ******************************/
	@PostMapping("/memberInsertCoupon")
	public Object memberInsertCoupon(@RequestParam Map<String,Object> pMap, HttpSession session, ModelAndView mav) {
		String id = (String) session.getAttribute("mem_id");
		logger.info(id);
		
		Object path = null;
		
		if (id == null) {
			path = "loginform";
		}else {
			MemberVO mVO = memberLogic.Login(id);
			logger.info("ID :"+mVO.getMember_id()+", NAME: "+mVO.getMember_name());
			mav.addObject("member", mVO);
			memberLogic.memberInsertCoupon(pMap);
			path = "redirect:memberListCoupon";
		}
		
		return path;
	}

	/****************************** [[ 회원정보 수정 ]] ******************************/
	@PostMapping("/memberUpdateP")
	public Object memberUpdateP(@RequestParam Map<String,Object> pMap, HttpSession session) {
		String id = (String) session.getAttribute("mem_id");
		logger.info(id);
		
		Object path = null;
		
		if (id == null) {
			path = "loginform";
		}else {
			memberLogic.memberUpdateP(pMap); 
			path = "redirect:memberListP";
		}
		
		return path;
	}

	/****************************** [[ 구매확정|교환|반품 상태 업데이트하기 ]] ******************************/
	@GetMapping("/memberUpdateState")
	public Object memberUpdateState(@RequestParam Map<String,Object> pMap, HttpSession session) {
		memberLogic.memberUpdateState(pMap);
		return "redirect:memberListPayment";
	}
	
	/****************************** [[ 회원탈퇴하기 ]] ******************************/
	@PostMapping("/memberDelete")
	public Object memberDelete(@RequestParam Map<String,Object> pMap, HttpSession session) {
		String id = (String) session.getAttribute("mem_id");
		logger.info(id);
		
		Object path = null;
		
		if (id == null) {
			path = "loginform";
		}else {
			int result = 0;
			result = memberLogic.memberDelete(pMap); 
			
			if(result == 1) { // 회원 삭제된 경우 
				session.invalidate(); // 세션 삭제 
				path = "redirect:/product/productList"; // 메인페이지로 이동한다. 
			} else { // 회원탈퇴 실패한 경우 
				path = "redirect:memberListP";
			}
		}
		return path;
	}
	
}
