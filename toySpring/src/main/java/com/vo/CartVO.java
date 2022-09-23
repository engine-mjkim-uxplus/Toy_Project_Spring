package com.vo;

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
	public String getProduct_img() {
		return product_img;
	}
	public void setProduct_img(String product_img) {
		this.product_img = product_img;
	}
	public int getProduct_price() {
		return product_price;
	}
	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}
	public int getProduct_count() {
		return product_count;
	}
	public void setProduct_count(int product_count) {
		this.product_count = product_count;
	}
	public String getProduct_no() {
		return product_no;
	}
	public void setProduct_no(String product_no) {
		this.product_no = product_no;
	}
	
	
}
