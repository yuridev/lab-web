package br.com.lab.util;

import br.com.lab.enumeradores.Estado;

public class ValidacaoUtil {

    public static boolean isNuloOuVazio(String string) {
        return string == null || "".equals(string);
    }

    public static boolean isNuloOuVazio(Object object) {
        return object == null;
    }

}
