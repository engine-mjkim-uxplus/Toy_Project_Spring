package com.toyproject.shopping.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.toyproject.shopping.dao.OrderDao;
import com.vo.CouponVO;
@Service
public class OrderLogic {
	Logger logger = LoggerFactory.getLogger(OrderLogic.class);
	
	private final OrderDao orderDao;
	
	public OrderLogic(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	
	/****************** 멤버 쿠폰 조회(MemberLogig으로 옮겨야함)******************/
	@Transactional(readOnly = true)
	public List<CouponVO> memberCoupon(String mem_id, Map<String,Object> rMap) {
		logger.info("OrderLogic => memberCoupon 호출");
		rMap.clear();
		
		List<CouponVO> couponList = null; 
		int isCoupon = 0; 
		
		// 멤버의 쿠폰 존재여부(0: 없음, 1: 존재)
		isCoupon = orderDao.searchIsCoupon(mem_id);
		
		// 쿠폰이 존재할 경우 쿠폰 정보 가져오기
		if(isCoupon > 0) {
			logger.info("쿠폰이 존재함");
			couponList = orderDao.getCouponList(mem_id);
			rMap.put("result", 1);
		} else {  
			logger.info("쿠폰이 존재하지 않음");
			couponList = new ArrayList();
			rMap.put("result", 0);
		} 
		return couponList; 
	}
	
	/****************** 주문 결제******************/
	@Transactional(rollbackFor = Exception.class)
	public void memOrder(Map<String, Object> pMap) throws Exception{
		logger.info("OrderLogic => memOrder 호출");
		
		int insertResult = 0;
		int cartDeleteResult = 0;
		int mUpdateResult = 0;
		int couponDeleteResult = 0;
		int coupon = (Integer)pMap.get("coupon");
		int point = (Integer)pMap.get("point");
		// 1. shopping_order insert
		orderDao.orderMinsert(pMap);
		// 2. cart 에서 제거
		orderDao.cartMdelete(pMap);
		// 3. 쿠폰을 사용 하였거나 point를 사용 하였다면 Update
		if(coupon > 0 || point > 0) {
			orderDao.orderMupdate(pMap);
			orderDao.couponDelete(pMap);
		}
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void noMemOrder(Map<String, Object> pMap) {
		logger.info("OrderLogic => noMemOrder 호출");		
		try {
          orderDao.orderInsert(pMap);
        } catch (Exception e) {
          e.printStackTrace();
        }
	}
	
	@Transactional(readOnly = true)
	public List<Map<String, Object>> unmemberList(Map<String, Object> pMap) {
		logger.info("OrderLogic => unmemberList 호출");
		List<Map<String,Object>> unmemberList = null;
		unmemberList = orderDao.unmemberList(pMap);
		return unmemberList;
	}
	
	@Transactional
	public void orderUnmemberSelect(Map<String, Object> pMap) {
		logger.info("MemberLogic: orderUnmemberSelect 호출");
		orderDao.orderUnmemberSelect(pMap);		
//		return result;
	}
}
