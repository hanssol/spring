package kr.or.ddit.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.user.dao.IUserDao;
import kr.or.ddit.user.model.UserVo;

@Service
public class UserService implements IUserService{
	
	@Resource(name="userDao")
	private IUserDao userDao;
	
	/**
	 * Method : userList
	 * 작성자 : PC03
	 * 변경이력 :
	 * @return
	 * Method 설명 : 전체사용자리스트조회
	 */
	@Override
	public List<UserVo> userList() {
		return userDao.userList();
	}

	@Override
	public int insertUser(UserVo userVo) {
		return userDao.insertUser(userVo);
	}

	@Override
	public int deleteUser(String userId) {
		return userDao.deleteUser(userId);
	}
	
	/**
	 * Method : getUser
	 * 작성자 : PC03
	 * 변경이력 :
	 * @param userId
	 * @return
	 * Method 설명 : 사용자 정보 조회
	 */
	@Override
	public UserVo getUser(String userId) {
		return userDao.getUser(userId);
	}

	@Override
	public int updateUser(UserVo userVo) {
		return userDao.updateUser(userVo);
	}

	@Override
	public int usersCnt() {
		return userDao.usersCnt();
	}

	@Override
	public Map<String, Object> userPagingList(PageVo pageVo) {
		
		// 1. List<UserVo>, userCnt를 필드로 하는 vo
		// 2. List<Object> resultList = new ArrayList<Object>();
		//		resultList.add(userList);
		//		resultList.add(usersCnt);
		// 3. Map<String, Object> resultMap = new Hashmap<String, Object>();
		//		resultMap.put("userList","userList);
		//		resultMap.put(usersCnt,usersCnt);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("userList", userDao.userPagingList(pageVo));
		
		// usersCnt --> paginationSize 변경
		// resultMap.put("usersCnt", dao.usersCnt());
		int usersCnt = userDao.usersCnt();
		// pageSize --> pageVo.getPageSize();
		
		int paginationSize = (int) Math.ceil((double)usersCnt/pageVo.getPageSize());
		resultMap.put("paginationSize", paginationSize);
		
		return resultMap;
	}

	@Override
	public List<UserVo> userListForPassEncrypt(SqlSession sqlSession2) {
		return userDao.userListForPassEncrypt(sqlSession2);
	}

	@Override
	public int updateUserEncryptPass(SqlSession sqlSession2, UserVo userVo) {
		return userDao.updateUserEncryptPass(sqlSession2, userVo);
	}

}
