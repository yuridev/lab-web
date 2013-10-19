<head>
	<title>Parametro [index]</title>
</head>
<body>

	<a href="${pageContext.request.contextPath}/parametros/new"><img alt="Novo" src="${pageContext.request.contextPath}/imagens/add.png"></a> 
	<table class="table table-striped table-bordered table-condensed">
		<caption><h3>Cadastro de Param&ecirc;tros</h3></caption>
		<tr>
			<th>Nome</th>
			<th>Unidade medida</th>
			<th>Lq</th>
			<th>Metodo</th>
			<th colspan="2">A&ccedil;&otilde;es</th>
		</tr>

		<c:forEach items="${parametroList}" var="parametro">
			<tr>
				<td><a href="${pageContext.request.contextPath}/parametros/${parametro.id}">${parametro.nome}</a></td>
				<td>${parametro.unidadeMedida}</td>
				<td>${parametro.lq}</td>
				<td>${parametro.metodo}</td>
				<td colspan="2">
					<a href="${pageContext.request.contextPath}/parametros/${parametro.id}/edit"><img src="imagens/editar.png" title="Editar registro"/></a>

					<a href="${pageContext.request.contextPath}/parametros/delete/${parametro.id}"><img src="imagens/excluir.png" title="Excluir registro"/></a>
				</td>				
			</tr>
		</c:forEach>
	</table>

	<br />
</body>