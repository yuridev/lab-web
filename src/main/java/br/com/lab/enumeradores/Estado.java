package br.com.lab.enumeradores;

public enum Estado {
    
      AC("1","Acre")
    , AL("2","Alagoas")
    , AP("3","Amap�")
    , AM("4","Amazonas")
    , BA("5","Bahia")
    , CE("6","Cear�")
    , DF("7","Distrito Federal")
    , ES("8","Esp�rito Santo")
    , GO("9","Goi�s")
    , MA("10","Maranh�o")
    , MT("11","Mato Grosso")
    , MS("12","Mato Grosso do Sul")
    , MG("13","Minas Gerais")
    , PA("14","Par�")
    , PB("15","Para�ba")
    , PR("16","Paran�")
    , PE("17","Pernambuco")
    , PI("18","Piau�")
    , RJ("19","Rio de Janeiro")
    , RN("20","Rio Grande do Norte")
    , RS("21","Rio Grande do Sul")
    , RO("22","Rond�nia")
    , SC("23","Santa Catarina")
    , RR("24","Roraima")
    , SP("25","S�o Paulo")
    , SE("26","Sergipe")
    , TO("27","Tocantins");
    
    private String id;
    private String nome;

    private Estado(String id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    
    public String getId() {
        return id;
    }
    
    public String getNome() {
        return nome;
    }
}
