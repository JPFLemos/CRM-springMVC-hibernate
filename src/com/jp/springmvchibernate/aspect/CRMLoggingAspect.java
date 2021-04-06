package com.jp.springmvchibernate.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class CRMLoggingAspect {

	// setup logger
	private Logger myLogger = Logger.getLogger(getClass().getName());

	// setup pointcut declarations
	@Pointcut("execution(* com.jp.springmvchibernate.controller.*.*(..))")
	private void forControllerPackage() {
	}

	@Pointcut("execution(* com.jp.springmvchibernate.service.*.*(..))")
	private void forServicePackage() {
	}

	@Pointcut("execution(* com.jp.springmvchibernate.dao.*.*(..))")
	private void forDAOPackage() {
	}

	@Pointcut("forControllerPackage() || forServicePackage() || forDAOPackage()")
	private void forControllerServiceDAO() {
	}

	// add @Before advice
	@Before("forControllerServiceDAO()")
	public void before(JoinPoint joinPoint) {

		MethodSignature methodSig = (MethodSignature) joinPoint.getSignature();

		// display method called

		myLogger.info(">>>>>>>>>>> in @Before: calling method: " + methodSig);

		// display arguments to method
		Object[] args = joinPoint.getArgs();

		for (Object arg : args) {
			if (arg != null) {
				myLogger.info(arg.toString());
			} else {
				myLogger.info("arg is null");
			
			}
		}

	}

	// add @AfterReturning advice
	@AfterReturning(
			pointcut="forControllerServiceDAO()",
			returning="result"
			)
	public void afterReturning(JoinPoint joinPoint, Object result) {
		
		MethodSignature methodSig = (MethodSignature) joinPoint.getSignature();
		
		// display method called

		myLogger.info(">>>>>>>>>>> in @AfterReturning: calling method: " + methodSig);
		
		// display returning data
		
		myLogger.info(">>>>>>>>>>>>>>> result: " + result);
	}
}
