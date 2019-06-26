package kr.or.ddit.lprod.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.lprod.dao.ILprodDao;
import kr.or.ddit.paging.model.PageVo;

@Service
public class LprodService implements ILprodService{

	@Resource(name="lprodDao")
	private ILprodDao lprodDao;
	
	@Override
	public Map<String, Object> lprodPagingList(PageVo pageVo) {
		Map<String,Object> resultMap = new HashMap<String, Object>();
		resultMap.put("lprodList", lprodDao.lprodPagingList(pageVo));
		
		int lprodCnt = lprodDao.lprodCnt();
		
		int paginationSize = (int) Math.ceil((double)lprodCnt/pageVo.getPageSize());
		resultMap.put("paginationSize", paginationSize);
		return resultMap;
	}

}
