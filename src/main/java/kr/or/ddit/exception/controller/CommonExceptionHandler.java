package kr.or.ddit.exception.controller;

import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@ControllerAdvice
public class CommonExceptionHandler {
	
	@ExceptionHandler(ArithmeticException.class)
	public String handleException() {
		return "exception";
	}
	

	
}
