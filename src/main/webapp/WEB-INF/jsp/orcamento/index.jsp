<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<head>
	<title>Orcamento [index]</title>
</head>
<body>
	<a href="${pageContext.request.contextPath}/orcamentos/new"><img alt="Novo" src="${pageContext.request.contextPath}/imagens/add.png"></a> 
	<table class="table table-striped table-bordered table-condensed">
		<caption><h3>Cadastro de Or&ccedil;amentos</h3></caption>
		<tr>
			<th width="20%">N&uacute;mero</th>
			<th width="50%">Cliente</th>
			<th width="20%">Valor Total</th>
			<th width="10%" colspan="2">A&ccedil;&otilde;es</th>
		</tr>

		<c:forEach items="${orcamentoList}" var="orcamento">
			<tr>
				<td>${orcamento.numero}</td>
				<td>${orcamento.cliente.nome}</td>
				<td><span class="money3">${orcamento.valorTotal}</span></td>
				<td colspan="2">
				
					<a target="_blank" href="${pageContext.request.contextPath}/orcamentos/reports/${orcamento.id}"><img src="${pageContext.request.contextPath}/imagens/pdf.png" title="Gerar Orçamento em PDF"/></a>
				
					<a href="${pageContext.request.contextPath}/orcamentos/${orcamento.id}/edit"><img src="${pageContext.request.contextPath}/imagens/editar.png" title="Editar registro"/></a>

					<a href="${pageContext.request.contextPath}/orcamentos/delete/${orcamento.id}"><img src="${pageContext.request.contextPath}/imagens/excluir.png" title="Excluir registro"/></a>
				</td>				
			</tr>
		</c:forEach>
	</table>
	<tag:paginador url="${pageContext.request.contextPath}/orcamentos/page/"></tag:paginador>
	
	<script
	src="${pageContext.request.contextPath}/javascripts/jquery.mask.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('.money3').mask("##.#00,00", {
				reverse : true,
				maxlength : false
			});
		});
	</script>	
</body>