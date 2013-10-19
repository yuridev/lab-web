<head>
	<title>Orcamento [show]</title>
</head>
<body>
	<p>
		<b>Nome:</b>
		${orcamento.nome}
	</p>
	<p>
		<b>Valor total:</b>
		${orcamento.valorTotal}
	</p>

	<a href="${pageContext.request.contextPath}/orcamentos/${orcamento.id}/edit">Edit</a>
	<a href="${pageContext.request.contextPath}/orcamentos">Back</a>
</body>