package br.com.lab.repositories;

import java.util.List;

import br.com.lab.models.Orcamento;

public interface OrcamentoRepository {
	/*
	 * Delete the methods you don't want to expose
	 */
	 
	void create(Orcamento entity);
	
	Orcamento update(Orcamento entity);
	
	void destroy(Orcamento entity);
	
	Orcamento find(Long id);
	
	List<Orcamento> findAll();

	List<Orcamento> listarPaginado(int pgAtual, int qtdRegistros);
    
    int getQuantidadeRegistros();
}
