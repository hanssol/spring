package kr.or.ddit.user.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.testenv.ControllerTestEnv;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.IUserService;

public class UserControllerTest extends ControllerTestEnv{

	private static final Logger logger = LoggerFactory.getLogger(UserControllerTest.class);

	
	@Resource(name="userService")
	private IUserService userService;
	@Test
	public void userListTest() throws Exception {
		/***Given***/
		

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/list")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		
		/***Then***/
		assertEquals("user/userList", mav.getViewName());
		assertEquals(105, ((List<UserVo>)mav.getModelMap().get("userList")).size());
		
	}
	
	/**
	 * Method : userPagingListTest
	 * 작성자 : PC03
	 * 변경이력 :
	 * Method 설명 : 사용자 페이지 리스트[page,pageSize 파라미터 없이 호출]
	 * @throws Exception 
	 */
	@Test
	public void userPagingListTest() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/pagingList").param("page", "2").param("pageSize", "10")).andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		List<UserVo> userList = (List<UserVo>) mav.getModelMap().get("userList");
		int paginationSize =  (int) mav.getModelMap().get("paginationSize");
		PageVo pageVo = (PageVo) mav.getModelMap().get("pageVo");
		
		/***Then***/
		assertEquals("user/userPageList", viewName);
		assertEquals(10, userList.size());
		assertEquals(11, paginationSize);
		assertEquals(2, pageVo.getPage());
		assertEquals(10, pageVo.getPageSize());
		
	}
	/**
	 * Method : userPagingListTest
	 * 작성자 : PC03
	 * 변경이력 :
	 * Method 설명 : 사용자 페이지 리스트[page,pageSize 파라미터 없이 호출]
	 * @throws Exception 
	 */
	@Test
	public void userPagingListWithoutParameterTest() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/pagingList")).andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		List<UserVo> userList = (List<UserVo>) mav.getModelMap().get("userList");
		int paginationSize =  (int) mav.getModelMap().get("paginationSize");
		PageVo pageVo = (PageVo) mav.getModelMap().get("pageVo");
		
		/***Then***/
		assertEquals("user/userPageList", viewName);
		assertEquals(10, userList.size());
		assertEquals(11, paginationSize);
		
		// PageVo equals,hashCode메소드를 구현해서 객체간 값 비교
		assertEquals(new PageVo(1,10),pageVo);
		
//		assertEquals(2, pageVo.getPage());
//		assertEquals(10, pageVo.getPageSize());
		
	}
	
	@Test
	public void userTest() throws Exception {
		/***Given***/

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/user").param("userId", "brown")).andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		UserVo userVo = (UserVo) mav.getModelMap().get("userInfo");
		/***Then***/
		assertEquals("user/user", viewName);
		assertEquals("브라운", userVo.getName());
		
	}
	
	/**
	 * Method : userFormTest
	 * 작성자 : PC03
	 * 변경이력 :
	 * Method 설명 : 사용자 입력 화면요청
	 * @throws Exception 
	 */
	@Test
	public void userFormTest() throws Exception {
		mockMvc.perform(get("/user/form")).andExpect(view().name("user/userForm"));
	}
	
	/**
	 * Method : userFormPostSuccessTest
	 * 작성자 : PC03
	 * 변경이력 :
	 * Method 설명 : 사용자 등록 테스트(success 시나리오)
	 * @throws Exception 
	 */
	@Test
	public void userFormPostSuccessTest() throws Exception {
		/***Given***/
		File f = new File("src/test/resources/kr/or/ddit/testenv/sally.png");
		MockMultipartFile file = new MockMultipartFile("profile",f.getName() ,"",new FileInputStream(f));
		/***When***/
		mockMvc.perform(fileUpload("/user/form").file(file)
												.param("userId", "AYOO1")
												.param("name", "AYOO")
												.param("alias", "AYOO")
												.param("addr1", "서울 강남구 밤고개로 99")
												.param("addr2", "AYOO")
												.param("zipcd", "06369")
												.param("birth", "2019-06-06")
												.param("pass", "ayoo1234"))
												.andExpect(view().name("redirect:/user/pagingList"));
		
		/***Then***/
		
		
	}
	
	/**
	 * Method : userFormPostFailTest
	 * 작성자 : PC03
	 * 변경이력 :
	 * @throws Exception
	 * Method 설명 : 사용자 등록 테스트 (Fail 시나리오)
	 */
	@Test
	public void userFormPostFailTest() throws Exception {
		/***Given***/
		File f = new File("src/test/resources/kr/or/ddit/testenv/sally.png");
		MockMultipartFile file = new MockMultipartFile("profile",f.getName() ,"",new FileInputStream(f));
		/***When***/
		mockMvc.perform(fileUpload("/user/form").file(file)
				.param("userId", "AYOO")
				.param("name", "AYOO")
				.param("alias", "AYOO")
				.param("addr1", "서울 강남구 밤고개로 99")
				.param("addr2", "AYOO")
				.param("zipcd", "06369")
				.param("birth", "2019-06-06")
				.param("pass", "ayoo1234")).andExpect(view().name("redirect:/user/pagingList"));
		
		/***Then***/
	}
	
	/**
	 * Method : profileTest
	 * 작성자 : PC03
	 * 변경이력 :
	 * Method 설명 : 사용자 사진 응답 테스트
	 * @throws Exception 
	 */
	@Test
	public void profileTest() throws Exception {
		mockMvc.perform(get("/profile").param("userId", "brown"))
						.andExpect(status().isOk());
//						.andExpect(status().is(200));
	}
	
	/**
	 * Method : userModifyGetTest
	 * 작성자 : PC03
	 * 변경이력 :
	 * Method 설명 : 사용자 수정화면 요청 테스트
	 * @throws Exception 
	 */
	@Test
	public void userModifyGetTest() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/modify").param("userId", "brown")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		UserVo userVo = (UserVo) mav.getModelMap().get("userInfo");
		
		/***Then***/
		assertEquals("user/userModify", viewName);
		assertEquals("브라운", userVo.getName());
		
	}
	
	@Test
	public void userModifyPostTest() throws Exception {
		/***Given***/
		File f = new File("src/test/resources/kr/or/ddit/testenv/sally.png");
		MockMultipartFile file = new MockMultipartFile("profile",f.getName() ,"",new FileInputStream(f));
		/***When***/
		mockMvc.perform(fileUpload("/user/modify").file(file)
				.param("userId", "brown")
				.param("name", "AYOO")
				.param("alias", "AYOO")
				.param("addr1", "서울 강남구 밤고개로 99")
				.param("addr2", "AYOO")
				.param("zipcd", "06369")
				.param("birth", "2019-06-06")
				.param("pass", "ayoo1234")).andExpect(view().name("redirect:/user/user?userId=brown"));
		
		/***Then***/
	}
	
}
