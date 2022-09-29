package com.toyproject.shopping.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.vo.CartVO;

public class CartDao {
	Logger logger = LoggerFactory.getLogger(CartDao.class);
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	// cart 페이지 조회
	public List<CartVO> getCartList(String mem_id) throws Exception {
		logger.info("CartDao ===> getCartList 호출 성공");
		logger.info("사용자 id는 " + mem_id);
		List<CartVO> cartList = null;
		
			cartList = sqlSessionTemplate.selectList("getCartList", mem_id);
			logger.info("조회된 cartList " + cartList);

		
		return cartList;
	}
	// cart 상품 수량 변경(Update)
	public int cartUpdate(Map<String, Object> pMap) throws Exception {
		logger.info("CartDao ===> cartUpdate 호출 성공");
		int result = 0;
		
			result = sqlSessionTemplate.update("cartUpdate", pMap);
			logger.info("수량변경 결과 " + result);
			logger.info("cart 업데이트 결과 " + result);
			
		return result;
	}
	// cart 상품 삭제(Delete)
	public int cartDelete(Map<String, Object> pMap) throws Exception {
		logger.info("CartDao ===> cartDelete 호출 성공");
		int result = 0;
		
			result = sqlSessionTemplate.delete("cartDelete", pMap);
		return result;
	}
	// 장바구니 담기 전 조회
	public String cartSearch(Map<String, Object> pMap) throws Exception {
		logger.info("CartDao ===> cartSearch 호출 성공");
		String result = null;
			result = sqlSessionTemplate.selectOne("cartSearch", pMap);
		return result;
	}
	
	// 장바구니 담기
	public int cartInsert(Map<String, Object> pMap) throws Exception {
		logger.info("CartDao ===> cartInsert 호출 성공");
		int result = 0;
			result = sqlSessionTemplate.insert("cartInsert", pMap);
		return result;
	}

}
