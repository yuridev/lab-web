<head>
<title>Cliente [show]</title>
</head>
<body>
	<a href="${pageContext.request.contextPath}/clientes"
		class="pull-rigth"><img title="Voltar"
		src="${pageContext.request.contextPath}/imagens/voltar.png" /></a>
	<div align="center">
		<p>
			<b>Nome:</b> ${cliente.nome}
		</p>
		<p>
			<b>Cpf cnpj:</b> ${cliente.cpfCnpj}
		</p>
		<p>
			<b>Tipo pessoa:</b> ${cliente.tipoPessoa}
		</p>
		<p>
			<b>Telefone:</b> ${cliente.telefone}
		</p>
		<p>
			<b>Email:</b> ${cliente.email}
		</p>
		<a href="${pageContext.request.contextPath}/clientes/${cliente.id}/edit">Editar</a>
	</div>

</body>