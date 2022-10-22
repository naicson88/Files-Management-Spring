package com.files.filesmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
