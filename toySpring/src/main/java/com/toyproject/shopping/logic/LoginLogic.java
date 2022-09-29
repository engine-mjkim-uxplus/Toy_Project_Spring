package com.toyproject.shopping.logic;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.toyproject.shopping.dao.LoginDao;

@Service
public class LoginLogic {
	Logger logger = LoggerFactory.getLogger(LoginLogic.class);
	
	@Autowired
	LoginDao loginDao = null;
	public String login(Map<String, Object> pMap){
		logger.info("login 호출 성공");
		String mem_id = null;
		try {
			mem_id = loginDao.getId(pMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mem_id; 
	}
}
