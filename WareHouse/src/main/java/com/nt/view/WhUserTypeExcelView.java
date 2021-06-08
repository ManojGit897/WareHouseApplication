package com.nt.view;

import java.util.List;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.nt.model.OrderMethod;
import com.nt.model.WhUserType;

public class WhUserTypeExcelView extends AbstractXlsxView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, 
			Workbook workbook, 
			HttpServletRequest request,
			HttpServletResponse response)
					throws Exception {
		
response.addHeader("Content-Disposition", "attachment;filename=WhUserType.xlsx");
		
		//1. read data from model memory (given by controller)
		@SuppressWarnings("unchecked")
		List<WhUserType> list  = (List<WhUserType>)model.get("list");
		
		//2. create one new sheet with one name
		Sheet sheet = workbook.createSheet("WH USER TYPE");
		
		//3. add rows to sheet 
		addHeader(sheet);
		addBody(sheet,list);
		
		
	}
	
	private void addHeader(Sheet sheet) {
		//row#0
		Row row = sheet.createRow(0);
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("USER TYPE");
		row.createCell(2).setCellValue("CODE");
		row.createCell(3).setCellValue("USER FOR");
		row.createCell(4).setCellValue("EMAIL");
		row.createCell(5).setCellValue("CONTACT NUMBER");
		row.createCell(6).setCellValue("ID TYPE");
		row.createCell(7).setCellValue("IF OTHER ID");
		row.createCell(8).setCellValue("ID NUMBER");
	}

	private void addBody(Sheet sheet, List<WhUserType> list) {
		int rowNum = 1;
		for(WhUserType whut : list) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(whut.getId());
			row.createCell(1).setCellValue(whut.getUserType());
			row.createCell(2).setCellValue(whut.getUserCode());
			row.createCell(3).setCellValue(whut.getUserFor());
			
			row.createCell(4).setCellValue(whut.getUserEmail());
			row.createCell(5).setCellValue(whut.getUserContact());
			row.createCell(6).setCellValue(whut.getUserIdType());
			row.createCell(7).setCellValue(whut.getIfOther());
			row.createCell(8).setCellValue(whut.getUserIdNum());
			
			
		}
	}



	}

	

