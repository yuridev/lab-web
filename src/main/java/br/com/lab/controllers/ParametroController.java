package br.com.lab.controllers;

import java.util.List;

import br.com.lab.models.Parametro;
import br.com.lab.repositories.ParametroRepository;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;

@Resource
public class ParametroController {

	private final Result result;
	private final ParametroRepository repository;
	
	private final Validator validator;
	
	public ParametroController(Result result, ParametroRepository repository, 
	Validator validator) {
		this.result = result;
		this.repository = repository;
	
		this.validator = validator;
	}
	
	@Get("/parametros")
	public List<Parametro> index() {
		return repository.findAll();
	}
	
	@Post("/parametros")
	public void create(Parametro parametro) {
		validator.validate(parametro);
		validator.onErrorUsePageOf(this).newParametro();
		repository.create(parametro);
		result.redirectTo(this).index();
	}
	
	@Get("/parametros/new")
	public Parametro newParametro() {
		return new Parametro();
	}
	
	@Put("/parametros")
	public void update(Parametro parametro) {
		validator.validate(parametro);
		validator.onErrorUsePageOf(this).edit(parametro);
		repository.update(parametro);
		result.redirectTo(this).index();
	}
	
	@Get("/parametros/{parametro.id}/edit")
	public Parametro edit(Parametro parametro) {
		
		return repository.find(parametro.getId());
	}

	@Get("/parametros/{parametro.id}")
	public Parametro show(Parametro parametro) {
		return repository.find(parametro.getId());
	}

	@Get("/parametros/delete/{parametro.id}")
	public void destroy(Parametro parametro) {
		repository.destroy(repository.find(parametro.getId()));
		result.redirectTo(this).index();  
	}
}