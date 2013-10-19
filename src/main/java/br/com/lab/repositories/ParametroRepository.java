package br.com.lab.repositories;

import java.util.List;

import br.com.lab.models.Parametro;

public interface ParametroRepository {
	/*
	 * Delete the methods you don't want to expose
	 */
	 
	void create(Parametro entity);
	
	Parametro update(Parametro entity);
	
	void destroy(Parametro entity);
	
	Parametro find(Long id);
	
	List<Parametro> findAll();

}
