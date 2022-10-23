package com.files.filesmanagement.service.atributo;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.files.filesmanagement.model.Atributo;

public class ImportExcelOfAtributo {
	
	 public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	 static String[] HEADERs = {"Name", "Atributo_Img_path" };
	 static String SHEET = "Atributos";
	 
	 public List<Atributo> importExcelOfAtributo(MultipartFile mp){
		 	
		 if(!hasExcelFormat(mp))
			 throw new RuntimeException("Invalid format, expected a Excel file.");
		 
		 try {
			 
			 InputStream imp = mp.getInputStream();
			 Workbook wb = new XSSFWorkbook(imp);
			 Sheet sheet = wb.getSheet(SHEET);
			 Iterator<Row> rows = sheet.iterator();
			 
			 List<Atributo> atributos = iterateOverExcelFile(rows);
			 wb.close();
			 
			 return atributos;
			 
		 }catch(IOException e) {
			 throw new RuntimeException("Error when trying import Atriuto Excel file. " + e.getMessage());
		 }
	 }
	 
	 private boolean hasExcelFormat(MultipartFile file) {
		 if(!TYPE.equals(file.getContentType())) {
			 return false;
		 }
		 return true;
	 } 
	 
	 private List<Atributo> iterateOverExcelFile( Iterator<Row> rows){
		 int rowNumber = 0;
		 List<Atributo> atributos = new ArrayList<>();
		 
		 while(rows.hasNext()) {
			 Row currentRow = rows.next();
			 if(rowNumber == 0 || currentRow.getCell(0) == null) {
				 rowNumber++;
				 continue;
			 }
	
			 Iterator<Cell> cellsInRow = currentRow.iterator();
			 
			 Atributo attr = new Atributo();
			 
			 int cellIndex = 0;
			 
			 while(cellsInRow.hasNext()) {
				 Cell currentCell = cellsInRow.next();
				 
				 switch(cellIndex) {
				  case 0:
					  // attr.setId((long) currentCell.getNumericCellValue());
					 // attr.setPublished(currentCell.getBooleanCellValue());
				  	attr.setId((long) currentCell.getNumericCellValue());
				  	break;
				  case 1:
					  attr.setName(currentCell.getStringCellValue());
					  break;
				  case 2:
					  attr.setAtributoImgPath((currentCell.getStringCellValue()));
					  break;
				  default:
					  break;
				 }
				 
				 cellIndex++;
			 }
			
			 atributos.add(attr);		 
		 }	 
		 
		 return atributos;
	 }
}
