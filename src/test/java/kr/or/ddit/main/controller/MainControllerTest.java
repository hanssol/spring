package kr.or.ddit.main.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import ch.qos.logback.core.status.Status;
import kr.or.ddit.testenv.ControllerTestEnv;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:kr/or/ddit/config/spring/application-context.xml"
						,"classpath:kr/or/ddit/config/spring/root-context.xml"
					  	,"classpath:kr/or/ddit/config/spring/application-datasource-dev.xml"
						,"classpath:kr/or/ddit/config/spring/application-transaction.xml"})
// 일반 자바 환경 -> 웹 환경
// applicationContext --> 웹 환경의 applicationContext 생성
@WebAppConfiguration
public class MainControllerTest extends ControllerTestEnv{

	/**
	 * Method : mainViewTest
	 * 작성자 : PC03
	 * 변경이력 :
	 * Method 설명 : Main View 호출 테스트
	 * @throws Exception 
	 */
	@Test
	public void mainViewTest() throws Exception {
		/***Given***/
		

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/main")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		String userId = (String) mav.getModel().get("mainUserId");
		
		/***Then***/
		assertEquals("main", viewName);
		assertEquals("brown", userId);
	}
	
	
	@Test
	public void mainViewAndExpectTest() throws Exception {
		/***Given***/
		
		
		/***When***/
		mockMvc.perform(get("/main"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("main"))
			   .andExpect(model().attribute("mainUserId", "brown"));
		
		/***Then***/
	}

}
