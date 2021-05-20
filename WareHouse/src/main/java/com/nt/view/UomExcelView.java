package com.nt.view;

import java.util.List;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.nt.model.Uom;

public class UomExcelView extends AbstractXlsxView  {

	@Override
	protected void buildExcelDocument(Map<String, 
			Object> model, 
			Workbook workbook, 
			HttpServletRequest request,
			HttpServletResponse response)
					throws Exception {
		
response.addHeader("Content-Disposition","attachment;filename=UOMS.xlsx");
		
		// read data from model memory (given by controller )
		@SuppressWarnings("unchecked")
		List<Uom> list=(List<Uom>)model.get("list");
	
		//2.create one new sheet with one name
		Sheet sheet=workbook.createSheet("UOMS");
	//3. add rows to sheet
		addHeader(sheet);
		addBody(sheet,list);
		
	}

	private void addHeader(Sheet sheet) {
		//row 0
		Row row=sheet.createRow(0);
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("UomType");
		row.createCell(2).setCellValue("UomModel");
		row.createCell(3).setCellValue("UomDesc");
		
	}
	
	
	private void addBody(Sheet sheet, List<Uom> list) {
		
		int rowNum=1;
		for(Uom uom:list) {
			Row row=sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(uom.getId());
			row.createCell(1).setCellValue(uom.getUomType());
			row.createCell(2).setCellValue(uom.getUomModel());
			row.createCell(3).setCellValue(uom.getUomDesc());
			
			
		}
	}
		

	


}
