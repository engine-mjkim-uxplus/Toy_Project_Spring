package com.toyproject.shopping.controller;

import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.toyproject.shopping.logic.ProductLogic;
import com.util.HashMapBinder;
import com.vo.ProductVO;

@Controller
@RequestMapping("product")
public class ProductController {
	Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	ProductLogic productLogic;
	
	@GetMapping("/productList")
	public String homePage(Model model, HttpSession session) {
		
		List<ProductVO> productList = null;
		productList = productLogic.productList();
		model.addAttribute("productList", productList);
		
		String id =(String) session.getAttribute("mem_id");
		
		
		return "home";
	}
	
	@GetMapping("/productDetail")
	public String detailPage(@RequestParam Map<String, Object> pMap, Model model) {
		
		ProductVO product = null;
		product = productLogic.productDetail(pMap);
		model.addAttribute("product", product);
		
		return "detail";
	}
	
	@PostMapping("/productInsertLike")
	@ResponseBody
	public Map<String,Object> addLike(@RequestBody Map<String,Object> pMap) {
		logger.info("addLike 호출");
		
		Map<String,Object> rMap = productLogic.addLike(pMap);

		return rMap;
	}
	
	/*********************** 상품 리뷰 추가 요청 ***********************/
	@PostMapping("/productInsertReview")
	public String productInsertReview(HttpServletRequest req, Model model) {
		logger.info("ProductController: productInsertReview 호출");
		
		Map<String,Object> pMap = new HashMap<>();
		HashMapBinder hmb = new HashMapBinder(req);
		hmb.multiBind(pMap);
		
		System.out.println("pMap==>"+pMap.toString());
		
		productLogic.productInsertReview(pMap);
		String no = (String) pMap.get("product_no");
		String category = (String) pMap.get("product_category");
		
		String path = "redirect:./productDetail?product_no="+no+"&product_category="+category;
		return path;
	}

}
