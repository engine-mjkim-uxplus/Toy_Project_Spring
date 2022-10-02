package com.toyproject.shopping.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.toyproject.shopping.dao.CartDao;
import com.vo.CartVO;
@Service
public class CartLogic {
	Logger logger = LoggerFactory.getLogger(CartLogic.class);
	@Autowired
	CartDao cartDao;
	
	@Transactional(readOnly = true)
	public List<CartVO> cartList(String mem_id) {
		logger.info("CartLogic ===> cartList 호출");
		List<CartVO> cartList = new ArrayList<>();
		try {
			cartList = cartDao.getCartList(mem_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cartList;
	}
	// cart 상품 삭제(Delete)
	@Transactional(rollbackFor = Exception.class)
	public int cartDelete(Map<String, Object> pMap) {
		logger.info("CartLogic ===> cartDelete 호출");
		int result = 0;
		try {
			result = cartDao.cartDelete(pMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	// 카트에 상품이 있는지 조회
	@Transactional(rollbackFor = Exception.class)
	public String cartSearch(Map<String, Object> pMap) {
		logger.info("CartLogic ===> cartSearch 호출");
		String result = null;
		try {
			result = cartDao.cartSearch(pMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(result != null) {
			cartUpdate(pMap);
		// 기존 장바구니에 없는 상품일 경우 CART 테이블에 INSERT
		} else {
			cartInsert(pMap);
		}
		return result;
	}
	//  새로운 상품 카트에 추가 (propagation = Propagation.REQUIRED) - 기본값
	@Transactional(rollbackFor = Exception.class)
	public void cartInsert(Map<String, Object> pMap) {
		logger.info("CartLogic ===> cartInsert 호출");
		try {
			cartDao.cartInsert(pMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	// 기존 카트 상품 수량 변경
	@Transactional(rollbackFor = Exception.class)
	public void cartUpdate(Map<String, Object> pMap) {
		logger.info("CartLogic ===> cartUpdate 호출");
		try {
			cartDao.cartUpdate(pMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
