package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ioc.collection.IocCollection;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-ioc-collection.xml")
public class SpringIocCollectionTest {
	
	
	@Resource(name="collectionBean")
	private IocCollection ioct;
	
	
	@Test
	public void springCollectionTest() {
		/***Given***/
		

		/***When***/

		/***Then***/
		assertEquals("brown", ioct.getList().get(0));
		assertEquals("sally", ioct.getList().get(1));
		assertEquals("cony", ioct.getList().get(2));
		assertEquals("brown", ioct.getMap().get("name"));
		assertEquals("2019-08-08", ioct.getMap().get("birth"));
		assertEquals("brown", ioct.getProperties().get("userId"));
		assertEquals("브라운", ioct.getProperties().get("name"));
		assertEquals(3, ioct.getSet().size());
		assertEquals(true, ioct.getSet().contains("brown"));
		assertEquals(true, ioct.getSet().contains("sally"));
		assertEquals(true, ioct.getSet().contains("cony"));
		assertTrue(ioct.getSet().contains("brown"));
		assertTrue(ioct.getSet().contains("sally"));
		assertTrue(ioct.getSet().contains("cony"));
		
		
		
	}

}
