package com.vo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	
	public ProductVO() {}
	
	public ProductVO(int product_no, String product_category, String product_name, int product_price,
			String product_img, int product_like, int product_reviewcount, int product_buycount) {
		logger.info("ProductVO: 생성자 호출");
		this.product_no = product_no;
		this.product_category = product_category;
		this.product_name = product_name;
		this.product_price = product_price;
		this.product_img = product_img;
		this.product_like = product_like;
		this.product_reviewcount = product_reviewcount;
		this.product_buycount = product_buycount;
	}

	public int getProduct_no() {
		return product_no;
	}

	public void setProduct_no(int product_no) {
		this.product_no = product_no;
	}

	public String getProduct_category() {
		return product_category;
	}

	public void setProduct_category(String product_category) {
		this.product_category = product_category;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public int getProduct_price() {
		return product_price;
	}

	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}

	public String getProduct_img() {
		return product_img;
	}

	public void setProduct_img(String product_img) {
		this.product_img = product_img;
	}

	public int getProduct_like() {
		return product_like;
	}

	public void setProduct_like(int product_like) {
		this.product_like = product_like;
	}

	public int getProduct_reviewcount() {
		return product_reviewcount;
	}

	public void setProduct_reviewcount(int product_reviewcount) {
		this.product_reviewcount = product_reviewcount;
	}

	public int getProduct_buycount() {
		return product_buycount;
	}

	public void setProduct_buycount(int product_buycount) {
		this.product_buycount = product_buycount;
	}
}