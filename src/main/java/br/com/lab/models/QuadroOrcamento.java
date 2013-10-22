package br.com.lab.models;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@javax.persistence.Entity
public class QuadroOrcamento extends Entity {

    private String nome;

    @ManyToMany
    private List<Parametro> parametros;

    @ManyToOne
    private Orcamento orcamento;

    private int quantidadeAmostras;

    public int getQuantidadeAmostras() {
        return quantidadeAmostras;
    }

    public void setQuantidadeAmostras(int quantidadeAmostras) {
        this.quantidadeAmostras = quantidadeAmostras;
    }

    private BigDecimal valorTotal;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Parametro> getParametros() {
        return parametros;
    }

    public void setParametros(List<Parametro> parametros) {
        this.parametros = parametros;
    }

    public Orcamento getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

}
