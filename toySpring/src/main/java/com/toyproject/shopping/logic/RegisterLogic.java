package com.toyproject.shopping.logic;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toyproject.shopping.dao.RegisterDao;

@Service
public class RegisterLogic {
	Logger logger = LoggerFactory.getLogger(RegisterLogic.class);
	@Autowired
	private RegisterDao registerDao = null;

	public int registerInsert(Map<String, Object> pMap) {
		logger.info("Logic의 registerInsert호출 성공");
		int result = 0;
		result = registerDao.registerInsert(pMap);
		return result;
	}
	
	public int registerSelect(Map<String, Object> pMap) {
		logger.info("Logic의 registerSelect호출 성공");
		int result = 0;
		result = registerDao.registerSelect(pMap);
		return result;
	}

}
