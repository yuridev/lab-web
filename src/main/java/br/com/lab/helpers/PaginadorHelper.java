package br.com.lab.helpers;

import br.com.caelum.vraptor.Result;

public class PaginadorHelper {
    
    
    public static final int QTD_REGISTROS_PAGINADOR = 20;

    public static void paginar(Result result, int pgAtual, int qtdRegistros, int totalRegistros) {
        int qtdPaginas =  totalRegistros / qtdRegistros;
        result.include("qtdPaginas", qtdPaginas).include("pgAtual", pgAtual);
    }

}
