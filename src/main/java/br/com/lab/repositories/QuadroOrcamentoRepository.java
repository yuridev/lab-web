package br.com.lab.repositories;

import java.util.List;

import br.com.lab.models.Orcamento;
import br.com.lab.models.QuadroOrcamento;

public interface QuadroOrcamentoRepository {
	/*
	 * Delete the methods you don't want to expose
	 */
	 
	void create(QuadroOrcamento entity);
	
	QuadroOrcamento update(QuadroOrcamento entity);
	
	void destroy(QuadroOrcamento entity);
	
	QuadroOrcamento find(Long id);
	
	List<QuadroOrcamento> findAll();

}
