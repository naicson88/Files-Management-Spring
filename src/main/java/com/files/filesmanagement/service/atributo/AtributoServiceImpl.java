package com.files.filesmanagement.service.atributo;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.files.filesmanagement.model.Atributo;
import com.files.filesmanagement.repository.AtributoRepository;

@Service
public class AtributoServiceImpl {
	
	@Autowired
	AtributoRepository atributoRepository;
	
	public ByteArrayInputStream createExcelOfAtributo() {
		 CreateExcelOfAtributo create = new CreateExcelOfAtributo();
		 List<Atributo> atributos = atributoRepository.findAll();
		 
		 ByteArrayInputStream in = create.createExcelOfAtributo(atributos);
		 return in;
	}
}
