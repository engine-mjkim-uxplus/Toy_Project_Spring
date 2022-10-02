package com.toyproject.shopping.logic;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toyproject.shopping.dao.MemberDao;
import com.vo.CouponVO;
import com.vo.MemberVO;
import com.vo.ProductVO;

@Service
public class MemberLogic {
	Logger logger = LoggerFactory.getLogger(MemberLogic.class);
	@Autowired
	private MemberDao memberDao = null;

	public MemberVO Login(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Map<String, Object>> memberListPayment(Map<String, Object> pMap) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Map<String, Object>> memberListReview(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ProductVO> memberListLike(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<CouponVO> memberListCoupon(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Integer> myCouponList(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public int memberInsertCoupon(Map<String, Object> pMap) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int memberUpdateP(Map<String, Object> pMap) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int memberUpdateState(Map<String, Object> pMap) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int memberDelete(Map<String, Object> pMap) {
		// TODO Auto-generated method stub
		return 0;
	}

}
