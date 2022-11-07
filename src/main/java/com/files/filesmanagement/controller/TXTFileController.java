package com.files.filesmanagement.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.files.filesmanagement.service.txt.TXTFileService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/txt")
public class TXTFileController {
	
	@Autowired
	TXTFileService service;
	
	@GetMapping("/read-file")
	public ResponseEntity<String> readTXTFile() throws IOException {
		return new ResponseEntity<String>(service.readTXTFile(), HttpStatus.OK);
	}
}
