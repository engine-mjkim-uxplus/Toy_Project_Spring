package com.toyproject.shopping.controller;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.toyproject.shopping.logic.OrderLogic;
import com.util.HashMapBinder;
import com.vo.CartVO;
import com.vo.CouponVO;
import com.vo.MemberVO;
@RequestMapping("/order")
public class OrderController {
	Logger logger = LoggerFactory.getLogger(OrderController.class);
	@Autowired
	OrderLogic orderLogic;
	@Autowired
//	MemberLogic memberLogic;
	
	/*********************  주문 페이지(회원, 비회원) ********************/
	@GetMapping("orderList")
	public Object orderList(HttpServletRequest req, ModelAndView mv) {
	logger.info("OrderController => order/orderList 호출 ");
	HashMapBinder hmb = new HashMapBinder(req);
	Map<String,Object> pMap = new HashMap<>();
	hmb.bind(pMap);
	
	// 상품이 한가지 품목만 넘어 왔을 경우, 여러 품목이 넘어 왔을 경우에 대해 분기처리
	List cartList = new ArrayList();
	CartVO cartVO = null;
	
	// pMap에 저장된 상품이 하나일 경우
	if(pMap.get("product_name") instanceof String) {
		String product_name = (String)pMap.get("product_name");
		String product_img = (String)pMap.get("product_img");
		String product_no =  (String)pMap.get("product_no");
		int product_price = Integer.valueOf((String)pMap.get("product_price"));
		int product_count = Integer.valueOf((String)pMap.get("product_count"));
		// 상품정보 1건 VO를 통해 List에 담기
		cartVO = new CartVO(product_name, product_img, product_no, product_price, product_count);
		cartList.add(cartVO);
		
	// pMap에 저장된 상품이 여러개일 경우(배열)	
	} else {
		String[] product_name = (String[])pMap.get("product_name");
		String[] product_img = (String[])pMap.get("product_img");
		String[] product_no = (String[])pMap.get("product_no");
		int[] product_price = Arrays.stream((String[])pMap.get("product_price"))
								.mapToInt(Integer::parseInt).toArray();
		int[] product_count = Arrays.stream((String[])pMap.get("product_count"))
								.mapToInt(Integer::parseInt).toArray();
		
		// 상품정보 N건 VO를 통해 List에 담기
		for(int i =0; i < product_name.length; i++) {
			cartVO = new CartVO(product_name[i], product_img[i]
					, product_no[i], product_price[i], product_count[i]);
			cartList.add(cartVO);
		}
		
	}
	HttpSession session = req.getSession();
	String mem_id = (String)session.getAttribute("mem_id");
	MemberVO member = null;
	List<CouponVO> couponList = null;
	
	// 회원일 경우 회원테이블에서 주소 가져오기
	if(mem_id != null) {
		mv.setViewName("memPayment");
		
		member = new MemberVO();
		member = memberLogic.Login(mem_id); // 멤버의 주소 및 휴대폰 번호
		mv.addObject("member", member);
		
		Map<String,Object> rMap = new HashMap(); // 결과값 받아올 Map
		// 쿠폰 여부조회 및 쿠포정보 가져오기
		couponList = orderLogic.memberCoupon(mem_id,rMap);
		
		// 조회한 쿠폰 결과가 있으면 추가(List 자료형이면 결과값이 있는 것)
		if((int)rMap.get("result") > 0 ) {
			logger.info("Controller 쿠폰 => " + couponList);
			mv.addObject("couponList",couponList);
		} else {
			couponList = null;
		}
		
	// 비회원
	} else if(mem_id == null) {
		mv.setViewName("payment");
		
	}
	mv.addObject("cartList", cartList);
	return mv;
	}
	
