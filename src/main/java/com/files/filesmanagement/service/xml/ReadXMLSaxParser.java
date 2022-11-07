package com.files.filesmanagement.service.xml;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class ReadXMLSaxParser {
	
	private static final String FILENAME = "src/main/resources/staff.xml";
	
	public void readXMLParser() {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		ReadXMLFile read = new ReadXMLFile();
		
		try {
			// XXE attack, see https://rules.sonarsource.com/java/RSPEC-2755
			SAXParser saxParser = factory.newSAXParser();
			
			saxParser.parse(FILENAME, read);
			
		}catch(ParserConfigurationException | SAXException | IOException e) {
			throw new RuntimeException("Error when parse XML: " + e.getMessage());
		}
	}
}
