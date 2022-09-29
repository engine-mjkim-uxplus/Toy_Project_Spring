package com.toyproject.shopping.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.toyproject.shopping.dao.OrderDao;
import com.vo.CouponVO;

public class OrderLogic {
	Logger logger = LoggerFactory.getLogger(OrderLogic.class);
	@Autowired
	OrderDao orderDao;
	
	/****************** 멤버 쿠폰 조회(MemberLogig으로 옮겨야함)******************/
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
	public void memOrder(Map<String, Object> pMap) {
		logger.info("OrderLogic => memOrder 호출");
		
		int insertResult = 0;
		int cartDeleteResult = 0;
		int mUpdateResult = 0;
		int couponDeleteResult = 0;
		int coupon = (Integer)pMap.get("coupon");
		int point = (Integer)pMap.get("point");
		// 1. shopping_order insert
		insertResult = orderDao.orderMinsert(pMap);
		// 2. cart 에서 제거
		cartDeleteResult = orderDao.cartMdelete(pMap);
		// 3. 쿠폰을 사용 하였거나 point를 사용 하였다면 Update
		if(coupon > 0 || point > 0) {
			mUpdateResult = orderDao.orderMupdate(pMap);
			couponDeleteResult = orderDao.couponDelete(pMap);
		}
		// 트랜잭션 관련 
		// 쿠폰을 사용하였을 경우
		if(coupon > 0) {
			if(insertResult > 0 && cartDeleteResult > 0 && mUpdateResult > 0 && couponDeleteResult > 0) {
				OrderDao.sqlSession.commit();
				OrderDao.sqlSession.close();
			} else {
				OrderDao.sqlSession.rollback();
				OrderDao.sqlSession.close();
			}
		}
		// 쿠폰을 사용하지 않았을 경우
		else if(coupon == 0) {
			if(insertResult > 0 && cartDeleteResult > 0 && mUpdateResult > 0 ) {
				OrderDao.sqlSession.commit();
				OrderDao.sqlSession.close();
			} else {
				OrderDao.sqlSession.rollback();
				OrderDao.sqlSession.close();
			}
		}
	}

	public void noMemOrder(Map<String, Object> pMap) {
		logger.info("OrderLogic => noMemOrder 호출");
		int result = 0;
		
		result = orderDao.orderInsert(pMap);
	}

	public List<Map<String, Object>> unmemberList(Map<String, Object> pMap) {
		logger.info("OrderLogic => unmemberList 호출");
		List<Map<String,Object>> unmemberList = null;
		unmemberList = orderDao.unmemberList(pMap);
		return unmemberList;
	}

	public int orderUnmemberSelect(Map<String, Object> pMap) {
		logger.info("MemberLogic: orderUnmemberSelect 호출");
		int result = 0;
		result = orderDao.orderUnmemberSelect(pMap);
		
		return result;
	}
}
