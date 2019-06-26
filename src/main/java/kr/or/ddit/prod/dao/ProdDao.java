package kr.or.ddit.prod.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.paging.model.PageVo;

@Repository
public class ProdDao implements IProdDao{
	@Resource(name="sqlSession")
	private SqlSessionTemplate sqlSession;

	@Override
	public List<LprodVo> prodPagingList(PageVo pageVo) {
		return sqlSession.selectList("prod.prodPagingList",pageVo);
	}

	@Override
	public int prodCnt() {
		return sqlSession.selectOne("prod.prodCnt");
	}
	
	
	
	
}
