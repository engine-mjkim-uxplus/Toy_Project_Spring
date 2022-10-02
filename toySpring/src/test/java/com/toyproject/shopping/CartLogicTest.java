package com.toyproject.shopping;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.toyproject.shopping.dao.CartDao;
import com.toyproject.shopping.logic.CartLogic;
import com.vo.CartVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/data-context.xml")
@Transactional
public class CartLogicTest {
	@Autowired DataSource ds;
	@Autowired CartLogic cartLogic;
	@Autowired CartDao cartDao;

	@Test
	public void cartListTest() throws Exception {
		System.out.println("cartList() 테스트");
		String mem_id = "mjey54";
		List<CartVO> cartList = null;
		cartList = cartDao.getCartList(mem_id);
		System.out.println("조회한 cartList = " + cartList);
		for(CartVO cvo : cartList) 
			System.out.println("조회한 cartList = " + cvo.toString());
	}
	@Test
	public void cartDelete() throws Exception {
		System.out.println("cartDelete() 테스트");
		int product_no = 107;
		String mem_id = "mjey54";
		Map<String,Object> pMap = new HashMap<>();
		pMap.put("product_no",product_no);
		pMap.put("mem_id",mem_id);
		cartDao.cartDelete(pMap);
	}
	@Test
	public void cartInsert() throws Exception {
		System.out.println("cartInsert() 테스트");
		int result = 0;
		String product_name = "cap";
		int product_no = 108;
		int product_count = 2;
		String product_img = "";
		int product_price = 20000;
		String produt_category = "모자";
//		CartVO item= new CartVO(product_name,product_no,product_count,product_img,product_price,product_category);
		Map<String,Object> pMap = new HashMap<>();
		pMap.put("product_name", product_name);
		pMap.put("product_no", product_no);
		pMap.put("product_count", product_count);
		pMap.put("product_img", product_img);
		pMap.put("product_price", product_price);
		pMap.put("produt_category", produt_category);
		pMap.put("mem_id", "mjey54");
		result = cartDao.cartInsert(pMap);
		assertTrue(result > 0 );
		
		
	}
	
}
