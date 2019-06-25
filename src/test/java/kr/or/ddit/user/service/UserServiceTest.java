package kr.or.ddit.user.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.testenv.LogicTestEnv;
import kr.or.ddit.user.model.UserVo;

public class UserServiceTest extends LogicTestEnv{
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

	
	@Resource(name="userService")
	private IUserService userService;
	
	/**
	 * Method : userListTest
	 * 작성자 : PC03
	 * 변경이력 :
	 * Method 설명 : 사용자 전체 리스트 조회 테스트
	 */
	@Test
	public void userListTest() {
	/***Given***/
		

		/***When***/
		List<UserVo> userList = userService.userList();
		
		/***Then***/
		assertNotNull(userList);
		assertTrue(userList.size()>100);
	}
	
	/**
	 * 
	 * Method : insertUserTest
	 * 작성자 : PC03
	 * 변경이력 :
	 * Method 설명 : 사용자 등록, 삭제 테스트
	 */
	@Test
	public void insertUserTest(){
		/***Given***/
		// 사용자정보를 담고 있는 vo객체 준비
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
		UserVo userVo = null;
		
		try {
			userVo = new UserVo("대더덕인","asas","중앙로","userTest1234","대전광역시 중구 중앙로76","영민빌딩 2층 204호","34940",sdf.parse("2019-05-31"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		/***When***/
		// userDao.insertUser()
		int insertCnt = userService.insertUser(userVo);
		
		/***Then***/
		// insertCnt(1)
		logger.debug("insertUser : {}", insertCnt);
		assertEquals(1, insertCnt);
		
				
		// data 삭제
//		 userDao.deleteUser(usreId);
		int deleteCnt = userService.deleteUser(userVo.getUserId());
		
		logger.debug("deleteCnt : {}", deleteCnt);
		assertEquals(1, deleteCnt);
	}
	
	@Test
	public void getUserTest() {
		/***Given***/
		String userId="brown";

		/***When***/
		UserVo userVo = userService.getUser(userId);
		/***Then***/
		assertEquals("브라운", userVo.getName());
	}

	/**
	 * 
	 * Method : userPagingListTest
	 * 작성자 : PC03
	 * 변경이력 :
	 * Method 설명 : 사용자 페이징 리스트 조회 테스트
	 */
	@Test
	public void userPagingListTest(){
		/***Given***/
		PageVo pageVo = new PageVo(1,10);
		

		/***When***/
		Map<String, Object> resultMap = userService.userPagingList(pageVo);
		List<UserVo> userList = (List<UserVo>) resultMap.get("userList");
		int pagination = (Integer)resultMap.get("paginationSize");
		
		/***Then***/
		// pagingList assert
		assertNotNull(userList);
		assertEquals(10, userList.size());
		
		// usersCnt assert
		assertEquals(11, pagination);
	}
	
	/**
	 * 
	 * Method : usersCntTest
	 * 작성자 : PC03
	 * 변경이력 :
	 * Method 설명 : 사용자 전체수 조회 테스트
	 */
	@Test
	public void usersCntTest(){
		/***Given***/
		

		/***When***/
		int usersCnt = userService.usersCnt();
		
		/***Then***/
		assertEquals(105, usersCnt);
	}
	
	/**
	 * Method : updateUser
	 * 작성자 : PC03
	 * 변경이력 :
	 * Method 설명 : 사용자 정보 수정
	 */
	@Test
	public void updateUser(){
		/***Given***/
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
		UserVo userVo = null;
		
		try {
			userVo = new UserVo("더더덕인","user100","중앙로","userTest1234","대전광역시 중구 중앙로76","영민빌딩 2층 204호","34940",sdf.parse("2019-05-31"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		/***When***/
		int updateUser = userService.updateUser(userVo);

		/***Then***/
		assertEquals(1, updateUser);
	}

}
