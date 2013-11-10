<form class="form-horizontal"
	action="${pageContext.request.contextPath}/clientes" method="post">
	<c:if test="${not empty cliente.id}">
		<input type="hidden" name="cliente.id" value="${cliente.id}" />
		<input type="hidden" name="_method" value="put" />
	</c:if>
	<fieldset>

		<!-- Form Name -->
		<legend>
			Cadastro de clientes <a
				href="
				${pageContext.request.contextPath}/clientes"
				class="btn btn-inverse btn-mini pull-right"><i
				class="icon-white icon-arrow-left"></i> Voltar</a>
		</legend>

		<!-- Text input-->
		<div class="control-group">
			<div class="controls">
				<input id="cliente.nome" name="cliente.nome" type="text"
					<c:if test="${cliente.tipoPessoa eq 'PF' }">
						placeholder="Nome"
					</c:if>
					<c:if test="${cliente.tipoPessoa != 'PF' }">
						placeholder="Raz&atilde;o Social"
					</c:if>
					 class="input-xlarge" required="" value="${cliente.nome}">

			</div>
		</div>

		<!-- Text input-->
		<div class="control-group">
			<div class="controls">
			<c:if test="${cliente.tipoPessoa == 'PF'}">
				<input type="hidden" name="cliente.tipoPessoa" value="CPF"/>
				<input name="cliente.cpfCnpj" id="cliente.cpfCnpj" type="text"
					placeholder="CPF" class="input-xlarge" data-mask="999.999.999-99" required="" value="${cliente.cpfCnpj }" onblur="validar(this.value);">
			</c:if>
			<c:if test="${cliente.tipoPessoa != 'PF' }" >
				<input type="hidden" name="cliente.tipoPessoa" value="CNPJ"/>
				<input name="cliente.cpfCnpj" id="cliente.cpfCnpj" type="text"
					placeholder="CNPJ" class="input-xlarge" data-mask="99.999.999/9999-99" required="" value="${cliente.cpfCnpj }" onblur="validar(this.value);">
			</c:if>
			</div>
		</div>

		<!-- Text input-->
		<div class="control-group">
			<div class="controls">
				<input id="cliente.telefone" name="cliente.telefone" type="text"
					placeholder="Telefone" class="input-xlarge" data-mask="(99) 9999 - 9999" required="" value="${cliente.telefone}">

			</div>
		</div>

		<!-- Text input-->
		<div class="control-group">
			<div class="controls">
				<input id="cliente.email" name="cliente.email" type="text"
					placeholder="Email" class="input-xlarge" required="" value="${cliente.email}">

			</div>
		</div>

		<!-- Text input-->
		<div class="control-group">
			<div class="controls">
				<input id="cliente.endereco" name="cliente.endereco" type="text"
					placeholder="Endere&ccedil;o" class="input-xxlarge" required="" value="${cliente.endereco}">

			</div>
		</div>

		<!-- Text input-->
		<div class="control-group">
			<div class="controls">
				<input id="cliente.cidade" name="cliente.cidade" type="text"
					placeholder="Cidade" class="input-xlarge" required="" value="${cliente.cidade}">

			</div>
		</div>

		<!-- Select Basic -->
		<div class="control-group">
			<div class="controls">
				<select id="cliente.estado" name="cliente.estado"
					class="input-xlarge" required="" value="${cliente.estado}">
					<option value="0">Selecione</option>
					<c:forEach items="${estadosList}" var="estado">
						<option value="${estado}" 
						<c:if test="${cliente.estado eq estado}">
							selected="true"
						</c:if>
						>${estado.nome}</option>
					</c:forEach>
				</select>
			</div>
		</div>

		<!-- Button -->
		<div class="control-group">
			<div class="controls">
				<button class="btn btn-success">Salvar</button>
			</div>
		</div>

	</fieldset>
</form>



<script type="text/javascript">


function validar(valor) {
	if(valor != "") {
		var campo = document.getElementById("cliente.cpfCnpj");
		if('${cliente.tipoPessoa}' == 'PF') {
			if(!validarCPF(valor)) {
				alert("CPF invalido!");
				campo.value = "";
				campo.focus();
			}
		} else {
			if(!validarCNPJ(valor)) {
				alert("CNPJ invalido!");
				campo.value = "";
				campo.focus();
			}
		}
		$.getJSON("/cliente/verificaCpfCnpj?cpfCnpj="+valor, function (json) {
			if(json) {
				alert("CPF/CNPJ já existente!");
				campo.value = "";
				campo.focus();
			}
		});
		
	}
}

</script>

<script
	src="${pageContext.request.contextPath}/javascripts/jquery.mask.min.js"></script>