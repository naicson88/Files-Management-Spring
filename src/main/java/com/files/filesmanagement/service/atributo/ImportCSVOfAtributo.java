package com.files.filesmanagement.service.atributo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import com.files.filesmanagement.model.Atributo;

public class ImportCSVOfAtributo {
	
	 public static String TYPE = "text/csv";
	 static String[] HEADERs = { "Id", "Name", "Atributo_Img_path" };
	 
	 private boolean hasCSVFormat(MultipartFile file) {
		 if(!TYPE.equals(file.getContentType()))
			 return false;
		 
		 return true;
	 }
	 
	 public List<Atributo> importCSVOfAtributo(MultipartFile file){
		 if(!hasCSVFormat(file))
			 throw new RuntimeException("Invalid format, expected a CSV file");
		 
		 try {
			 InputStream imp = file.getInputStream();
			 BufferedReader fileReader = new BufferedReader(new InputStreamReader(imp, "UTF-8"));
			 CSVParser csvParser = new CSVParser(fileReader,
					 CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
			 
			 List<Atributo> atributos = iterateCSVFile(csvParser.getRecords());
			 
			 csvParser.close();
			 
			 return atributos;
			 
		 }catch(IOException e) {
			 throw new RuntimeException("Error when trying import CSV file of Atributo. " + e.getMessage());
		 }
	 }

	private List<Atributo> iterateCSVFile(List<CSVRecord> records) {
		
		List<Atributo> atributos = new ArrayList<>();
		
		for(CSVRecord csvRec : records) {
			Atributo att = new Atributo();
			att.setId(Long.parseLong(csvRec.get("Id")));
			att.setName(csvRec.get("Name"));
			att.setAtributoImgPath(csvRec.get("Path"));
			
			atributos.add(att);
		}
		
		return atributos;
	}

}
