package kr.or.ddit.lprod.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.lprod.service.ILprodService;
import kr.or.ddit.paging.model.PageVo;

@RequestMapping("/lprod")
@Controller
public class LprodController {
	@Resource(name="lprodService")
	private ILprodService lprodService;
	
	@RequestMapping("/pagingList")
	public String lprodList(PageVo pageVo,Model model) {
		
		Map<String, Object> resultMap = lprodService.lprodPagingList(pageVo);
		
		List<LprodVo> lprodList = (List<LprodVo>) resultMap.get("lprodList");
		int paginationSize = (int) resultMap.get("paginationSize");
		model.addAttribute("lprodList", lprodList);
		model.addAttribute("paginationSize", paginationSize);
		model.addAttribute("pageVo", pageVo);
		
		return "lprod/lprodPage";
	}
	
}
