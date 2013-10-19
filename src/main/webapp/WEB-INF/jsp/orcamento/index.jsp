<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<head>
	<title>Orcamento [index]</title>
</head>
<body>
	<a href="${pageContext.request.contextPath}/orcamentos/new"><img alt="Novo" src="${pageContext.request.contextPath}/imagens/add.png"></a> 
	<table class="table table-striped table-bordered table-condensed">
		<caption><h3>Cadastro de Or&ccedil;mentos</h3></caption>
		<tr>
			<th>N&uacute;mero</th>
			<th>Cliente</th>
			<th>Valor Total</th>
			<th colspan="2">A&ccedil;&otilde;es</th>
		</tr>

		<c:forEach items="${orcamentoList}" var="orcamento">
			<tr>
				<td><a href="${pageContext.request.contextPath}/orcamentos/${orcamento.id}">${orcamento.numero}</a></td>
				<td>${orcamento.cliente.nome}</td>
				<td>${orcamento.valorTotal}</td>
				<td colspan="2">
					<a href="${pageContext.request.contextPath}/orcamentos/${orcamento.id}/edit"><img src="${pageContext.request.contextPath}/imagens/editar.png" title="Editar registro"/></a>

					<a href="${pageContext.request.contextPath}/orcamentos/delete/${orcamento.id}"><img src="${pageContext.request.contextPath}/imagens/excluir.png" title="Excluir registro"/></a>
				</td>				
			</tr>
		</c:forEach>
	</table>
	<tag:paginador url="${pageContext.request.contextPath}/orcamentos/page/"></tag:paginador>
</body>