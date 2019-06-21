package kr.or.ddit.user.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

}
