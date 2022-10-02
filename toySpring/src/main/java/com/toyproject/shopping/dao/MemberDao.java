package com.toyproject.shopping.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vo.CartVO;
import com.vo.CouponVO;
import com.vo.MemberVO;
import com.vo.ProductReviewVO;
import com.vo.ProductVO;

@Repository
public class MemberDao {
	Logger logger = LoggerFactory.getLogger(MemberDao.class);
	
	@Autowired(required=false)
	private SqlSessionTemplate sqlSessionTemplate = null;

	public MemberVO login(String id) {
		MemberVO mVO = null;
		Map<String,Object> pMap = new HashMap<>();
		pMap.put("id", id);
		try {
			mVO = sqlSessionTemplate.selectOne("getMember",pMap); //ProductVO			
		} catch (Exception e) {
			logger.info("Exception : "+e.toString());
		} 
		return mVO;
	}

	public List<Map<String, Object>> memberListPayment(Map<String, Object> pMap) {
		logger.info("MemberDao: memberListPayment 호출 성공");
		List<Map<String,Object>> orderList = null;
		try {
			orderList = sqlSessionTemplate.selectList("getOrderList", pMap); 	
			// insert here
		} catch (Exception e) {
			logger.info("Exception : "+e.toString());
		} 
		return orderList;
	}

	public List<Map<String, Object>> memberListReview(String id) {
		logger.info("MemberDao: memberListReview 호출 성공");
		List<Map<String, Object>> memberListReview = null;
		
		try {
			memberListReview = sqlSessionTemplate.selectList("memberListReview",id);
			logger.info(id+", "+memberListReview);
		} catch (Exception e) {
			logger.info("Exception : " + e.toString());
		} 
		return memberListReview;
	}

	public List<ProductVO> getMemberListLike(String id) {
		logger.info("MemberDao: getMemberListLike 호출 성공");
		List<ProductVO> likeList = null;
		
		try {
			likeList = sqlSessionTemplate.selectList("memberListLike",id);
			logger.info(id+", "+likeList);
		} catch (Exception e) {
			logger.info("Exception : " + e.toString());
		} 
		return likeList;
	}

	public List<CouponVO> memberListCoupon(String id) {
		logger.info("MemberDao: memberListCoupon 호출 성공");
		List<CouponVO> memberListCoupon = null;
		try {
			memberListCoupon = sqlSessionTemplate.selectList("memberListCoupon", id);
			logger.info(memberListCoupon.toString());
		} catch (Exception e) {
			logger.info("Exception : " + e.toString());
		} 
		return memberListCoupon;
	}

	public List<Integer> myCouponList(String id) {
		logger.info("MemberDao: myCouponList 호출 성공");
		List<Integer> myCouponList = null;
		try {
			myCouponList = sqlSessionTemplate.selectList("isCoupon",id);
			logger.info(myCouponList.toString());
		} catch (Exception e) {
			logger.info("Exception : " + e.toString());
		} 
		return myCouponList;
	}

	public int memberInsertCoupon(Map<String, Object> pMap) {
		logger.info("MemberDao: memberInsertCoupon 호출 성공");
		int result = 0;
		try {
			result = sqlSessionTemplate.update("memberInsertCoupon",pMap);
			logger.info("result: "+ result);
		} catch (Exception e) {
			logger.info("Exception : " + e.toString());
		} 
		return result;
	}

	public void memberUpdateCoupon(Map<String, Object> pMap) {
		logger.info("MemberDao: memberUpdateCoupon 호출 성공");
		int result = 0;
		try {
			result = sqlSessionTemplate.update("memberUpdateCoupon",pMap);
			logger.info("result: "+ result);
		} catch (Exception e) {
			logger.info("Exception : " + e.toString());
		}
	}

	public int memberUpdateState(Map<String, Object> pMap) {
		logger.info("MemberDao: memberUpdateState 호출 성공");
		
		int result = 0;
		try {
			result = sqlSessionTemplate.update("UpdateState",pMap);
		}catch (Exception e) {
			logger.info("Exception : " + e.toString());
		} 
		return result;
	}

	public void pointUpdate(Map<String, Object> pMap) {
		logger.info("MemberDao: pointUpdate 호출 성공");
		int result = 0;
		try {
			result = sqlSessionTemplate.update("updPoint",pMap);
		}catch (Exception e) {
			logger.info("Exception : " + e.toString());
		}
	}

