package com.files.filesmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.files.filesmanagement.service.xml.XMLFileServiceImpl;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/xml")
public class XMLFile {
	
	@Autowired
	XMLFileServiceImpl xmlService;
	
	@GetMapping("/read-file")
	public ResponseEntity<String> getExcelFile(){
		
		xmlService.readXMLFile();
		
		return  ResponseEntity.ok().body("File was read");
	}
}
