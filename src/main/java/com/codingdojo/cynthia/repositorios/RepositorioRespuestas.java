package com.codingdojo.cynthia.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.cynthia.modelos.Respuesta;

@Repository
public interface RepositorioRespuestas extends CrudRepository<Respuesta, Long> {
	
	List<Respuesta> findAll();
	
}
