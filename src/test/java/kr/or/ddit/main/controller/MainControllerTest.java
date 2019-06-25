package kr.or.ddit.main.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static final Logger logger = LoggerFactory.getLogger(MainControllerTest.class);

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
		assertNotNull(mav.getModel().get("rangers"));
		assertNotNull(mav.getModel().get("userVo"));
	}
	
	
	@Test
	public void mainViewAndExpectTest() throws Exception {
		/***Given***/
		
		
		/***When***/
		mockMvc.perform(get("/main"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("main"))
			   .andExpect(model().attribute("mainUserId", "brown"))
			   .andExpect(model().attributeExists("rangers"))
			   .andExpect(model().attributeExists("userVo"));
		/***Then***/
	}
	
	/**
	 * Method : mainViewMavTest
	 * 작성자 : PC03
	 * 변경이력 :
	 * Method 설명 : ModelAndView 객체를 이용한 main페이지 요청 테스트
	 * @throws Exception 
	 */
	@Test
	public void mainViewMavTest() throws Exception {
		/***Given***/
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/mainMav")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();

		/***Then***/
		// viewName이 기대하는 문자열로 리턴 되는지
		assertEquals("main", mav.getViewName());
		
		// model객체에 controller에서 설정한 속성이 있는지
		assertEquals("brown", mav.getModel().get("mainUserId"));
		assertNotNull(mav.getModel().get("rangers"));
		
	}
	
	/**
	 * Method : pathvariableTest
	 * 작성자 : PC03
	 * 변경이력 :
	 * Method 설명 : @Pathvariable 테스트
	 * @throws Exception 
	 */
	@Test
	public void pathvariableTest() throws Exception {
	
		mockMvc.perform(get("/main/pathvariable/brown")) 
				.andExpect(status().is(200))
				.andExpect(view().name("main"));
	
	}
	
	/**
	 * Method : pathvariableTest
	 * 작성자 : PC03
	 * 변경이력 :
	 * Method 설명 : @Pathvariable 테스트
	 * @throws Exception 
	 */
	@Test
	public void pathvariableTest2() throws Exception {
		
		mockMvc.perform(get("/main/pathvariable/sally")) 
				.andExpect(status().is(200))
				.andExpect(view().name("main"));
		
	}
	
	/**
	 * Method : requestHeaderTest
	 * 작성자 : PC03
	 * 변경이력 :
	 * @throws Exception
	 * Method 설명 : @Requestheader test
	 */
	@Test
	public void requestHeaderTest() throws Exception {
		
		mockMvc.perform(get("/main/header").accept("text/html")) 
				.andExpect(status().is(200))
				.andExpect(view().name("main"));
		
	}

}
