package br.com.lab.util;

import org.joda.time.LocalDate;

public class DataUtil {

    private static final String[] meses = new String[] {"", "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
            "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};

    private static final String SEPARADOR = " de ";

    public static String getDataPorExtenso(LocalDate dataAtualizacao) {
        StringBuilder data = new StringBuilder("");
        data.append(dataAtualizacao.getDayOfMonth()).append(SEPARADOR).append(meses[dataAtualizacao.getMonthOfYear()])
                .append(SEPARADOR).append(dataAtualizacao.getYear());
        return data.toString();
    }

}
