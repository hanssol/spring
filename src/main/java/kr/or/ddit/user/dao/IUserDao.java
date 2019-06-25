package kr.or.ddit.user.dao;

import java.util.List;

import kr.or.ddit.user.model.UserVo;

public interface IUserDao {
	
	/**
	 * Method : userList
	 * 작성자 : PC03
	 * 변경이력 :
	 * @return
	 * Method 설명 : 전체 사용자 조회
	 */
	List<UserVo> userList();

	int insertUser(UserVo userVo);

	int deleteUser(String userId);
	
	
	/**
	 * Method : getUser
	 * 작성자 : PC03
	 * 변경이력 :
	 * @param userId
	 * @return
	 * Method 설명 : 사용자 정보 조회
	 */
	UserVo getUser(String userId);
}
