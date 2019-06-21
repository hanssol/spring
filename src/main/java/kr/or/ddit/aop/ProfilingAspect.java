package kr.or.ddit.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class ProfilingAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(ProfilingAspect.class);
	
	@Pointcut("execution(* kr.or.ddit..service.*.*(..))")
	public void dummy() {
		
	}
	
	@Around("dummy()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		// business logic 실행 전
		long start = System.currentTimeMillis();
		logger.debug("Profiling Asepct around method before : {}", start);
			
		Object[] methodArgs = joinPoint.getArgs();
		Object returnObj = joinPoint.proceed(methodArgs);
		
		// business logic 실행 후
		long end = System.currentTimeMillis();
		logger.debug("Profiling Asepct around method after : {}", end);
		logger.debug("sayHello Profiling : {}", end-start);
		
		return returnObj;
	}
	
	
}
