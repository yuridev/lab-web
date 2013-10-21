package br.com.lab.models;


@javax.persistence.Entity
public class SequenciadorOrcamento extends Entity{
    
    private Integer numero;
    private String data;
    public Integer getNumero() {
        return numero;
    }
    public void setNumero(Integer numero) {
        this.numero = numero;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }

}
