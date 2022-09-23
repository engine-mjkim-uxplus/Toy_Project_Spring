package com.vo;

public class CouponVO {

	 private String coupon_no;
	 private String coupon_name;
	 private String coupon_date;
	 private String coupon_text;
	 private String coupon_price;
	 private int result;
	 
	public CouponVO() {} 
	public CouponVO(int result) {
		this.result = result;
	}
	public String getCoupon_no() {
		return coupon_no;
	}
	public void setCoupon_no(String coupon_no) {
		this.coupon_no = coupon_no;
	}
	public String getCoupon_name() {
		return coupon_name;
	}
	public void setCoupon_name(String coupon_name) {
		this.coupon_name = coupon_name;
	}
	public String getCoupon_date() {
		return coupon_date;
	}
	public void setCoupon_date(String coupon_date) {
		this.coupon_date = coupon_date;
	}
	public String getCoupon_text() {
		return coupon_text;
	}
	public void setCoupon_text(String coupon_text) {
		this.coupon_text = coupon_text;
	}
	public String getCoupon_price() {
		return coupon_price;
	}
	public void setCoupon_price(String coupon_price) {
		this.coupon_price = coupon_price;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
}
