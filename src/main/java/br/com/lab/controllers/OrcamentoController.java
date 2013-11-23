package br.com.lab.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import br.com.lab.helper.GeradorRelatorioHelper;
import br.com.lab.helpers.PaginadorHelper;
import br.com.lab.models.Orcamento;
import br.com.lab.models.QuadroOrcamento;
import br.com.lab.models.SequenciadorOrcamento;
import br.com.lab.repositories.ClienteRepository;
import br.com.lab.repositories.OrcamentoRepository;
import br.com.lab.repositories.ParametroRepository;
import br.com.lab.repositories.QuadroOrcamentoRepository;
import br.com.lab.repositories.SequenciadorOrcamentoRepository;
import br.com.lab.util.CurrencyWriter;
import br.com.lab.util.DataUtil;
import br.com.lab.util.LabUtil;

@Resource
public class OrcamentoController {

    private static final String IMAGEM_CABECALHO = "C:\\Users\\Administrador\\Documents\\GitHub\\lab-web\\src\\main\\resources\\br\\com\\lab\\relatorios\\logokbf.jpg";
    private static final String IMAGEM_RODAPE = "C:\\Users\\Administrador\\Documents\\GitHub\\lab-web\\src\\main\\resources\\br\\com\\lab\\relatorios\\rodape.png";
    
//    private static final String IMAGEM_CABECALHO = "C:\\Users\\Yuri\\Documents\\GitHub\\lab-web\\src\\main\\resources\\br\\com\\lab\\relatorios\\logokbf.jpg";
//    private static final String IMAGEM_RODAPE = "C:\\Users\\Yuri\\Documents\\GitHub\\lab-web\\src\\main\\resources\\br\\com\\lab\\relatorios\\rodape.png";
    
    private final Result result;
    private final OrcamentoRepository repository;

    private final Validator validator;
    private final QuadroOrcamentoRepository quadroOrcamentoRepository;
    private final ClienteRepository clienteRepository;
    private final ParametroRepository parametroRepository;
    private final SequenciadorOrcamentoRepository sequenciadorOrcamentoRepository;
    private HttpServletResponse response;

    public OrcamentoController(Result result, OrcamentoRepository repository, Validator validator,
            QuadroOrcamentoRepository quadroOrcamentoRepository, ClienteRepository clienteRepository,
            ParametroRepository parametroRepository, SequenciadorOrcamentoRepository sequenciadorOrcamentoRepository, HttpServletResponse response) {
        this.result = result;
        this.repository = repository;
        this.validator = validator;
        this.quadroOrcamentoRepository = quadroOrcamentoRepository;
        this.clienteRepository = clienteRepository;
        this.parametroRepository = parametroRepository;
        this.sequenciadorOrcamentoRepository = sequenciadorOrcamentoRepository;
        this.response = response;
    }

    @Get("/orcamentos")
    public List<Orcamento> index() {
        return repository.findAll();
    }

    @Post("/orcamentos")
    public void create(final Orcamento orcamento) {
        carregarOrcamentoCreate(orcamento);
        validator.onErrorUsePageOf(this).newOrcamento();
        repository.create(orcamento);
        result.redirectTo(this).edit(orcamento);
    }

    private void carregarOrcamentoCreate(Orcamento orcamento) {
        SequenciadorOrcamento next = sequenciadorOrcamentoRepository.getNext();
        sequenciadorOrcamentoRepository.update(next);
        formatarNumero(orcamento, next);
        carregarOrcamentoUpdate(orcamento);
    }

    private void carregarOrcamentoUpdate(Orcamento orcamento) {
        Orcamento find;
        if(orcamento.getId() != null) {
            find = repository.find(orcamento.getId());
        } else {
            find = orcamento;
        }
                
        orcamento.setValorTotalQuadros(new BigDecimal(OrcamentoHelper.getValorTotalSomenteParametros(find)));
        orcamento.setValorTotalQuadrosExtenso(LabUtil.adicionaParenteses(new CurrencyWriter().writeCapitalize(orcamento
                .getValorTotalQuadros())));

        orcamento.setValorTotal(OrcamentoHelper.getValorTotal(orcamento));
        orcamento.setValorTotalExtenso(LabUtil.adicionaParenteses(new CurrencyWriter().writeCapitalize(orcamento
                .getValorTotal())));
        orcamento.setDataAtualizacao(new LocalDate());
        orcamento.setDataPorExtenso(DataUtil.getDataPorExtenso(orcamento.getDataAtualizacao()));
        orcamento.setHoraAtualizacao(new LocalTime());
    }

    @Get("/orcamentos/new")
    public Orcamento newOrcamento() {
        carregarMetaDados();
        Orcamento orcamento = new Orcamento();
        SequenciadorOrcamento next = sequenciadorOrcamentoRepository.getNext();
        formatarNumero(orcamento, next);
        return orcamento;
    }

    protected void formatarNumero(Orcamento orcamento, SequenciadorOrcamento next) {
        DecimalFormat format = new DecimalFormat("0000");
        String numero = format.format(next.getNumero());
        orcamento.setNumero(numero + "/" + next.getData());
    }

    @Put("/orcamentos")
    public void update(Orcamento orcamento) {
        validator.validate(orcamento);
        carregarOrcamentoUpdate(orcamento);
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
        QuadroOrcamento retorno = quadroOrcamentoRepository.find(quadro.getId());
        result.use(Results.json()).withoutRoot().from(retorno).recursive().serialize();
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

    @Get("/orcamentos/reports/{orcamento.id}")
    public void openReport(Orcamento orcamento) {
        Orcamento find = repository.find(orcamento.getId());
        byte[] arquivo = GeradorRelatorioHelper.createPDF(find, getParametrosRelatorio());
        response.setContentType("application/pdf");  
        response.setContentLength(arquivo.length);
        String nomeArquivo = "Orcamento_" + find.getNumero() + "_" + find.getCliente().getNome() + ".pdf";
        response.setHeader("Content-Disposition", "filename=\"" + nomeArquivo );
        ServletOutputStream ouputStream;
        try {
            ouputStream = response.getOutputStream();
            ouputStream.write(arquivo, 0, arquivo.length);  
            ouputStream.flush();  
            ouputStream.close();  
        } catch (IOException e) {
            e.printStackTrace();
        }  
    }

    private Map<String, Object> getParametrosRelatorio() {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("IMAGEM_CABECALHO", IMAGEM_CABECALHO);
        parametros.put("IMAGEM_RODAPE", IMAGEM_RODAPE);
        return parametros;
    }

}