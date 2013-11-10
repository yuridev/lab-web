package br.com.lab.controllers;

import java.math.BigDecimal;

import br.com.lab.models.Orcamento;
import br.com.lab.models.QuadroOrcamento;

public class OrcamentoHelper {

    public static BigDecimal getValorTotal(Orcamento orcamento) {
        double valorColeta = orcamento.getValorColeta() == null ? 0.0 : orcamento.getValorColeta().doubleValue();
        double valorKM = orcamento.getValorKM() == null ? 0.0 : orcamento.getValorKM().doubleValue();
        double valor = 0;
        valor = (orcamento.getValorTotalQuadros() == null ? 0 : orcamento.getValorTotalQuadros().doubleValue()) + valorColeta + valorKM;
        BigDecimal retorno = new BigDecimal(valor);
        return retorno;
    }

    public static double getValorTotalSomenteParametros(Orcamento orcamento) {
        double valor = 0;
        if (orcamento.getQuadros() != null) {
            for (QuadroOrcamento quadro : orcamento.getQuadros()) {
                double valorTotalPorAmostra =
                        quadro.getValorTotal() == null ? 0.0 : quadro.getValorTotal().doubleValue();
                double valorAmostrasQuadro = quadro.getQuantidadeAmostras() * valorTotalPorAmostra;
                valor += valorAmostrasQuadro;
            }
        }
        return valor;
    }

}
