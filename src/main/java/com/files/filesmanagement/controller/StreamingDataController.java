package com.files.filesmanagement.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@RestController
@RequestMapping ("/api/streaming-data")
public class StreamingDataController {
	
//	Streaming Data with Spring Boot
//	Streaming data is a radical new approach to sending data to web browsers which provides for dramatically faster page load times.

	private final Logger logger = LoggerFactory.getLogger(StreamingDataController.class);
	
	@GetMapping(value = "/download", produces = MediaType.APPLICATION_JSON_VALUE)
//	In this API endpoint, we are reading multiple files from a directory and creating a zip file. We are executing 
//	this process within StreamingResponseBody. It writes data directly to an OutputStream before passing that written information 
//	back to the client using a ResponseEntity. This means that download process will start immediately on the client, while the server
//	is processing and writing data in chunks.
	
//	When using StreamingResponseBody, it is highly recommended to configure TaskExecutor used in Spring MVC 
//	for executing asynchronous requests. TaskExecutor is an interface that abstracts the execution of a Runnable.
	public ResponseEntity<StreamingResponseBody> download(final HttpServletResponse response){
		
		response.setContentType("application/zip");
		response.setHeader( "Content-Disposition","attachment;filename=sample.zip");
		
		StreamingResponseBody stream = out -> {
			final String home = System.getProperty("user.home");
			final File directory = new File(home + File.separator + "Documents" + File.separator + "sample");
			
			final ZipOutputStream zipOut = new ZipOutputStream(response.getOutputStream());
			
			if(directory.exists() && directory.isDirectory()) {
				try {
					for(final File file : directory.listFiles()) {
						final InputStream inputStream = new FileInputStream(file);
						final ZipEntry zipEntry = new ZipEntry(file.getName());
						
						zipOut.putNextEntry(zipEntry);
						byte[] bytes = new byte[1024];
						int length;
						
						while((length = inputStream.read(bytes)) >= 0) {
							zipOut.write(bytes, 0, length);
						}
						
						inputStream.close();
					}
					
					zipOut.close();
					
				}catch (final IOException e) {
					logger.error("Exception while reading and streaming data {} ", e);
				}
			}
		};
		
		logger.info("Streaming response {} ", stream);
		return new ResponseEntity<>(stream, HttpStatus.OK);
	}
}
