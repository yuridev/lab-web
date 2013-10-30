package br.com.lab.models;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

@javax.persistence.Entity
public class Orcamento extends Entity {

    private String numero;
    private BigDecimal valorTotal;
    @ManyToOne
    private Cliente cliente;

    @OneToMany(mappedBy = "orcamento")
    private List<QuadroOrcamento> quadros;

    private LocalDate dataAtualizacao;
    private LocalTime horaAtualizacao;
    
    private BigDecimal valorKM;
    private BigDecimal valorColeta;
    
    private Integer diasValidade;
    

    public BigDecimal getValorKM() {
        return valorKM;
    }

    public void setValorKM(BigDecimal valorKM) {
        this.valorKM = valorKM;
    }

    public BigDecimal getValorColeta() {
        return valorColeta;
    }

    public void setValorColeta(BigDecimal valorColeta) {
        this.valorColeta = valorColeta;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<QuadroOrcamento> getQuadros() {
        return quadros;
    }

    public void setQuadros(List<QuadroOrcamento> quadros) {
        this.quadros = quadros;
    }

    public LocalDate getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDate dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public LocalTime getHoraAtualizacao() {
        return horaAtualizacao;
    }

    public void setHoraAtualizacao(LocalTime horaAtualizacao) {
        this.horaAtualizacao = horaAtualizacao;
    }

    public Integer getDiasValidade() {
        return diasValidade;
    }

    public void setDiasValidade(Integer diasValidade) {
        this.diasValidade = diasValidade;
    }

}
