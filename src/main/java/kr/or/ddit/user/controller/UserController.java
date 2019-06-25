package kr.or.ddit.user.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.IUserService;

@Controller
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	
	@Resource(name="userService")
	private IUserService userService;
	
	/**
	 * Method : userList
	 * 작성자 : PC03
	 * 변경이력 :
	 * @param model
	 * @return
	 * Method 설명 : 사용자 전체 리스트조회
	 */
	@RequestMapping("/userList")
	public String userList(Model model) {
		
		model.addAttribute("userList",userService.userList());
		return "user/userList";
	}
	
	/**
	 * Method : userPagingList
	 * 작성자 : PC03
	 * 변경이력 :
	 * @return
	 * Method 설명 : 사용자 페이징 리스트
	 */
//	@RequestMapping("/userPageList")
//	public String userPagingList(@RequestParam(name="page",defaultValue =  "1")int page, 
//								@RequestParam(name="pageSize",defaultValue = "10")int pageSize,
//								Model model) {
//	PageVo pageVo = new PageVo(page,pageSize);
	@RequestMapping("/userPagingList")
	public String userPagingList(PageVo pageVo,	Model model) {
	
		
		logger.debug("page : {}", pageVo);
		
		
		Map<String, Object> resultMap = userService.userPagingList(pageVo);
		
		List<UserVo> userList = (List<UserVo>) resultMap.get("userList");
		int paginationSize = (int) resultMap.get("paginationSize");
		model.addAttribute("userList", userList);
		model.addAttribute("paginationSize", paginationSize);
		model.addAttribute("pageVo", pageVo);
		
		
		return "user/userPageList";
	}
	
}
