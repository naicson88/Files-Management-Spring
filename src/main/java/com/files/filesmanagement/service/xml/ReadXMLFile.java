package com.files.filesmanagement.service.xml;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class ReadXMLFile extends DefaultHandler{
	
	private StringBuilder currentValue = new StringBuilder();
	
	@Override
	public void startDocument() {
		System.out.println("Start Document");
	}
	
	@Override
	public void endDocument() {
		System.out.println("End Document");
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) {
		 // reset the tag value
		currentValue.setLength(0);
		
		System.out.printf("Start Element : %s%n", qName);
		
		if("staff".equalsIgnoreCase(qName)) {
		   // get tag's attribute by name
			String id = attributes.getValue("id");
			System.out.printf("Staff id : %s%n", id);
		}
		
		if("salary".equalsIgnoreCase(qName)) {
		   // get tag's attribute by index, 0 = first attribute
			String currency = attributes.getValue(0);
			System.out.printf("Currency : %s%n", currency);
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) {
		System.out.printf("End Element : %s%n", qName);
		
		if("name".equalsIgnoreCase(qName))
			System.out.printf("Name: %s%n", currentValue.toString());
		if("role".equalsIgnoreCase(qName))
			System.out.printf("Role: %s%n", currentValue.toString());
		if("salary".equalsIgnoreCase(qName))
			System.out.printf("Salary : %s%n", currentValue.toString());
		if("bio".equalsIgnoreCase(qName))
			System.out.printf("Bio : %s%n", currentValue.toString());
	}
	
	@Override
	public void characters(char ch[], int start, int length) {
	  // The characters() method can be called multiple times for a single text node.
      // Some values may missing if assign to a new string

      // avoid doing this
      // value = new String(ch, start, length);

      // better append it, works for single or multiple calls
		currentValue.append(ch, start, length);
	}

}
