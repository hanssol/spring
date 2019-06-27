package kr.or.ddit.view;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;

import kr.or.ddit.user.model.UserVo;

public class ExcelDownloadView implements View {
	
	private static final Logger logger = LoggerFactory.getLogger(ExcelDownloadView.class);

	
	@Override
	public String getContentType() {
		return "application/vnd.ms-excel";
	}

	@Override
	public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		
		String filename = (String) model.get("filename");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment;filename="+filename+".xlsx");
		
		List<String> header = (List<String>) model.get("header");	// data 헤더정보 (userId, name, alias...)
		List<UserVo> userList = (List<UserVo>) model.get("data");		// data 
			
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("data");
		
		Row row = null;
		int rowIdx = 0;
		int colIdx= 0;
		
		row = sheet.createRow(rowIdx++);
		
		// header 쓰기
		for(String head : header) {
			row.createCell(colIdx++).setCellValue(head);
		}
		// data 쓰기
		for(UserVo user : userList) {
			colIdx = 0;
			row = sheet.createRow(rowIdx++);
			row.createCell(colIdx++).setCellValue(user.getUserId());
			row.createCell(colIdx++).setCellValue(user.getName());
			row.createCell(colIdx++).setCellValue(user.getalias());
			row.createCell(colIdx++).setCellValue(user.getAddr1());
			row.createCell(colIdx++).setCellValue(user.getAddr2());
			row.createCell(colIdx++).setCellValue(user.getZipcd());
			if(user.getBirth() !=null) {
			row.createCell(colIdx++).setCellValue(date.format(user.getBirth()));
			}
		}
		
		ServletOutputStream sos = response.getOutputStream();
		workbook.write(sos);
		workbook.close();
		
		
		sos.close();
		
	}
	
}
