package com.files.filesmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import com.files.filesmanagement.model.Atributo;
import com.files.filesmanagement.service.atributo.AtributoServiceImpl;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/atributo")
public class AtributoController {
	
	@Autowired
	AtributoServiceImpl atributoService;
	
	@GetMapping("/download-excel")
	public ResponseEntity<Resource> getExcelFile(){
		InputStreamResource file = new InputStreamResource(atributoService.createExcelOfAtributo());
		String fileName = "atributos.xlsx";
		
		return ResponseEntity
				.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+fileName)
				.contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
				.body(file);
	}
	
	@GetMapping("/download-csv")
	public ResponseEntity<Resource> getCSVFile(){
		InputStreamResource file = new InputStreamResource(atributoService.createCSVOfAtributo());
		String fileName = "atributos.csv";
		
		return ResponseEntity
				.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
		        .contentType(MediaType.parseMediaType("application/csv"))
		        .body(file);
		
	}
	
	@PostMapping("/upload-excel-file")
	public ResponseEntity<List<Atributo>> importExcelOfAtributo(@RequestParam("file") MultipartFile file){
		List<Atributo> atributos = atributoService.importExcelOfAtributo(file);
		
		return new ResponseEntity<List<Atributo>>(atributos, HttpStatus.OK);
	}
	
	 @ExceptionHandler(MaxUploadSizeExceededException.class)
	  public ResponseEntity<String> handleMaxSizeException(MaxUploadSizeExceededException exc) {
	    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("File too large!");
	  }
}
