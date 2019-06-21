package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.typeConvert.model.FormattingVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-ioc-type-formatting.xml")
public class ApplicationIocTypeFormattingTest {
	
	private static final Logger logger = LoggerFactory.getLogger(ApplicationIocTypeFormattingTest.class);

	
	@Resource(name="formattingVo")
	private FormattingVo forVo;
	
	
	@Test
	public void FormattingVoTest() {
		/***Given***/
		

		/***When***/
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		
		SimpleDateFormat sdf2 = new SimpleDateFormat("MM-yyyy-dd");
		String reg_dt =sdf2.format(forVo.getReg_dt());
		String mod_dt = sdf2.format(forVo.getMod_dt());
		
		logger.debug("reg_dt : {}", reg_dt);
		logger.debug("mod_dt : {}", mod_dt);
		
		
		/***Then***/
		assertNotNull(forVo);
		assertEquals("2019-06-21", sdf1.format(forVo.getReg_dt()));
		assertEquals("06-21-2019", sdf.format(forVo.getMod_dt()));
		
		assertEquals("06-2019-21", reg_dt);
		assertEquals("06-2019-21", mod_dt);
		assertEquals(6000, forVo.getNumber());		// "6,000" => 6000
	}

}
