package kr.or.ddit.batch.service;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.batch.dao.IBatchDao;
import kr.or.ddit.testenv.LogicTestEnv;

public class BatchServiceTest extends LogicTestEnv{

	@Resource(name="batchService")
	private IBatchService batchService;
	
	/**
	 * Method : createDailyTest
	 * 작성자 : PC03
	 * 변경이력 :
	 * Method 설명 : 일시적 일괄생성
	 */
	@Test
	public void createDailyTest() {
		/***Given***/
		String ym = "201907";

		/***When***/
		int createCnt = batchService.createDaily(ym);

		/***Then***/
		assertEquals(69, createCnt);
	}
	

}
