package com.files.filesmanagement.service.atributo;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
	
	public ByteArrayInputStream createCSVOfAtributo() {
		CreateCSVOfAtributo csv = new CreateCSVOfAtributo();
		List<Atributo> atributos = atributoRepository.findAll();
		
		ByteArrayInputStream in = csv.createCSVOfAtributo(atributos);
		
		return in;
	}
	
	@Transactional(rollbackFor = {Exception.class, RuntimeException.class})
	public List<Atributo> importExcelOfAtributo(MultipartFile mp){
		ImportExcelOfAtributo imp = new ImportExcelOfAtributo();
		
		List<Atributo> atributos = new ArrayList<>();
		
		for(Atributo att : imp.importExcelOfAtributo(mp)) {
			atributoRepository.saveNewAtributo(att.getId(), att.getName(), att.getAtributoImgPath());
			atributos.add(att);
		}
	
		return atributos;
		
	}
}
