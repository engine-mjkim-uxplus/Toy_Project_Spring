package com.toyproject.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


@Aspect
@Component 
@Slf4j
public class LoggingAdvice {
	
	@Around("execution(* com.toyproject.shopping.controller.*.*(..))")
	public Object params(ProceedingJoinPoint pjp) throws Throwable{
		log.info("=====================================================================");
		
		MethodSignature MethodSignature = (MethodSignature)pjp.getSignature(); // 핵심코드 정보
		log.info(MethodSignature.getName() + "호출 성공");
		Object[] args = pjp.getArgs();
		
		Object result = pjp.proceed();
		
		for(Object obj: args) {
			log.info("type : " + obj.getClass().getSimpleName() + ", value : " + obj.toString());
		}
//		logger.info("[End] : " + pjp.getSignature().getName()+Arrays.toString(pjp.getArgs()));
		log.info("=====================================================================");
		
		return result;
	}
	

}
