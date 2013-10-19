<head>
	<title>Parametro [show]</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/parametros" class="pull-rigth"><img title="Voltar" src="${pageContext.request.contextPath}/imagens/voltar.png" /></a>
	<div align="center">
	
		<p>
			<b>Nome:</b>
			${parametro.nome}
		</p>
		<p>
			<b>Unidade medida:</b>
			${parametro.unidadeMedida}
		</p>
		<p>
			<b>Lq:</b>
			${parametro.lq}
		</p>
		<p>
			<b>Metodo:</b>
			${parametro.metodo}
		</p>
		<a href="${pageContext.request.contextPath}/parametros/${parametro.id}/edit">Editar</a>
	</div>

</body>