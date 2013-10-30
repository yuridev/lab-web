package br.com.lab.repositories;

import br.com.lab.models.SequenciadorOrcamento;

public interface SequenciadorOrcamentoRepository {
	/*
	 * Delete the methods you don't want to expose
	 */
	 
    SequenciadorOrcamento update(SequenciadorOrcamento entity);
	
	SequenciadorOrcamento getNext();
	
}
