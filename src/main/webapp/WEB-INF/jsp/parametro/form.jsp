<form class="form-horizontal"
	action="${pageContext.request.contextPath}/parametros/page/1" method="post">
	<fieldset>
		<c:if test="${not empty parametro.id}">
			<input type="hidden" name="parametro.id" value="${parametro.id}" />
			<input type="hidden" name="_method" value="put" />
		</c:if>

		<!-- Form Name -->
		<legend>
			Cadastro de param&ecirc;tros <a
				href="${pageContext.request.contextPath}/parametros"
				class="btn btn-inverse btn-mini pull-right"><i
				class="icon-white icon-arrow-left"></i> Voltar</a>
		</legend>

		<!-- Text input-->
		<div class="control-group">
			<div class="controls">
				<input id="parametro.nome" name="parametro.nome" type="text"
					placeholder="Nome" class="input-xlarge" required=""
					value="${parametro.nome }">

			</div>
		</div>

		<!-- Text input-->
		<div class="control-group">
			<div class="controls">
				<input id="parametro.unidadeMedida" name="parametro.unidadeMedida"
					type="text" placeholder="Unidade de medida" class="input-xlarge"
					required="" value="${parametro.unidadeMedida}">

			</div>
		</div>

		<!-- Text input-->
		<div class="control-group">
			<div class="controls">
				<input id="parametro.lq" name="parametro.lq" type="text"
					placeholder="LQ" class="input-xlarge" required=""
					value="${parametro.lq}">

			</div>
		</div>

		<div class="control-group">
			<div class="controls">
				<input id="parametro.ld" name="parametro.ld" type="text"
					placeholder="LD" class="input-xlarge" required=""
					value="${parametro.ld}">

			</div>
		</div>

		<!-- Text input-->
		<div class="control-group">
			<div class="controls">
				<input id="parametro.metodo" name="parametro.metodo" type="text"
					placeholder="M&eacute;todo" class="input-xlarge" required=""
					value="${parametro.metodo}">

			</div>
		</div>


		<div class="control-group">
			<div class="controls">
				<input id="parametro.preco" name="parametro.preco" type="text"
					placeholder="Pre&ccedil;o" class="input-xlarge money2" required=""
					value="${parametro.preco}">

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
<script
	src="${pageContext.request.contextPath}/javascripts/jquery.mask.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('.money2').mask("000,00", {
			reverse : true,
			maxlength : true
		});
	})
</script>