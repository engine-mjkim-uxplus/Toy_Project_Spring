package com.toyproject.shopping.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterDao {
	Logger logger = LoggerFactory.getLogger(RegisterDao.class);
	@Autowired(required=false)
	private SqlSessionTemplate sqlSessionTemplate = null;

	public int registerInsert(Map<String, Object> pMap) {
		int result = 0;
		try {
			result = sqlSessionTemplate.update("registerInsert",pMap);
			logger.info("result: "+ result);
		} catch (Exception e) {
			logger.info("회원가입DAO Exception : " + e.toString());
		}
		return result;
	}

	public int registerSelect(Map<String, Object> pMap) {
		int result = 0;
		try {
			result = sqlSessionTemplate.selectOne("registerSelect", pMap);
		} catch (Exception e) {
			logger.info("중복체크DAO Exception : " + e.toString());
		} 
		return result;
	}
	
}
