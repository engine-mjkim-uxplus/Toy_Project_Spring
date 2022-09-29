package com.toyproject.shopping.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.toyproject.shopping.logic.CartLogic;
import com.util.HashMapBinder;
import com.vo.CartVO;
@RequestMapping("/cart")
public class CartController{
	Logger logger = LoggerFactory.getLogger(CartController.class);
	@Autowired
	CartLogic cartLogic;
	
	/************************ 장바구니 목록조회 ****************************/
	@GetMapping("/cartList")
	public String cartList(HttpServletRequest req, Model m) {
		logger.info("CartController => cart/cartList 호출 ");
		List<CartVO> cartList = null;
		HttpSession session = req.getSession();
		String mem_id = (String)session.getAttribute("mem_id");
		List<CartVO> sCartList = (ArrayList<CartVO>)session.getAttribute("cartList");
		
		// 회원일 경우 db에서 카트조회
		if(mem_id != null) {
			cartList = cartLogic.cartList(mem_id);
			m.addAttribute("cartList", cartList);
		}
		return "cart";
	}
	
	/************************ 장바구니 담기 ****************************/
	@GetMapping("/cartInsert")
	public String cartInsert(HttpServletRequest req) {
		logger.info("CartController => cart/carInsert 호출 ");
		
		String select = null;
		int result = 0;
		HttpSession session = req.getSession();
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
		String path = "";

		// 회원 장바구니 담기
		if(mem_id != null) {
			pMap.put("mem_id", mem_id);
			pMap.put("product_count", product_count);
			select = cartLogic.cartSearch(pMap);
			// 이후 cartUpdate로 redirect 하도록 수정 필요	
			// 기존에 장바구니에 있는 상품일 경우 수량 UPDATE
			if(select != null) {
				result = cartLogic.cartUpdate(pMap);
				// 기존 장바구니에 없는 상품일 경우 CART 테이블에 INSERT
			} else {
				result = cartLogic.cartInsert(pMap);
			}
			// 메인페이지에서 장바구니 담기
			if(url.equals("home")) {
				path = "redirect:/product/productList";
			}
			// 상세페이지 장바구니 담기
			else {
				path = "redirect:/product/productDetail?product_no=" + product_no + "&product_category=" + product_category;	
			}
		}
		
		// 비로그인 장바구니 담기
		else if(mem_id == null) {
			// 처음으로 장바구니 담을 시 cart생성
			if(sCartList == null) {
				sCartList = new ArrayList<CartVO>();
				// 파라미터로 넘어온 아이템 추가
				CartVO item= new CartVO(product_name,product_no,product_count,product_img,product_price,product_category);
				sCartList.add(item);
				session.setAttribute("cartList", sCartList);
				// 메인페이지에서 장바구니 담기
				if(url.equals("home")) {
					path = "redirect:/product/productList";
				}
				// 상세페이지 장바구니 담기
				else if(url.equals("detail")) {
					path = "redirect:/product/productDetail?product_no=" + product_no + "&product_category=" + product_category;	
					
				}
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
				// 메인페이지에서 장바구니 담기
				if(url.equals("home")) {
					path = "redirect:/product/productList";
				}
				// 상세페이지 장바구니 담기
				else if(url.equals("detail")) {
					path = "redirect:/product/productDetail?product_no=" + product_no + "&product_category=" + product_category;		
				}
			} // end of else
		} // end of else if
			
		return path;
	}
	
	/************************ 장바구니 수량 변경 ****************************/
	@GetMapping("cartUpdate")
	public Object cartUpdate(HttpServletRequest req) {
		logger.info("CartController => cart/cartUpdate 호출 ");
		int result = 0;
		String path = "redirect:/cart/cartList";
		HttpSession session = req.getSession();
		String mem_id = (String)session.getAttribute("mem_id");
		List<CartVO> sCartList = (ArrayList<CartVO>)session.getAttribute("cartList");
		Map<String,Object> pMap = new HashMap<>();
		HashMapBinder hmb = new HashMapBinder(req);
		hmb.bind(pMap);
		
		int product_no = Integer.valueOf((String) pMap.get("product_no"));
		pMap.put("product_no", product_no);
		pMap.put("mem_id", mem_id);
		
		// 회원 수량 변경
		if(mem_id != null) {
			result = cartLogic.cartUpdate(pMap);
			
		// 비회원 수량 변경
		} else if(mem_id ==null && sCartList !=null) {
			String btn = (String)pMap.get("btn");
			String no = String.valueOf(product_no);
			
			// 수량 증가할 아이템 list에서 찾은 후 수량 변경
			for(CartVO item : sCartList) {
				if(item.getProduct_no().equals(no)) {
					int count = item.getProduct_count();
					// 수량 증가/감소 분기
					if(btn.equals("plus")) {
						item.setProduct_count(count + 1);
						logger.info("수량 1 증가");
					} else {
						item.setProduct_count(count - 1);
						logger.info("수량 1 감소");
					}
					break;
				}
			}
		}
		return path;
	}
	
	/************************ 장바구니 아이템 삭제 ****************************/
	@GetMapping("/cartDelete")
	public Object cartDelete(HttpServletRequest req) {
		logger.info("CartController => cart/cartDelete 호출 ");
		
		int result = 0;
		String path = "cart/cartList";
		Map<String,Object> pMap = new HashMap<>();
		HashMapBinder hmb = new HashMapBinder(req);
		hmb.bind(pMap);
		HttpSession session = req.getSession();
		String mem_id = (String)session.getAttribute("mem_id");
		List<CartVO> sCartList = (ArrayList<CartVO>)session.getAttribute("cartList");
		int product_no = Integer.valueOf((String) pMap.get("product_no"));
		pMap.put("product_no", product_no);
		pMap.put("mem_id", mem_id);
		
		// 회원 장바구니 삭제
		if(mem_id != null) {
			result = cartLogic.cartDelete(pMap);
			
		// 비회원 장바구니 삭제
		}else if(mem_id ==null && sCartList !=null) {
			String btn = (String)pMap.get("btn");
			String no = String.valueOf(product_no);
			
			// 장바구니에서 삭제할 아이템 list에서 찾은 후 삭제
			for(CartVO item : sCartList) {
				if(item.getProduct_no().equals(no)) {
					sCartList.remove(item);
					break;
					
				}
			}
		}
		return path;
	}

}
