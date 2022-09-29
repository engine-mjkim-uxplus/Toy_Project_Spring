package com.toyproject.shopping.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.toyproject.shopping.dao.CartDao;
import com.vo.CartVO;

public class CartLogic {
	Logger logger = LoggerFactory.getLogger(CartLogic.class);
	@Autowired
	CartDao cartDao;
	
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

	public int cartUpdate(Map<String, Object> pMap) {
		logger.info("CartLogic ===> cartUpdate 호출");
		int result = 0;
		try {
			result = cartDao.cartUpdate(pMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

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

	public String cartSearch(Map<String, Object> pMap) {
		logger.info("CartLogic ===> cartSearch 호출");
		String result = null;
		try {
			result = cartDao.cartSearch(pMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int cartInsert(Map<String, Object> pMap) {
		logger.info("CartLogic ===> cartInsert 호출");
		int result = 0;
		try {
			result = cartDao.cartInsert(pMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
}
