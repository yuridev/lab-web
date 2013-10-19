<%@tag language="java" pageEncoding="UTF-8"%>

<%-- Importando a JSTL --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- Atributos --%>

<%@attribute name="url" type="java.lang.String"%>

<link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/css/bootstrap-combined.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/javascripts/bootstrap-paginator/bootstrap-paginator.min.js"></script>

 <div id="paginador"></div>
    <script type='text/javascript'>
    var qtdRg = parseInt(${qtdRegistros});
    if(qtdRg == '') {
    	qtdRg = 20;
    }
    var options = {
            currentPage: ${pgAtual},
            totalPages: ${qtdPaginas},
            useBootstrapTooltip:true,
            pageUrl: function(type, page, current) {
            	return '${url}' + page;
            },
            tooltipTitles: function (type, page, current) {
                switch (type) {
                case "first":
                    return "Ir para a primeira página<i class='icon-fast-backward icon-white'></i>";
                case "prev":
                    return "Ir para a página anterior<i class='icon-backward icon-white'></i>";
                case "next":
                    return "Ir para a próxima página <i class='icon-forward icon-white'></i>";
                case "last":
                    return "Ir para a última página<i class='icon-fast-forward icon-white'></i>";
                case "page":
                    return "Ir para a página " + page + " <i class='icon-file icon-white'></i>";
                }
            },
            bootstrapTooltipOptions: {
                html: true,
                placement: 'top'
            }
        }

        $('#paginador').bootstrapPaginator(options);
    </script>