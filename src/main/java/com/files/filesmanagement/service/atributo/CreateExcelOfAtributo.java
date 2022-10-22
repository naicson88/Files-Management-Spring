package com.files.filesmanagement.service.atributo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.files.filesmanagement.model.Atributo;

public class CreateExcelOfAtributo {
	 public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	 static String[] HEADERs = { "Id", "Name", "Atributo_Img_path" };
	 static String SHEET = "Atributos";
	 
	
	public ByteArrayInputStream createExcelOfAtributo(List<Atributo> atributos) {
		
		try(Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();){
			Sheet sheet = workbook.createSheet(SHEET);
			//Header
			Row headerRow = sheet.createRow(0);
			
			for(int col = 0; col < HEADERs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(HEADERs[col]);
			}
			
			int rowIdx = 1;
			
			for(Atributo att : atributos) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(att.getId());
				row.createCell(1).setCellValue(att.getName());
				row.createCell(2).setCellValue(att.getAtributoImgPath());
			}
			
			workbook.write(out);
			
			return new ByteArrayInputStream(out.toByteArray());
				
		}catch(IOException  e) {
			throw new RuntimeException("Fail to import data to Excel on Atributo. " + e.getMessage());
		}
	}
}
