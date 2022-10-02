package com.toyproject.shopping.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import com.vo.CouponVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
	public int orderMinsert(Map<String, Object> pMap) throws Exception{
		log.info("OrderDao ===> orderMinsert 호출");
		int result = 0;
		log.info(pMap.get("productList"));
		result = sqlSession.insert("orderMinsert", pMap);
		log.info("Insert 결과 : " + result);
		return result;
	}
	
	/************************ 결제 후 결제상품 장바구니 삭제 ***********************/
	public int cartMdelete(Map<String, Object> pMap) throws Exception {
		log.info("OrderDao ===> orderMdelete 호출");
		int result = 0;
		result = sqlSession.delete("cartMdelete", pMap);
		log.info("delete 결과 : " + result);
	
		return result;
	}
	
	/************************ 결제 후 회원 쿠폰 / 포인트 사용시 업데이트 ***********************/
	public int orderMupdate(Map<String, Object> pMap) throws Exception{
		log.info("OrderDao ===> orderMupdate 호출");
		int result = 0;
		result = sqlSession.update("orderMupdate", pMap);
		log.info("update 결과 : " + result);
		return result;
	}
	
	/************************ 쿠폰 사용시 사용한 쿠폰 coupon_zip에서 삭제 ***********************/
	public int couponDelete(Map<String, Object> pMap) throws Exception {
		log.info("OrderDao ===> couponDelete 호출");
		int result = 0;
		result = sqlSession.delete("couponDelete", pMap);
		log.info("couponDelete 결과 : " + result);
		return result;
	}
	/************************** 결제후 비회원 주문 테이블 등록***************************/
	public int orderInsert(Map<String, Object> pMap) throws Exception{
		log.info("OrderDao ===> orderInsert 호출");
		int result = 0;
		log.info(pMap.get("productList").toString());
		result = sqlSession.insert("orderInsert", pMap);
		log.info("Insert 결과 : " + result);
		return result;
	}

	public List<Map<String, Object>> unmemberList(Map<String, Object> pMap) {
		log.info("OrderDao ===> unmemberList 호출");
		
		List<Map<String,Object>> unmemberList = null;
		unmemberList = sqlSession.selectList("unMemberList", pMap);
		
		return unmemberList;
	}

	public int orderUnmemberSelect(Map<String, Object> pMap) {
		log.info("OrderDao ===> orderUnmemberSelect 호출");
	
		int result = 0;
		result = sqlSession.update("unmemberUpdateState",pMap);
		
		return result;
	}
	
}

