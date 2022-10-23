package com.files.filesmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.files.filesmanagement.model.Atributo;

public interface AtributoRepository extends JpaRepository<Atributo, Long> {
	
	@Modifying
	@Query(value = "insert into tab_atributo values(:id, :name,:path)", nativeQuery = true)
	public void saveNewAtributo(Long id, String name, String path);
}
