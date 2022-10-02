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

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class LoginDao {
	@Autowired
	private final SqlSession sqlSession;

	public LoginDao(SqlSession sqlSession) {
	  this.sqlSession = sqlSession;
	}
	// 쿼리문의 아이디와 메소드 이름은 통일함
	public String getId(Map<String, Object> pMap) throws Exception {
		String result = "";
		log.info("LoginDao ===> getId 호출 성공");
		result = sqlSession.selectOne("getId", pMap);
		log.info("조회된 사용자 id는 " + result);
		return result;
	}
	
}
