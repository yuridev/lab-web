package br.com.lab.util;

public class LabUtil {
    
    
    private static final String FECHA_PARENTESES = ")";
    private static final String ABRE_PARENTESES = "(";

    public static String adicionaParenteses(String valor) {
        return ABRE_PARENTESES + valor + FECHA_PARENTESES;
    }

}
