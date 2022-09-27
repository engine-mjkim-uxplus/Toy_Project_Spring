package com.toyproject.shopping.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
