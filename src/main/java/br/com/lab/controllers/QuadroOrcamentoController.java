package br.com.lab.controllers;

import java.util.List;

import br.com.lab.models.QuadroOrcamento;
import br.com.lab.repositories.QuadroOrcamentoRepository;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.view.Results;

@Resource
public class QuadroOrcamentoController {

	private final Result result;
	private final QuadroOrcamentoRepository repository;
	
	private final Validator validator;
	
	public QuadroOrcamentoController(Result result, QuadroOrcamentoRepository repository, 
	Validator validator) {
		this.result = result;
		this.repository = repository;
	
		this.validator = validator;
	}
	
	@Get("/quadroOrcamentos")
	public List<QuadroOrcamento> index() {
		return repository.findAll();
	}
	
	@Post("/quadroOrcamentos")
	public void create(QuadroOrcamento quadroOrcamento) {
		validator.validate(quadroOrcamento);
		validator.onErrorUsePageOf(this).newQuadroOrcamento();
		repository.create(quadroOrcamento);
		result.redirectTo(this).index();
	}
	
	@Get("/quadroOrcamentos/new")
	public QuadroOrcamento newQuadroOrcamento() {
		return new QuadroOrcamento();
	}
	
	@Put("/quadroOrcamentos")
	public void update(QuadroOrcamento quadroOrcamento) {
		validator.validate(quadroOrcamento);
		validator.onErrorUsePageOf(this).edit(quadroOrcamento);
		repository.update(quadroOrcamento);
		result.redirectTo(this).index();
	}
	
	@Get("/quadroOrcamentos/{quadroOrcamento.id}/edit")
	public QuadroOrcamento edit(QuadroOrcamento quadroOrcamento) {
		
		return repository.find(quadroOrcamento.getId());
	}

	@Get("/quadroOrcamentos/{quadroOrcamento.id}")
	public QuadroOrcamento show(QuadroOrcamento quadroOrcamento) {
		return repository.find(quadroOrcamento.getId());
	}

}