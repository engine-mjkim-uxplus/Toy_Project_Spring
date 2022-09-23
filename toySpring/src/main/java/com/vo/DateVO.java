package com.vo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateVO {

	private Date date = null;
	private SimpleDateFormat format = null;
	
	private String today = null;
	
	private String aWeekAgo = null;
	private String aMonthAgo =null;
	private String threeMonthsAgo =null;
	
	public DateVO() {
		this.date = new Date();
		this.format = new SimpleDateFormat("yyyy-MM-dd");
	}
	

	public String getToday() {
		today = format.format(date);      
		return today;
	}
	public void setToday(String today) {
		this.today = today;
	}


	public String getaWeekAgo() {
		Calendar cal = Calendar.getInstance();    
		cal.add(Calendar.DATE , -7); 
		aWeekAgo = format.format(cal.getTime());
		return aWeekAgo;
	}
	public void setaWeekAgo(String aWeekAgo) {
		this.aWeekAgo = aWeekAgo;
	}


	public String getaMonthAgo() {
		Calendar cal = Calendar.getInstance();    
		cal.add(Calendar.MONTH , -1);    
		aMonthAgo = format.format(cal.getTime());
		return aMonthAgo;
	}
	public void setaMonthAgo(String aMonthAgo) {
		this.aMonthAgo = aMonthAgo;
	}


	public String getThreeMonthsAgo() {
		Calendar cal = Calendar.getInstance();    
		cal.add(Calendar.MONTH , -3);    
		threeMonthsAgo = format.format(cal.getTime());
		return threeMonthsAgo;
	}
	public void setThreeMonthsAgo(String threeMonthsAgo) {
		this.threeMonthsAgo = threeMonthsAgo;
	}


	public static void main(String[] args) {
		DateVO date = new DateVO();
		
		// 오늘
		System.out.println("오늘: "+date.getToday());
		
		// 일주일 전
		System.out.println("일주일전: "+date.getaWeekAgo());
		
		// 한달 전
		System.out.println("한달전: "+date.getaMonthAgo());
		
		// 세달 전
		System.out.println("세달전: "+date.getThreeMonthsAgo());
	}

}
