package com.toyproject.shopping.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.toyproject.shopping.dao.MemberDao;
import com.vo.CartVO;
import com.vo.CouponVO;
import com.vo.MemberVO;
import com.vo.ProductReviewVO;
import com.vo.ProductVO;

@Service
public class MemberLogic {
	Logger logger = LoggerFactory.getLogger(MemberLogic.class);
	@Autowired
	private MemberDao memberDao = null;

	public MemberVO Login(String id) {
		logger.info("MemberLogic: memberListPayment 호출");
		MemberVO mVO = memberDao.login(id);
		return mVO;
	}

	public List<Map<String, Object>> memberListPayment(Map<String, Object> pMap) {
		List<Map<String,Object>> orderList = null;
		orderList = memberDao.memberListPayment(pMap);
		return orderList;
	}

	public List<Map<String, Object>> memberListReview(String id) {
		logger.info("MemberLogic: memberListReview 호출");
		List<Map<String, Object>> memberListReview = new ArrayList<>();
		memberListReview = memberDao.memberListReview(id);
		return memberListReview;
	}

	public List<ProductVO> memberListLike(String id) {
		logger.info("MemberLogic: memberListLike 호출");
		List<ProductVO> likeList = new ArrayList<>();
		likeList = memberDao.getMemberListLike(id);
		return likeList;
	}

	public List<CouponVO> memberListCoupon(String id) {
		logger.info("MemberLogic: memberListCoupon 호출");
		List<CouponVO> memberListCoupon = new ArrayList<>();
		memberListCoupon = memberDao.memberListCoupon(id);
		return memberListCoupon;
	}

	public List<Integer> myCouponList(String id) {
		logger.info("MemberLogic: myCouponList 호출");
		List<Integer> myCouponList = new ArrayList<>();
		myCouponList = memberDao.myCouponList(id);
		return myCouponList;
	}

	public int memberInsertCoupon(Map<String, Object> pMap) {
		logger.info("MemberLogic: memberInsertCoupon 호출");
		int result = 0;
		result = memberDao.memberInsertCoupon(pMap);
		if(result > 0) {
			memberDao.memberUpdateCoupon(pMap);
		}
		return result;
	}

	public int memberUpdateP(Map<String, Object> pMap) {
		logger.info("MemberLogic: memberUpdateP 호출");
		int result = 0;
		result = memberDao.memberUpdateP(pMap);
		return result;
	}

	public int memberUpdateState(Map<String, Object> pMap) {
		logger.info("MemberLogic: memberUpdateState 호출");
		int result = 0;
		result = memberDao.memberUpdateState(pMap);
		
		if (pMap.get("state").equals("buy") ) {
			logger.info("구매확정 선택");
			memberDao.pointUpdate(pMap);
		}
		
		return result;
	}
	/****************************** [[ 회원탈퇴시 트랜잭션 처리하기 ]] ******************************/
	@Transactional(rollbackFor = Exception.class)
	public int memberDelete(Map<String, Object> pMap) {
		logger.info("MemberLogic: memberDelete 호출");
		int result = 0;
		List<CartVO> cartList = new ArrayList<>();
		List<ProductVO> likeList = new ArrayList<>();
		List<ProductReviewVO> reviewList = new ArrayList<>();
		List<Map<String,Object>> couponList = null;
		List<Map<String,Object>> orderList = new ArrayList<>();
		logger.info("MemberLogic: 카트 목록 불러오기");
		cartList = memberDao.getCartList(pMap);
		likeList = memberDao.getLikeList(pMap);
		reviewList = memberDao.getReviewList(pMap);
		couponList = memberDao.getCouponList(pMap);
		orderList = memberDao.getOrderList(pMap);
		pMap.put("cartList", cartList);
		pMap.put("likeList", likeList);
		pMap.put("reviewList", reviewList);
		pMap.put("couponList", couponList);
		pMap.put("orderList", orderList);
		if(cartList.size() > 0 && cartList != null) {
			logger.info("MemberLogic: 카트 삭제");
			try {
				memberDao.deleteCart(pMap);
			} catch (Exception e) {
				System.out.println("카트 삭제 도중 예외 발생: "+e.toString());
			}
		}
		if(likeList.size() > 0 && cartList != null) {
			logger.info("MemberLogic: 좋아요 삭제");
			try {
				memberDao.deleteLike(pMap);
			} catch (Exception e) {
				System.out.println("좋아요 삭제 도중 예외 발생: "+e.toString());
			}
		}
		if(reviewList.size() > 0 && reviewList != null) {
			logger.info("MemberLogic: 리뷰 삭제");
			try {
				memberDao.deleteReview(pMap);
			} catch (Exception e) {
				System.out.println("리뷰 삭제 도중 예외 발생: "+e.toString());
			}
		}
		if(couponList.size() > 0 && couponList != null) {
			logger.info("MemberLogic: 쿠폰 삭제");
			try {
				memberDao.deleteCoupon(pMap);
			} catch (Exception e) {
				System.out.println("쿠폰 삭제 도중 예외 발생: "+e.toString());
			}
		}
		if(orderList.size() > 0 && orderList != null) {
			logger.info("MemberLogic: 주문내역 삭제");
			try {
				memberDao.deleteOrder(pMap);
			} catch (Exception e) {
				System.out.println("주문내역 삭제 도중 예외 발생: "+e.toString());
			}
		}
		try {
			result = memberDao.memberDelete(pMap);
		} catch (Exception e) {
			System.out.println("회원탈퇴 도중 예외 발생: "+e.toString());
		}
		return result;
	}

}
