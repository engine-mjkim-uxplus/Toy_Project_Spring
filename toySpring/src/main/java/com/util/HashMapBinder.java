package com.util;

import java.io.File;
import java.util.Enumeration;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/*
 * 텍스트 파일 처리(문자열 처리) - 숫자도 문자열 처리 - 숫자도 문자열로 처리 후 변경해서 
 * 사용자가 화면에 입력하는 값을 받아오기 위해서 공통 코드를 만듦
 * 오라클 테이블의 컬럼의 수가 50개이면 req.getParameter()를 50번 써야하는 것임
 */
public class HashMapBinder {
	Logger logger = LoggerFactory.getLogger(HashMapBinder.class);
	HttpServletRequest req = null;
	HttpSession session = null;
	//첨부 파일 처리에 필요한 변수 선언 - 바이너리 타입 처리를 위해
	MultipartRequest multi = null;
	//첨부 파일 업로드의 물리적인 경로 이름
	String realFolder = null;
	//첨부파일의 한글 처리
	String encType = "utf-8";
	//첨부파일의 최대 크기
	int maxSize = 5*1024*1024;//5MB
	
	public HashMapBinder (HttpServletRequest req) {
		this.req = req;
	}
	
	public void multiBind(Map<String,Object> pMap) {
		realFolder = req.getSession().getServletContext().getRealPath("/reviewimg");

		System.out.println("realFolder :"+realFolder);


		pMap.clear();
		try {
			multi = new MultipartRequest(req, realFolder, maxSize, encType, new DefaultFileRenamePolicy());
		} catch (Exception e) {
			logger.info("Exception:"+e.toString());
		}
		Enumeration<String> em = multi.getParameterNames();
		while(em.hasMoreElements()) {
			String key = em.nextElement();
			pMap.put(key, multi.getParameter(key));
		}
		//기존에 클라이언트에서 받아온 정보를 처리하기
		//첨부파일에 대한 정보 받아오기
		Enumeration<String> files = multi.getFileNames();
		//만일 폼 전송에서 업로드할 파일명이 존재하면...
		if(files !=null) {
			//업로드 대상 파일을 객체로 만듦
			File file = null;
			while(files.hasMoreElements()) {
				String fname = files.nextElement();
				String filename = multi.getFilesystemName(fname);
				pMap.put("review_img", filename);
				if(filename !=null && filename.length()>1) {
					file = new File(realFolder+"\\"+filename);
				}
				logger.info(file.toString());
			}
		}
		logger.info(pMap.toString());
	}
	
	// 사용자의 요청파라미터 값 처리 메서드
	public void bind(Map<String,Object> pMap) {
		pMap.clear(); // 초기화하고 사용
		// html화면에 정의된 input name값들을 모두 담아줌
		Enumeration<String> em = req.getParameterNames();
		while(em.hasMoreElements()) {
			// key값 꺼내기
			String key = em.nextElement(); 
			String[] keys = req.getParameterValues(key); // 키 하나당 여러개의 값(배열)
			if(keys != null && keys.length > 1) {
				pMap.put(key, keys);
			} else {
				pMap.put(key, req.getParameter(key));
			}
		}
		logger.info(pMap.toString());
	}/////////// end of bind
}
