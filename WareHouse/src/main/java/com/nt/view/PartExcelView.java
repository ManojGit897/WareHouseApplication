package com.nt.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.nt.model.Part;
import com.nt.model.ShipmentType;

public class PartExcelView extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
response.addHeader("Content-Disposition","attachment;filename=PARTS.xlsx");
		
		// read data from model memory (given by controller )
		@SuppressWarnings("unchecked")
		List<Part> list=(List<Part>)model.get("list");
	
		//2.create one new sheet with one name
		Sheet sheet=workbook.createSheet("PARTS");
	//3. add rows to sheet
		addHeader(sheet);
		addBody(sheet,list);
		
	}

	private void addHeader(Sheet sheet) {
		//row 0
		Row row=sheet.createRow(0);
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("CODE");
		row.createCell(2).setCellValue("DIMENSIONS");
		row.createCell(3).setCellValue("COST");
		row.createCell(4).setCellValue("CURRENCY");
		row.createCell(5).setCellValue("UOM");
		row.createCell(6).setCellValue("ORDER METHOD CODE");
		row.createCell(7).setCellValue("NOTE");

	}
	
	
	private void addBody(Sheet sheet, List<Part> list) {

		int rowNum=1;
		for(Part part:list) {
			Row row=sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(part.getId());
			row.createCell(1).setCellValue(part.getPartCode());
			row.createCell(2).setCellValue(part.getPartWid());
			row.createCell(2).setCellValue(part.getPartLen());
			row.createCell(2).setCellValue(part.getPartHt());
			row.createCell(3).setCellValue(part.getPartBaseCost());
			row.createCell(4).setCellValue(part.getPartCurrency());
			row.createCell(5).setCellValue(part.getUom().getUomModel());
			row.createCell(6).setCellValue(part.getOm().getOrderCode());
			row.createCell(7).setCellValue(part.getPartDesc());
			
		}
	}

		
	}
