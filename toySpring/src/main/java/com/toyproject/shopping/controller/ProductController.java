package com.toyproject.shopping.controller;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.toyproject.shopping.logic.ProductLogic;
import com.vo.ProductVO;

@Controller
@RequestMapping("product")
public class ProductController {
	Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	ProductLogic productLogic;
	
	@GetMapping("/productList")
	public String homePage(Model model) {
		
		List<ProductVO> productList = null;
		productList = productLogic.productList();
		model.addAttribute("productList", productList);
		
		return "home";
	}
	
	@GetMapping("/productDetail")
	public String detailPage(@RequestParam Map<String, Object> pMap, Model model) {
		
		ProductVO product = null;
		product = productLogic.productDetail(pMap);
		model.addAttribute("product", product);
		
		return "detail";
	}
	
}
