package br.com.lab.controllers;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.view.Results;
import br.com.lab.helpers.PaginadorHelper;
import br.com.lab.models.Orcamento;
import br.com.lab.models.QuadroOrcamento;
import br.com.lab.models.SequenciadorOrcamento;
import br.com.lab.repositories.ClienteRepository;
import br.com.lab.repositories.OrcamentoRepository;
import br.com.lab.repositories.ParametroRepository;
import br.com.lab.repositories.QuadroOrcamentoRepository;
import br.com.lab.repositories.SequenciadorOrcamentoRepository;

@Resource
public class OrcamentoController {

    private final Result result;
    private final OrcamentoRepository repository;

    private final Validator validator;
    private final QuadroOrcamentoRepository quadroOrcamentoRepository;
    private final ClienteRepository clienteRepository;
    private final ParametroRepository parametroRepository;
    private final HttpServletRequest request;
    private final SequenciadorOrcamentoRepository sequenciadorOrcamentoRepository;

    public OrcamentoController(HttpServletRequest request, Result result, OrcamentoRepository repository,
            Validator validator, QuadroOrcamentoRepository quadroOrcamentoRepository,
            ClienteRepository clienteRepository, ParametroRepository parametroRepository, SequenciadorOrcamentoRepository sequenciadorOrcamentoRepository) {
        this.request = request;
        this.result = result;
        this.repository = repository;

        this.validator = validator;
        this.quadroOrcamentoRepository = quadroOrcamentoRepository;
        this.clienteRepository = clienteRepository;
        this.parametroRepository = parametroRepository;
        this.sequenciadorOrcamentoRepository = sequenciadorOrcamentoRepository;
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
        SequenciadorOrcamento next = sequenciadorOrcamentoRepository.getNext();
        sequenciadorOrcamentoRepository.create(next);
        orcamento.setDataAtualizacao(new LocalDate());
        orcamento.setHoraAtualizacao(new LocalTime());
    }

    @Get("/orcamentos/new")
    public Orcamento newOrcamento() {
        carregarMetaDados();
        Orcamento orcamento = new Orcamento();
        DecimalFormat format = new DecimalFormat("0000");
        SequenciadorOrcamento next = sequenciadorOrcamentoRepository.getNext();
        String numero = format.format(next.getNumero());
        orcamento.setNumero(numero + "/" + next.getData().toString("yy"));
        return orcamento;
    }

    @Put("/orcamentos")
    public void update(Orcamento orcamento) {
        validator.validate(orcamento);
        carregarOrcamento(orcamento);
        validator.onErrorUsePageOf(this).edit(orcamento);
        repository.update(orcamento);
        result.redirectTo(this).edit(orcamento);
    }

    @Get("/orcamentos/{orcamento.id}/edit")
    public Orcamento edit(Orcamento orcamento) {
        carregarMetaDados();
        Orcamento find = repository.find(orcamento.getId());
        carregarDatas(find);
        return find;
    }

    private void carregarDatas(Orcamento orcamento) {
        result.include("dataAtualizacao", orcamento.getDataAtualizacao().toString("dd/MM/yyyy"));
        result.include("horaAtualizacao", orcamento.getHoraAtualizacao().toString("HH:mm:ss"));
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
        result.include("parametroList", parametroRepository.findAll());
    }

    public void incluirQuadro(QuadroOrcamento quadro) {
        quadroOrcamentoRepository.create(quadro);
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
    
    @Get("/orcamentos/deletarQuadro/{quadroOrcamento.id}")
    public void destroy(QuadroOrcamento quadroOrcamento) {
        quadroOrcamentoRepository.destroy(quadroOrcamentoRepository.find(quadroOrcamento.getId()));
        result.use(Results.json()).withoutRoot().from(quadroOrcamento).serialize();  
    }

}