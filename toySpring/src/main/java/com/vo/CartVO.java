package com.vo;

import lombok.Data;

@Data
public class CartVO {
// 상품 카테고리, 상품이름, 상품 이미지, 상품 수량, 상품 가격
	private String product_category;
	private String product_name;
	private String product_img;
	private String product_no;
	private int product_price;
	private int product_count;
	public CartVO() {}
	public CartVO(String product_name,String product_no, int product_count, String product_img, int product_price,
			String product_category) {
		this.product_name = product_name;
		this.product_category = product_category;
		this.product_count = product_count;
		this.product_img = product_img;
		this.product_no = product_no;
		this.product_price = product_price;
	}
	public CartVO(String product_name, String product_img, String product_no, int product_price, int product_count) {
		super();
		this.product_name = product_name;
		this.product_img = product_img;
		this.product_no = product_no;
		this.product_price = product_price;
		this.product_count = product_count;
	}
	
}
