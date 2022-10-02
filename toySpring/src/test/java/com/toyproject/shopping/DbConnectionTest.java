package com.toyproject.shopping;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/spring/data-context.xml")
public class DbConnectionTest {
	@Autowired
	DataSource ds;

	@Before
	public void setUp() throws Exception {
		System.out.println("------------테스트 시작--------------");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("------------테스트 종료--------------");
	}

	@Test
	public void dbConnectionTest() throws Exception {
		Connection conn = ds.getConnection();
		
		System.out.println("연결된 Connection = " + conn);
		assertTrue(conn!=null);
		
		assertEquals(1, 1);
	}

}
