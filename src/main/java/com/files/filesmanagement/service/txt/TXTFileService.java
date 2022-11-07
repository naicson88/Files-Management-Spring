package com.files.filesmanagement.service.txt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.stereotype.Service;

@Service
public class TXTFileService {
	
	private static final String FILEPATH = "src/main/resources/teste.txt";
	
	public String readTXTFile() throws IOException {
		File file = new File(FILEPATH);
		String line = null;
		StringBuilder lines = new StringBuilder();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
				
			while ((line = br.readLine()) != null){
				System.out.println(line);
				lines.append(line + "\n");
			}
			
			br.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return lines.toString();
	}
}
