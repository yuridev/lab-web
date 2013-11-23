package br.com.lab.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import br.com.lab.models.Orcamento;

public class GeradorRelatorioHelper {
    
    
//    private static final String PATH_RELATORIO = "C:\\Users\\Yuri\\Documents\\GitHub\\lab-web\\src\\main\\resources\\br\\com\\lab\\relatorios\\";
    
    private static final String PATH_RELATORIO = "C:\\Users\\Administrador\\Documents\\GitHub\\lab-web\\src\\main\\resources\\br\\com\\lab\\relatorios\\";
    

    public static byte[] createPDF(Orcamento orcamento, Map<String, Object> parametros) {
        byte[] retorno = null;
        List<Orcamento> orcamentos = new ArrayList<Orcamento>();
        orcamentos.add(orcamento);
        parametros.put("SUBREPORT_DIR", PATH_RELATORIO);
        try {
            JRDataSource dataSource = new JRBeanCollectionDataSource(orcamentos);
            JasperPrint print = JasperFillManager.fillReport(PATH_RELATORIO + "orcamento.jasper", parametros , dataSource);
            retorno = JasperExportManager.exportReportToPdf(print);
            System.out.println("RELATORIO GERADO COM SUCESSO!!!!!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
        return retorno;
    }
    
//    public static void main(String[] args) {
//        Orcamento orcamento = new Orcamento();
//        orcamento.setNumero("000113");
//        orcamento.setCliente(new Cliente());
//        orcamento.setQuadros(new ArrayList<QuadroOrcamento>());
//        QuadroOrcamento quadro = new QuadroOrcamento();
//        quadro.setNome("Nome Quadro");
//        quadro.setParametros(new ArrayList<Parametro>());
//        Parametro parametro = new Parametro();
//        parametro.setNome("Parametro 1");
//        parametro.setMetodo("Metodo Parametro 1");
//        parametro.setLd("1,0");
//        parametro.setUnidadeMedida("ml/g");
//        
//        quadro.getParametros().add(parametro);
//        orcamento.getQuadros().add(quadro);
//        
//        
//        orcamento.getCliente().setNome("Yuri Martins da Paixão");
//        createPDF(orcamento);
//    }

}
