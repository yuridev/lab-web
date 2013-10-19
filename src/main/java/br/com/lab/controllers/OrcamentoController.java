package br.com.lab.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.caelum.vraptor.validator.Validations;
import br.com.caelum.vraptor.view.Results;
import br.com.lab.helpers.PaginadorHelper;
import br.com.lab.models.Orcamento;
import br.com.lab.models.QuadroOrcamento;
import br.com.lab.repositories.ClienteRepository;
import br.com.lab.repositories.OrcamentoRepository;
import br.com.lab.repositories.ParametroRepository;
import br.com.lab.repositories.QuadroOrcamentoRepository;

@Resource
public class OrcamentoController {

	private final Result result;
	private final OrcamentoRepository repository;
	
	private final Validator validator;
    private final QuadroOrcamentoRepository quadroOrcamentoRepository;
    private final ClienteRepository clienteRepository;
    private final ParametroRepository parametroRepository;
    private final HttpServletRequest request;
	
	public OrcamentoController(HttpServletRequest request, Result result, OrcamentoRepository repository, 
	Validator validator, QuadroOrcamentoRepository quadroOrcamentoRepository, ClienteRepository clienteRepository, ParametroRepository parametroRepository) {
		this.request = request;
        this.result = result;
		this.repository = repository;
	
		this.validator = validator;
        this.quadroOrcamentoRepository = quadroOrcamentoRepository;
        this.clienteRepository = clienteRepository;
        this.parametroRepository = parametroRepository;
	}
	
	@Get("/orcamentos")
	public List<Orcamento> index() {
		return repository.findAll();
	}
	
	@Post("/orcamentos")
	public void create(final Orcamento orcamento) {
	    carregarOrcamento(orcamento);
		validator.onErrorUsePageOf(this).newOrcamento();
		repository.create(orcamento);
		result.redirectTo(this).edit(orcamento);
	}

	private void carregarOrcamento(Orcamento orcamento) {
	    orcamento.setDataAtualizacao(new DateTime());
        result.include("mensagem","Orçamento " + orcamento.getNumero() + "gravado em " + orcamento.getDataAtualizacao().toString("dd/MM/yyyy às HH:mm"));
    }

    @Get("/orcamentos/new")
	public Orcamento newOrcamento() {
	    carregarMetaDados();
		return new Orcamento();
	}
	
	@Put("/orcamentos")
	public void update(Orcamento orcamento) {
		validator.validate(orcamento);
		carregarOrcamento(orcamento);
		validator.onErrorUsePageOf(this).edit(orcamento);
		repository.update(orcamento);
		result.redirectTo(this).index();
	}
	
	@Get("/orcamentos/{orcamento.id}/edit")
	public Orcamento edit(Orcamento orcamento) {
	    carregarMetaDados();
		return repository.find(orcamento.getId());
	}

	@Get("/orcamentos/{orcamento.id}")
	public Orcamento show(Orcamento orcamento) {
		return repository.find(orcamento.getId());
	}

	@Get("/orcamentos/delete/{orcamento.id}")
	public void destroy(Orcamento orcamento) {
		repository.destroy(repository.find(orcamento.getId()));
		result.redirectTo(this).index();  
	}
	
	protected void carregarMetaDados() {
	    result.include("clienteList", clienteRepository.findAll());
	    result.include("quadrosList", quadroOrcamentoRepository.findAll());
	    result.include("parametroList", parametroRepository.findAll());
	}
	
	@SuppressWarnings("unchecked")
    public void incluirQuadro(QuadroOrcamento quadro){
	    List<QuadroOrcamento> quadros =  (ArrayList<QuadroOrcamento>) request.getSession().getAttribute("quadrosLista");
	    if(quadros == null) {
	        quadros = new ArrayList<QuadroOrcamento>();
	    }
	    quadros.add(quadro);
	    request.getSession().setAttribute("quadrosLista", quadros);
	    result.use(Results.json()).withoutRoot().from(quadro).serialize();
	}
	
    @Get("/orcamentos/page/{pgAtual}")
    public List<Orcamento> index(int pgAtual) {
        int qtdRegistros = PaginadorHelper.QTD_REGISTROS_PAGINADOR;
        paginar(pgAtual, qtdRegistros);
        return repository.listarPaginado(pgAtual, qtdRegistros);
    }

    protected void paginar(int pgAtual, int qtdRegistros) {
        PaginadorHelper.paginar(result, pgAtual, qtdRegistros, repository.getQuantidadeRegistros());
    }
	
}