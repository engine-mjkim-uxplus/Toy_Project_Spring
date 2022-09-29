package com.toyproject.shopping.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.vo.CouponVO;

public class OrderDao {
	Logger logger = LoggerFactory.getLogger(OrderDao.class);
	@Autowired
	public static SqlSessionTemplate sqlSessionTemplate; // 트랜잭션 처리를 위해 공유 자원으로 등록 
	
	/************************** 쿠폰 존재여부 확인 *****************************/
	public int searchIsCoupon(String mem_id) {
		logger.info("OrderDao ===> searchIsCoupon 호출");
		int result = 0;
		result = sqlSessionTemplate.selectOne("searchIsCoupon", mem_id);
		logger.info("쿠폰 존재 여부 : " + result);
		return result;
	}
	
	/************************보유쿠폰 정보 가져오기 ***********************/
	public List<CouponVO> getCouponList(String mem_id) {
		logger.info("OrderDao ===> getCouponList 호출");
		List<CouponVO> couponList = null;
		couponList = sqlSessionTemplate.selectList("getCouponList", mem_id);
		logger.info("조회된 couponList " + couponList);
		
		return couponList;
	}
	
	/************************ 결제 후 회원 주문 테이블 등록***********************/
	public int orderMinsert(Map<String, Object> pMap) {
		logger.info("OrderDao ===> orderMinsert 호출");
		int result = 0;
		logger.info(pMap.get("productList"));
		result = sqlSessionTemplate.insert("orderMinsert", pMap);
		logger.info("Insert 결과 : " + result);
		return result;
	}
	
	/************************ 결제 후 결제상품 장바구니 삭제 ***********************/
	public int cartMdelete(Map<String, Object> pMap) {
		logger.info("OrderDao ===> orderMdelete 호출");
		int result = 0;
		result = sqlSessionTemplate.delete("cartMdelete", pMap);
		logger.info("delete 결과 : " + result);
	
		return result;
	}
	
	/************************ 결제 후 회원 쿠폰 / 포인트 사용시 업데이트 ***********************/
	public int orderMupdate(Map<String, Object> pMap) {
		logger.info("OrderDao ===> orderMupdate 호출");
		int result = 0;
		result = sqlSessionTemplate.update("orderMupdate", pMap);
		logger.info("update 결과 : " + result);
		return result;
	}
	
	/************************ 쿠폰 사용시 사용한 쿠폰 coupon_zip에서 삭제 ***********************/
	public int couponDelete(Map<String, Object> pMap) {
		logger.info("OrderDao ===> couponDelete 호출");
		int result = 0;
		result = sqlSessionTemplate.delete("couponDelete", pMap);
		logger.info("couponDelete 결과 : " + result);
		return result;
	}
	/************************** 결제후 비회원 주문 테이블 등록***************************/
	public int orderInsert(Map<String, Object> pMap) {
		logger.info("OrderDao ===> orderInsert 호출");
		int result = 0;
		logger.info(pMap.get("productList"));
		result = sqlSessionTemplate.insert("orderInsert", pMap);
		logger.info("Insert 결과 : " + result);
		return result;
	}

	public List<Map<String, Object>> unmemberList(Map<String, Object> pMap) {
		logger.info("OrderDao ===> unmemberList 호출");
		
		List<Map<String,Object>> unmemberList = null;
		unmemberList = sqlSessionTemplate.selectList("unMemberList", pMap);
		
		return unmemberList;
	}

	public int orderUnmemberSelect(Map<String, Object> pMap) {
		logger.info("OrderDao ===> orderUnmemberSelect 호출");
	
		int result = 0;
		result = sqlSessionTemplate.update("unmemberUpdateState",pMap);
		
		return result;
	}
	
}

