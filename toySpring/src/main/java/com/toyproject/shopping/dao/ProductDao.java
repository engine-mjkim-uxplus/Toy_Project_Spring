package com.toyproject.shopping.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vo.ProductVO;

@Repository
public class ProductDao {

	Logger logger = LoggerFactory.getLogger(ProductDao.class);
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public List<ProductVO> productList() {
		logger.info("ProductDao==> productList 호출");
		List<ProductVO> productList = null;
		
		try {
			productList = sqlSessionTemplate.selectList("productList");
		} catch (Exception e) {
			logger.info("Exception : "+e.toString());
		}
		
		return productList;
	}

	public ProductVO getProduct(Map<String, Object> pMap) {
		ProductVO product = null;
		
		try {
			product = sqlSessionTemplate.selectOne("getProduct",pMap);
		} catch (Exception e) {
			logger.info("Exception : "+e.toString());
		}
		
		return product;
	}

	public List<Map<String, Object>> getRelatedProducts(Map<String, Object> pMap) {
		List<Map<String,Object>> productList = null;
		
		try {
			productList = sqlSessionTemplate.selectList("getRelatedProducts",pMap);
		} catch (Exception e) {
			logger.info("Exception : "+e.toString());
		}
		
		return productList;
	}

	public List<Map<String, Object>> getReviewList(Map<String, Object> pMap) {
		List<Map<String,Object>> reviewList = null;
		
		try {
			reviewList = sqlSessionTemplate.selectList("getReviewList",pMap);
		} catch (Exception e) {
			logger.info("Exception : "+e.toString());
		}
		
		return reviewList;
	}

	public List<Integer> selectLikeList(Map<String, Object> pMap) {
		List<Integer> result = null;
		
		System.out.println("selectLikeList==>"+pMap.toString());
		try {
			result = sqlSessionTemplate.selectList("likeList",pMap);
		} catch (Exception e) {
			logger.info("Exception : "+e.toString());
		}
		
		return result;
	}

	public void addLike(Map<String, Object> pMap) {
		logger.info("ProductDao : addLike 호출 성공");
		sqlSessionTemplate.update("likeUpdate",pMap);
	}
	
	public void MemberLikeUpdate(Map<String, Object> pMap) {
		logger.info("ProductDao : MemberLikeUpdate 호출 성공");
		sqlSessionTemplate.update("MemberLikeUpdate",pMap);
	}

	public void InsertReview(Map<String, Object> pMap) {
		logger.info("ProductDao: InsertReview 호출 성공");
		sqlSessionTemplate.update("InsertReview",pMap);
	}
	
	public void InsertReviewCount(Map<String, Object> pMap) {
		logger.info("ProductDao: InsertReviewCount 호출 성공");
		sqlSessionTemplate.update("InsertReviewCount",pMap);

	}


}
