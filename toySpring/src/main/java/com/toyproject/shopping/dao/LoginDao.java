package com.toyproject.shopping.dao;

import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.toyproject.shopping.controller.LoginController;

@Service
public class LoginDao {
	Logger logger = LoggerFactory.getLogger(LoginDao.class);
	// 물리적인 DB서버와 연결
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	// 쿼리문의 아이디와 메소드 이름은 통일함
	public String getId(Map<String, Object> pMap) {
		String result = null;
		logger.info("LoginDao ===> getId 호출 성공");
		result = sqlSessionTemplate.selectOne("getId", pMap);
		logger.info("조회된 사용자 id는 " + result);
		return result;
	}
	
}
