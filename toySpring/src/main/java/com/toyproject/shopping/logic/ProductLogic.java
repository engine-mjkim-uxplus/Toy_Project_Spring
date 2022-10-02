package com.toyproject.shopping.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.toyproject.shopping.dao.ProductDao;
import com.vo.ProductVO;

@Service
public class ProductLogic {
	Logger logger = LoggerFactory.getLogger(ProductLogic.class);
	
	@Autowired
	ProductDao productDao;
	 
	public List<ProductVO> productList() {
		List<ProductVO> productList = null;
		productList = productDao.productList();
		return productList;
	}

	@Transactional(rollbackFor = Exception.class)
	public ProductVO productDetail(Map<String, Object> pMap) {
		/* 사용자가 선택한 상품의 정보 */
		ProductVO product = null;
		product = productDao.getProduct(pMap);
		
		/* 선택한 상품과 관련된 4건의 상품 정보 (기준:LIKE)*/
		List<Map<String,Object>> productList = null;
		productList = productDao.getRelatedProducts(pMap);
		
		/* 선택한 상품의 리뷰 목록 */
		List<Map<String,Object>> reviewList = null;
		reviewList = productDao.getReviewList(pMap);

		product.setRelatedProducts(productList);
		product.setReviewList(reviewList);

		return product;
	}
	

	public Map<String, Object> addLike(Map<String, Object> pMap) {
		Map<String,Object> rMap = new HashMap<>();
		
		List<Integer> result = productDao.selectLikeList(pMap);
		
		if(result.isEmpty() || !result.contains(pMap.get("product_no"))) {
			productDao.addLike(pMap);
			productDao.MemberLikeUpdate(pMap);
			rMap.put("success", true);
			rMap.put("msg", "좋아요가 등록됐습니다.");
		}else {
			rMap.put("success", false);
			rMap.put("msg", "이미 좋아요를 누른 상품입니다.");
		}
		
		return rMap;
	}
	
	/*********************** 상품 리뷰 추가 로직 ***********************/
	public void productInsertReview(Map<String, Object> pMap) {
		logger.info("ProductLogic: productInsertReview 호출");
		productDao.InsertReview(pMap);
		productDao.InsertReviewCount(pMap);
	}
}
