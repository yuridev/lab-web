package br.com.lab.repositories;

import java.util.List;

import br.com.lab.models.Cliente;

public interface ClienteRepository {
	/*
	 * Delete the methods you don't want to expose
	 */
	 
	void create(Cliente entity);
	
	Cliente update(Cliente entity);
	
	void destroy(Cliente entity);
	
	Cliente find(Long id);
	
	List<Cliente> findAll();

}
