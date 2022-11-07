package com.files.filesmanagement.service.xml;

import org.springframework.stereotype.Service;

@Service
public class XMLFileServiceImpl {
	
	public void readXMLFile() {
		ReadXMLSaxParser read = new ReadXMLSaxParser();
		
		read.readXMLParser();	
	}
}
