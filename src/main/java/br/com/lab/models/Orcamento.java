package br.com.lab.models;

import java.util.List;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@javax.persistence.Entity
public class Orcamento extends Entity {

    private String numero;
    private Double valorTotal;
    @ManyToOne
    private Cliente cliente;
    
    @OneToMany
    private List<QuadroOrcamento> quadros;

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Double getValorTotal() {
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

}
