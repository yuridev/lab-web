package br.com.lab.models;

import java.math.BigDecimal;

@javax.persistence.Entity
public class Parametro extends Entity {

	private String nome;
	private String unidadeMedida;
	private String lq;
	private String ld;
	private String metodo;
	private BigDecimal preco;

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setUnidadeMedida(String unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	public String getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setLq(String lq) {
		this.lq = lq;
	}

	public String getLq() {
		return lq;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}

	public String getMetodo() {
		return metodo;
	}

    public String getLd() {
        return ld;
    }

    public void setLd(String ld) {
        this.ld = ld;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

}
