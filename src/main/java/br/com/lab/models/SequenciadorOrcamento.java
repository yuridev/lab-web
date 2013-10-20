package br.com.lab.models;

import org.joda.time.LocalDate;

@javax.persistence.Entity
public class SequenciadorOrcamento extends Entity{
    
    private Integer numero;
    private LocalDate data;
    public Integer getNumero() {
        return numero;
    }
    public void setNumero(Integer numero) {
        this.numero = numero;
    }
    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }

}
