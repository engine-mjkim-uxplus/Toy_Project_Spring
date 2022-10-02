package com.toyproject.shopping.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.toyproject.shopping.logic.CartLogic;
import com.util.HashMapBinder;
import com.vo.CartVO;
import com.vo.ProductVO;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping("/cart")
public class CartController{
	@Autowired
	CartLogic cartLogic;
	
	/************************ 장바구니 목록조회 ****************************/
	@GetMapping("/cartList")
	public String cartList(HttpServletRequest req, Model m,	HttpSession session) {
		List<CartVO> cartList = null;
		String mem_id = (String)session.getAttribute("mem_id");
		
		// 회원일 경우 db에서 카트조회
		if(loginCheck(session)) {
			cartList = cartLogic.cartList(mem_id);
			m.addAttribute("cartList", cartList);
		}
		return "cart";
	}
	
	/*****************로그인 체크(있으면 true, 없으면 false 리턴)*****************/
	private boolean loginCheck(HttpSession session) {
		return session.getAttribute("mem_id") != null;
	}
	
	/************************ 장바구니 담기 ****************************/
	@GetMapping("/cartInsert")
	public String cartInsert(HttpServletRequest req, HttpSession session, ProductVO product ) {		
		String select = "";
		String path = "";
		int result = 0;
		String mem_id = (String)session.getAttribute("mem_id");
		List<CartVO> sCartList = (ArrayList<CartVO>)session.getAttribute("cartList");
		
		Map<String,Object> pMap = new HashMap<>();
		HashMapBinder hmb = new HashMapBinder(req);
		hmb.bind(pMap);
		// 각 자료형에 맞게 파라미터 값 형변환(비로그인 시 활용)
		String product_no = (String)pMap.get("product_no");
		String product_img = (String)pMap.get("product_img");
		String product_category = (String)pMap.get("product_category");
		String product_name = (String)pMap.get("product_name");
		int product_count = Integer.valueOf((String) pMap.get("product_count"));
		int product_price = Integer.valueOf((String)pMap.get("product_price"));
		String url = (String)pMap.get("url");

		// 회원 장바구니 담기
		if(loginCheck(session)) {
			pMap.put("mem_id", mem_id);
			pMap.put("product_count", product_count);
			select = cartLogic.cartSearch(pMap);
		}
		// 비로그인 장바구니 담기
		else if(loginCheck(session) == false) {
			// 처음으로 장바구니 담을 시 cart생성
			if(sCartList == null) {
				sCartList = new ArrayList<CartVO>();
				// 파라미터로 넘어온 아이템 추가
				CartVO item= new CartVO(product_name,product_no,product_count,product_img,product_price,product_category);
				sCartList.add(item);
				session.setAttribute("cartList", sCartList);
			} else {
				// 기존 상품이 아니면 0, 기존 상품이면 1
				int check = 0;
				
				for(CartVO cartList : sCartList) {
					String sProduct_name = cartList.getProduct_name();
					int sProduct_count = cartList.getProduct_count();
					
					// 기존에 장바구니에 있는 상품인지 체크
					if(product_name.equals(sProduct_name)) {
						cartList.setProduct_count(sProduct_count + product_count);
						++check;
						break;
					}
				}				
				if(check == 0) {
					CartVO item= new CartVO(product_name,product_no,product_count,product_img,product_price,product_category);
					sCartList.add(item);
					session.setAttribute("cartList", sCartList);
				}
			} // end of else
			
		}
		// 메인페이지에서 장바구니 담기한 경우
		if(url.equals("home")) {
			path = "redirect:/product/productList";
		}
		// 상세페이지 장바구니 담기한 경우
		else if(url.equals("detail")) {
			path =  "redirect:/product/productDetail?product_no=" + product_no + "&product_category=" + product_category;		
		}	
		return path;
	}
	
	/************************ 장바구니 수량 변경 ****************************/
	@GetMapping("/cartUpdate")
	public String cartUpdate(@RequestParam Map<String,Object> pMap, HttpSession session) {
		int result = 0;
		String path = "redirect:/cart/cartList";
		// 회원 수량 변경
		if(loginCheck(session)) {
			pMap.put("mem_id",session.getAttribute("mem_id"));
			cartLogic.cartUpdate(pMap);
			return path;			
		} 
		// 비회원 수량 변경
		List<CartVO> sCartList = (ArrayList<CartVO>)session.getAttribute("cartList");
		String btn = (String)pMap.get("btn");
		String no = (String) pMap.get("product_no");
		
		// 수량 증가할 아이템 list에서 찾은 후 수량 변경
		for(CartVO item : sCartList) {
			if(item.getProduct_no().equals(no)) {
				int count = item.getProduct_count();
				// 수량 증가/감소 분기
				if(btn.equals("plus")) {
					item.setProduct_count(count + 1);
				} else {
					item.setProduct_count(count - 1);
				}
				break;
			}
		}
		return path;
	}
	
	/************************ 장바구니 아이템 삭제 ****************************/
	@GetMapping("/cartDelete")
	public Object cartDelete(@RequestParam Map<String,Object> pMap, HttpSession session) {
		String path = "redirect:/cart/cartList";
		// 회원 장바구니 삭제
		if(loginCheck(session)) {
			pMap.put("mem_id",session.getAttribute("mem_id"));
			cartLogic.cartDelete(pMap);
			return path;			
		}
		// 비회원 장바구니 삭제
		List<CartVO> sCartList = (ArrayList<CartVO>)session.getAttribute("cartList");
		String no = (String) pMap.get("product_no");		
		// 장바구니에서 삭제할 아이템 list에서 찾은 후 삭제
		for(CartVO item : sCartList) {
			if(item.getProduct_no().equals(no)) {
				sCartList.remove(item);
				break;		
			}
		}
		return path;
	}

}
