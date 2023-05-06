package com.codingdojo.cynthia.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.cynthia.modelos.Pregunta;

@Repository
public interface RepositorioPreguntas extends CrudRepository<Pregunta, Long> {
	
	List<Pregunta> findAll(); //SELECT * FROM preguntas
	
}
