package com.files.filesmanagement.service.atributo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import com.files.filesmanagement.model.Atributo;

public class CreateCSVOfAtributo {

	public ByteArrayInputStream createCSVOfAtributo(List<Atributo> atributos) {

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		CSVPrinter printer;

		try {
			printer = new CSVPrinter(new PrintWriter(out), CSVFormat.DEFAULT);
			
			for (Atributo attr : atributos) {
				List<String> data = Arrays.asList(String.valueOf(attr.getId()), attr.getName(),
						attr.getAtributoImgPath());

				printer.printRecord(data);
			}

			printer.flush();

		} catch (IOException e) {
			throw new RuntimeException("Error when creating CSV File of Atributo. " + e.getMessage());		
		}

		return new ByteArrayInputStream(out.toByteArray());

	}
}
