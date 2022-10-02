package com.toyproject.shopping.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.vo.CouponVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderDao {
	
	private final SqlSession sqlSession; // 트랜잭션 처리를 위해 공유 자원으로 등록 
	
	public OrderDao(SqlSession sqlSession ) {
		this.sqlSession = sqlSession;
	}
	
	/************************** 쿠폰 존재여부 확인 *****************************/
	public int searchIsCoupon(String mem_id) {
		log.info("OrderDao ===> searchIsCoupon 호출");
		int result = 0;
		result = sqlSession.selectOne("searchIsCoupon", mem_id);
		log.info("쿠폰 존재 여부 : " + result);
		return result;
	}
	
	/************************보유쿠폰 정보 가져오기 ***********************/
	public List<CouponVO> getCouponList(String mem_id) {
		log.info("OrderDao ===> getCouponList 호출");
		List<CouponVO> couponList = null;
		couponList = sqlSession.selectList("getCouponList", mem_id);
		log.info("조회된 couponList " + couponList);
		
		return couponList;
	}
	
	/************************ 결제 후 회원 주문 테이블 등록***********************/
	public void orderMinsert(Map<String, Object> pMap) throws Exception{
		log.info("OrderDao ===> orderMinsert 호출");
		sqlSession.insert("orderMinsert", pMap);
	}
	
	/************************ 결제 후 결제상품 장바구니 삭제 ***********************/
	public void cartMdelete(Map<String, Object> pMap) throws Exception {
		log.info("OrderDao ===> orderMdelete 호출");
		sqlSession.delete("cartMdelete", pMap);
	}
	
	/************************ 결제 후 회원 쿠폰 / 포인트 사용시 업데이트 ***********************/
	public void orderMupdate(Map<String, Object> pMap) throws Exception{
		log.info("OrderDao ===> orderMupdate 호출");
		sqlSession.update("orderMupdate", pMap);
	}
	
	/************************ 쿠폰 사용시 사용한 쿠폰 coupon_zip에서 삭제 ***********************/
	public void couponDelete(Map<String, Object> pMap) throws Exception {
		log.info("OrderDao ===> couponDelete 호출");
		sqlSession.delete("couponDelete", pMap);
	}
	/************************** 결제후 비회원 주문 테이블 등록***************************/
	public void orderInsert(Map<String, Object> pMap) throws Exception{
		log.info("OrderDao ===> orderInsert 호출");
		sqlSession.insert("orderInsert", pMap);
	}
	/************************** 비회원 주문 리스트 *************************************/
	public List<Map<String, Object>> unmemberList(Map<String, Object> pMap) {
		log.info("OrderDao ===> unmemberList 호출");
		
		List<Map<String,Object>> unmemberList = null;
		unmemberList = sqlSession.selectList("unMemberList", pMap);
		
		return unmemberList;
	}
	/************************* 비회원 구매상태 수정  ***********************************/
	public int orderUnmemberSelect(Map<String, Object> pMap) {
		log.info("OrderDao ===> orderUnmemberSelect 호출");
	
		int result = 0;
		result = sqlSession.update("unmemberUpdateState",pMap);
		
		return result;
	}
	
}

