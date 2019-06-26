package kr.or.ddit.prod.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.prod.model.ProdVo;
import kr.or.ddit.prod.service.IProdService;

@RequestMapping("/prod")
@Controller
public class ProdController {
	@Resource(name="prodService")
	private IProdService prodService;
	
	@RequestMapping("/pagingList")
	public String lprodList(PageVo pageVo,Model model) {
		
		Map<String, Object> resultMap = prodService.prodPagingList(pageVo);
		
		List<ProdVo> lprodList = (List<ProdVo>) resultMap.get("prodList");
		int paginationSize = (int) resultMap.get("paginationSize");
		model.addAttribute("prodList", lprodList);
		model.addAttribute("paginationSize", paginationSize);
		model.addAttribute("pageVo", pageVo);
		
		return "prod/prodPage";
	}
	
}
