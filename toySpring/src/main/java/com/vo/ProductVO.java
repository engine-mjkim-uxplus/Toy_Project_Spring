package com.vo;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Data;

@Data
public class ProductVO {
	Logger logger = LoggerFactory.getLogger(ProductVO.class);
	
	private int product_no;
	private String product_category;
	private String product_name;
	private int product_price;
	private String product_img;
	private int product_like;
	private int product_reviewcount;
	private int product_buycount;
	
	private List<Map<String,Object>> relatedProducts;
	private List<Map<String,Object>> reviewList;
}