	public List<CartVO> getCartList(Map<String, Object> pMap) {
		logger.info("MemberDao: getCartList 호출 성공");
		List<CartVO> cartList = null;
		try {
			cartList = sqlSessionTemplate.selectList("getCart",pMap);
		} catch (Exception e) {
			logger.info("Exception : " + e.toString());
		} 
		return cartList;
	}

	public List<ProductVO> getLikeList(Map<String, Object> pMap) {
		logger.info("MemberDao: getLikeList 호출 성공");
		List<ProductVO> likeList = null;
		try {
			likeList = sqlSessionTemplate.selectList("getLike",pMap);
		} catch (Exception e) {
			logger.info("Exception : " + e.toString());
		}
		return likeList;
	}

	public List<ProductReviewVO> getReviewList(Map<String, Object> pMap) {
		logger.info("MemberDao: getReviewList 호출 성공");
		List<ProductReviewVO> reviewList = null;
		try {
			reviewList = sqlSessionTemplate.selectList("getReview",pMap);
		} catch (Exception e) {
			logger.info("Exception : " + e.toString());
		} 
		return reviewList;
	}

	public List<Map<String, Object>> getCouponList(Map<String, Object> pMap) {
		logger.info("MemberDao: getCouponList 호출 성공");
		List<Map<String, Object>> couponList = null;
		try {
			couponList = sqlSessionTemplate.selectList("getCoupon",pMap);
			logger.info(couponList.toString());
		} catch (Exception e) {
			logger.info("Exception : " + e.toString());
		} 
		return couponList;
	}

	public List<Map<String, Object>> getOrderList(Map<String, Object> pMap) {
		logger.info("MemberDao: getOrderList 호출 성공");
		List<Map<String, Object>> orderList = null;
		try {
			orderList = sqlSessionTemplate.selectList("getOrder",pMap);
			logger.info(orderList.toString());
		} catch (Exception e) {
			logger.info("Exception : " + e.toString());
		} 
		return orderList;
	}

	public void deleteCart(Map<String, Object> pMap) {
		logger.info("MemberDao: deleteCart 호출 성공");
		int result = 0;
		try {
			result = sqlSessionTemplate.delete("delCart",pMap);
			logger.info("result: "+ result);
		} catch (Exception e) {
			logger.info("Exception : " + e.toString());
		} 
	}

	public void deleteLike(Map<String, Object> pMap) {
		logger.info("MemberDao: deleteLike 호출 성공");
		int result = 0;
		try {
			result = sqlSessionTemplate.delete("delLike",pMap);
			logger.info("result: "+ result);
		} catch (Exception e) {
			logger.info("Exception : " + e.toString());
		} 
	}

	public void deleteReview(Map<String, Object> pMap) {
		logger.info("MemberDao: deleteReview 호출 성공");
		int result = 0;
		try {
			result = sqlSessionTemplate.delete("delReview",pMap);
			logger.info("result: "+ result);
		} catch (Exception e) {
			logger.info("Exception : " + e.toString());
		} 
	}

	public void deleteCoupon(Map<String, Object> pMap) {
		logger.info("MemberDao: deleteCoupon 호출 성공");
		int result = 0;
		try {
			result = sqlSessionTemplate.delete("delCoupon",pMap);
			logger.info("result: "+ result);
		} catch (Exception e) {
			logger.info("Exception : " + e.toString());
		} 
	}

	public void deleteOrder(Map<String, Object> pMap) {
		logger.info("MemberDao: deleteOrder 호출 성공");
		int result = 0;
		try {
			result = sqlSessionTemplate.delete("delOrder",pMap);
			logger.info("result: "+ result);
		} catch (Exception e) {
			logger.info("Exception : " + e.toString());
		} 
	}

	public int memberDelete(Map<String, Object> pMap) {
		logger.info("MemberDao: memberDelete 호출 성공");
		int result = 0;
		try {
			result = sqlSessionTemplate.update("memberDelete",pMap);
			logger.info("result: "+ result);
		} catch (Exception e) {
			logger.info("Exception : " + e.toString());
		} 
		return result;
	}

	public int memberUpdateP(Map<String, Object> pMap) {
		logger.info("MemberDao: memberUpdateP 호출 성공");
		int result = 0;
		try {
			result = sqlSessionTemplate.update("memberUpdateP",pMap);
			logger.info("result: "+ result);
		} catch (Exception e) {
			logger.info("Exception : " + e.toString());
		} 
		return result;
	}
	
}
