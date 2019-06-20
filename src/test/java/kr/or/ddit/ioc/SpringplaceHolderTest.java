package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ioc.placeholder.DbInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-ioc-placeholder.xml")
public class SpringplaceHolderTest {
	@Resource(name="dbInfo")
	private DbInfo dbInfo;
	
	@Test
	public void placeHolderTest() {
		/***Given***/
		

		/***When***/

		/***Then***/
		assertEquals("oracle.jdbc.driver.OracleDriver", dbInfo.getDriver());
		assertTrue(dbInfo.getUrl().contains("@localhost:1521:xe"));
		assertEquals("jdbc:oracle:thin:@localhost:1521:xe", dbInfo.getUrl());
		assertEquals("PC03", dbInfo.getUsername());
		assertEquals("java", dbInfo.getPassword());
		
	}

}