	/*********************  결제 완료 (회원, 비회원) ********************/
	@PostMapping("/orderInsert")
	public Object orderInsert(HttpServletRequest req, ModelAndView mv) {
		logger.info("OrderController => order/orderInsert.do 호출 ");
		HashMapBinder hmb = new HashMapBinder(req);
		HttpSession session = req.getSession();
		String mem_id = (String)session.getAttribute("mem_id");
		session.removeAttribute(mem_id);
		Map<String,Object> pMap = new HashMap<>();
		hmb.bind(pMap);
				
		// 주문번호(날짜생성 ex.20220522)
		String path = "";
		String orderNumber = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat fDate = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c1 = Calendar.getInstance();
		String strToday = sdf.format(c1.getTime());
		String strToday2 = fDate.format(c1.getTime());
		
		String uuid = UUID.randomUUID().toString();
		// 하이픈 제외
		String resultUuid = uuid.toString().replaceAll("-", "");
		orderNumber = strToday + resultUuid.substring(0,10);
		String orderName = (String)pMap.get("name");
		//mv.addObject("orderNumber", orderNumber);
		//mv.addObject("name", (String)pMap.get("name"));
		pMap.put("orderNumber", orderNumber);
		pMap.put("mem_id", mem_id);
		pMap.put("buyState", "주문완료");
		pMap.put("order_date", strToday2); // 테이블에 저장할 날짜(yyyy-mm-dd)
		logger.info(orderNumber);
		
		// 주문 관련
		List<Map<String,Object>> productList = new ArrayList<>();
		HashMap<String,Object> lMap = new HashMap<>();
			
		// 상품의 종류가 단건일 경우
		if(pMap.get("product_name") instanceof String) {
			oneProductProcess(productList, lMap, pMap, mem_id,session);
			
		// 상품의 종류가 여러개일 경우(배열)	
		} else {
			ManyProductProcess(productList, lMap, pMap, mem_id,session);
		} 
		 try {
			orderName = URLEncoder.encode(orderName, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		mv.setViewName("sucessPayment");
		path = "order/orderSucess.do?orderNumber="+ orderNumber + "&&name=" + orderName;
		return path;
	}
	
	/**************************결제 성공 페이지************************/
	@GetMapping("/orderSucess")
	public String orderSucess(HttpServletRequest req) {
		logger.info("OrderController => order/orderSucess 호출");
		return "sucessPayment";
	}
	
	// 단건 상품 가공 메소드 (회원,비회원)
	public void oneProductProcess (List<Map<String,Object>> productList,HashMap<String,Object> lMap,
									Map<String,Object> pMap, String mem_id, HttpSession session) {
		logger.info("상품의 종류가 하나 입니다");
		
		String product_name = (String)pMap.get("product_name");
		String product_no = (String)pMap.get("product_no");
		int product_price = Integer.valueOf((String)pMap.get("product_price"));
		int product_count = Integer.valueOf((String)pMap.get("product_count"));
		
		lMap.put("product_name", product_name); 
		lMap.put("product_no", product_no); 
		lMap.put("product_price", product_price); 
		lMap.put("product_count", product_count); 
		productList.add(lMap);
		pMap.put("productList", productList);
		// 회원 결제
		if(mem_id != null) {
			// 회원의 경우만 쿠폰, 포인트 사용
			int coupon = Integer.valueOf((String)pMap.get("coupon"));
			int point = Integer.valueOf((String)pMap.get("point"));
			pMap.put("coupon", coupon);
			pMap.put("point", point);
			
			orderLogic.memOrder(pMap);
		}
		// 비회원 결제
		else {
			orderLogic.noMemOrder(pMap);
			session.removeAttribute("cartList");
		}
	}
	// N건 상품 가공 메소드 (회원,비회원)
	public void ManyProductProcess (List<Map<String,Object>> productList, HashMap<String,Object> lMap,
									Map<String,Object> pMap, String mem_id,HttpSession session) {
		logger.info("상품의 종류가 여러개 입니다");
		
		try {
			String[] product_name = (String[])pMap.get("product_name");
			String[] product_no = (String[])pMap.get("product_no");
			int[] product_price = Arrays.stream((String[])pMap.get("product_price"))
									.mapToInt(Integer::parseInt).toArray();
			int[] product_count = Arrays.stream((String[])pMap.get("product_count"))
									.mapToInt(Integer::parseInt).toArray();	
			
			for(int i = 0; i < product_name.length; i++) {
				lMap = new HashMap<>();
				lMap.put("product_name", product_name[i]); 
				lMap.put("product_no", product_no[i]); 
				lMap.put("product_price", product_price[i]); 
				lMap.put("product_count", product_count[i]); 
				productList.add(lMap);
			}
			pMap.put("productList", productList);
			
			// 회원 결제
			if(mem_id !=null) {
				// 회원의 경우만 쿠폰, 포인트 사용
				int coupon = Integer.valueOf((String)pMap.get("coupon"));
				int point = Integer.valueOf((String)pMap.get("point"));
				pMap.put("coupon", coupon);
				pMap.put("point", point);
				
				orderLogic.memOrder(pMap);
			} 
			// 비회원 결제
			else {
				orderLogic.noMemOrder(pMap);
				session.removeAttribute("cartList");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@GetMapping("/orderUnmemberPage")
	public Object orderUnmemberPage(HttpServletRequest req, Model m) {
		logger.info("OrderController => orderUnmemberPage 호출 ");
		
		HashMapBinder hmb = new HashMapBinder(req);
		Map<String,Object> pMap = new HashMap<String, Object>();
		hmb.bind(pMap);
		
		List<Map<String,Object>> unmemberList = null;
		unmemberList = orderLogic.unmemberList(pMap);
		m.addAttribute("unmemberList", unmemberList);
		
		return "unmember";
	}
	
	public Object orderUnmemberSelect(HttpServletRequest req) {
		logger.info("OrderController => orderUnmemberSelect 호출 ");
		Map<String,Object> pMap = new HashMap<>();
		HashMapBinder hmb = new HashMapBinder(req);
		hmb.bind(pMap);
		
		int result = 0;
		result = orderLogic.orderUnmemberSelect(pMap);

		return "order/orderUnmemberPage?order_number="+pMap.get("order_number");
	}
}
