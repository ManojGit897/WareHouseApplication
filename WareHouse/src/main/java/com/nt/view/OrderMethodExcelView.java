package com.nt.view;

import java.util.List;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.nt.model.OrderMethod;

public class OrderMethodExcelView extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		
response.addHeader("Content-Disposition", "attachment;filename=OM.xlsx");
		
		//1. read data from model memory (given by controller)
		@SuppressWarnings("unchecked")
		List<OrderMethod> list  = (List<OrderMethod>)model.get("list");
		
		//2. create one new sheet with one name
		Sheet sheet = workbook.createSheet("ORDERMETHODS");
		
		//3. add rows to sheet 
		addHeader(sheet);
		addBody(sheet,list);
		
		
	}
	
	private void addHeader(Sheet sheet) {
		//row#0
		Row row = sheet.createRow(0);
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("MODE");
		row.createCell(2).setCellValue("CODE");
		row.createCell(3).setCellValue("TYPE");
		row.createCell(4).setCellValue("ACCEPT");
		row.createCell(5).setCellValue("NOTE");
	}

	private void addBody(Sheet sheet, List<OrderMethod> list) {
		int rowNum = 1;
		for(OrderMethod om : list) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(om.getId());
			row.createCell(1).setCellValue(om.getOrderMode());
			row.createCell(2).setCellValue(om.getOrderCode());
			row.createCell(3).setCellValue(om.getOrderType());
			//Cell accepts only String, number, not List/Set...so, convert to String Format
			row.createCell(4).setCellValue(om.getOrderAcpt().toString());
			row.createCell(5).setCellValue(om.getOrderDesc());
		}
	}


	

}
