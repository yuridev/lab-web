<head>
	<title>Listagem de Clientes</title>
</head>
<body>

	<a href="${pageContext.request.contextPath}/clientes/new/PF"><img title="Cadastrar Pessoa F&iacute;sica" src="imagens/novo_cliente.png"></a>
	<a href="${pageContext.request.contextPath}/clientes/new/PJ"><img title="Cadastrar Pessoa Juridica" src="imagens/add_company.png"></a>  

	<table class="table table-striped table-bordered table-condensed">
		<caption><h3>Cadastro de clientes</h3></caption>
		<tr>
			<th colspan="2">Nome</th>
			<th>CPF/CPNJ</th>
			<th>Telefone</th>
			<th>Email</th>
			<th colspan="2">A&ccedil;&otilde;es</th>
		</tr>
		
		<c:forEach items="${clienteList}" var="cliente">
			<tr>
				<td colspan="2"><a href="${pageContext.request.contextPath}/clientes/${cliente.id}">${cliente.nome}</a></td>
				<td>${cliente.cpfCnpj}</td>
				<td>${cliente.telefone}</td>
				<td>${cliente.email}</td>
				<td colspan="2">
					<a href="${pageContext.request.contextPath}/clientes/${cliente.id}/edit"><img src="imagens/editar.png" title="Editar registro"/></a>

					<a href="#" onclick="excluirRegistro('${pageContext.request.contextPath}/clientes/delete/${cliente.id}');"><img src="imagens/excluir.png" title="Excluir registro"/></a>
				</td>
			</tr>
		</c:forEach>
	</table>
	
	<br />
</body>