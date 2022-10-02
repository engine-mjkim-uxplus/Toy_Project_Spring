package com.toyproject.shopping.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vo.CartVO;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class CartDao {
	@Autowired
	SqlSession sqlSession;
	
	// cart 페이지 조회
	public List<CartVO> getCartList(String mem_id) throws Exception {
		log.info("CartDao ===> getCartList 호출 성공");
		log.info("사용자 id는 " + mem_id);
		List<CartVO> cartList = null;
		cartList = sqlSession.selectList("getCartList", mem_id);
		log.info("조회된 cartList " + cartList);
		return cartList;
	}
	// cart 상품 수량 변경(Update)
	public int cartUpdate(Map<String, Object> pMap) throws Exception {
		log.info("CartDao ===> cartUpdate 호출 성공");
		int result = 0;
		result = sqlSession.update("cartUpdate", pMap);
		log.info("수량변경 결과 " + result);
		log.info("cart 업데이트 결과 " + result);
			
		return result;
	}
	// cart 상품 삭제(Delete)
	public int cartDelete(Map<String, Object> pMap) throws Exception {
		log.info("CartDao ===> cartDelete 호출 성공");
		int result = 0;
		result = sqlSession.delete("cartDelete", pMap);
		return result;
	}
	// 장바구니 담기 전 조회
	public String cartSearch(Map<String, Object> pMap) throws Exception {
		log.info("CartDao ===> cartSearch 호출 성공");
		String result = null;
		result = sqlSession.selectOne("cartSearch", pMap);
		return result;
	}
	
	// 장바구니 담기
	public int cartInsert(Map<String, Object> pMap) throws Exception {
		log.info("CartDao ===> cartInsert 호출 성공");
		int result = 0;
		result = sqlSession.insert("cartInsert", pMap);
		return result;
	}

}
