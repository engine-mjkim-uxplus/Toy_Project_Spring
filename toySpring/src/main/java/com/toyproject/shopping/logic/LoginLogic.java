package com.toyproject.shopping.logic;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.toyproject.shopping.dao.LoginDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LoginLogic {
	
	private final LoginDao loginDao;
	
	public LoginLogic(LoginDao loginDao) {
	  this.loginDao = loginDao;
	}
	@Transactional(readOnly = true)
	public String login(Map<String, Object> pMap){
		log.info("login 호출 성공");
		String mem_id = null;
		try {
			mem_id = loginDao.getId(pMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mem_id; 
	}
}
