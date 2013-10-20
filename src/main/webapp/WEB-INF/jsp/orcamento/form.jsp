<script type="text/javascript">
	var precos = [];
	var numeroQuadro = 1;
	$("#nomeQuadro").text("Quadro" + numeroQuadro);
</script>

<form action="${pageContext.request.contextPath}/orcamentos"
	method="post" class="form-horizontal" onsubmit="return validar();">
	<fieldset>

		<legend>
			Cadastro de Or&ccedil;amentos <a
				href="${pageContext.request.contextPath}/orcamentos/page/1"
				class="btn btn-inverse btn-mini pull-right"><i
				class="icon-white icon-arrow-left"></i> Voltar</a>
		</legend>
		<c:if test="${not empty orcamento.id}">
			<input type="hidden" name="orcamento.id" value="${orcamento.id}" />
			<input type="hidden" name="_method" value="put" />
			<c:if test="${not empty orcamento.dataAtualizacao}">
				<div class="alert alert-success">
					Or&ccedil;amento n&uacute;mero: ${ orcamento.numero} gravado em 
					${dataAtualizacao }
					&agrave;s
					${horaAtualizacao }
				</div>
			</c:if>
		</c:if>
		<div class="control-group">
			<div class="controls">
				<input id="orcamento.numero" type="text" name="orcamento.numero"
					value="${orcamento.numero}" placeholder="Numero"
					class="input-xlarge" required="">

			</div>
		</div>

		<div class="control-group">
			<div class="controls">
				<select id="orcamento-cliente" name="orcamento.cliente.id"
					class="input-xlarge" required="">
					<option value="0">Selecione</option>
					<c:forEach items="${clienteList}" var="cliente">
						<option value="${cliente.id}"
							<c:if test="${orcamento.cliente.id eq cliente.id}">
							selected="true"
						</c:if>>${cliente.nome}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="control-group">
			<div class="controls">

				<c:if test="${not empty orcamento.id}">
					<a data-toggle="modal" 
						class="btn btn-primary btn-lg" href="#myModal">Incluir quadro</a>
				</c:if>

				<!-- Modal -->
				<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h4 class="modal-title">Inclus&atilde;o de quadros</h4>
							</div>
							<div class="modal-body">
								<div class="control-group">
									<div class="controls">
										<input type="text" id="nomeQuadro"
											placeholder="Nome do quadro" disabled="true"/>
									</div>
								</div>
								<div class="control-group">
									<div class="controls">
										<select id="orcamento.parametros" class="input-xlarge">
											<option value="0">Selecione um Par&acirc;metro</option>
											<c:forEach items="${parametroList}" var="parametro">
												<option value="${parametro.id}">${parametro.nome}</option>
												<script type="text/javascript">
													precos['${parametro.id}'] = '${parametro.preco}';
												</script>
											</c:forEach>
										</select>
										<button type="button" class="btn"
											onclick="adicionaParametro();">Add</button>
									</div>
								</div>
								<div class="control-group">
									<div class="controls">
										<ul id="listaParametros">
										</ul>
									</div>
								</div>
								<div class="control-group">
									<div class="controls">
										<input type="text" id="valorTotal"
											placeholder="Valor por amostra (R$)" class="money2" disabled="true"/>
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
								<button type="button" class="btn btn-primary"
									onclick="incluirQuadro();">Incluir</button>

								<script type="text/javascript">
							
								function adicionaParametro() {
									var combo = document.getElementById("orcamento.parametros");
									var valor = combo[combo.selectedIndex].text;
									var idParametro = combo[combo.selectedIndex].value;
									var precoParametro = precos[idParametro];
									var incluir = true;
									
									if(idParametro == 0) {
										alert("Selecione um param�tro para ser adicionado.");
										incluir = false;
									}
									if(precoParametro == 0) {
										alert("Parametro sem pre�o!");
										incluir = false;
									}
									$("input[name=parametros]").each(function(index, obj){
										if(idParametro == obj.value) {
											alert("Par�metro j� adicionado, escolha outro par�metro!");
											incluir = false;
										}
									});
									if(incluir) {
										texto = valor + "<input type='hidden' name='parametros' value='" + idParametro + "'>";
										var id = "li-"+idParametro;
										$("#listaParametros")
										.append($('<li>').attr('id', id)
												.append($('<span>').attr('class', 'tab')
														.append(texto))
															.append($('<i>').attr('class', 'icon-trash icon-black pull-right').click(function() {removerParametro(id, idParametro)})));
										var campoValorAtual = document.getElementById("valorTotal")
										var valorAtual = campoValorAtual.value;
										if(valorAtual == '') {
											valorAtual = 0.0;
										} else {
											valorAtual =  valorAtual.replace(",", ".")
										} 
										var valorTotalAmostra = 0.0 + parseFloat(valorAtual);
										valorTotalAmostra += parseFloat(precoParametro);
										campoValorAtual.value = Number(valorTotalAmostra).toFixed(2);
										$('.money2').mask("#.##0,00", {
											reverse : true,
											maxlength : false
										});
									}
									combo.value = 0;
								}
								
								
								function removerParametro(item, idParametro) {
									var campoValorAtual = document.getElementById("valorTotal");
									var valorFinal = 0.0;
									valorFinal = parseFloat(campoValorAtual.value.replace(",", ".")) - precos[idParametro];
									campoValorAtual.value = Number(valorFinal).toFixed(2);
									$('.money2').mask("#.##0,00", {
										reverse : true,
										maxlength : false
									});
									$("#"+item).remove();
								}
								
								function incluirQuadro() {
									
									var nome = $("#nomeQuadro").val();
									var parametros = '';
									var valorTotal = $("#valorTotal").val();
									$("input[name=parametros]").each(function(index, obj){
										parametros += 'quadro.parametros[' + index + '].id=' + obj.value + '&';
									});
									parametros = parametros.substring(0, parametros.length - 1);
									if(parametros != '') {
										var dados = {
											"quadro.nome" : nome,
											"quadro.valorTotal" : valorTotal,
											"quadro.orcamento.id" : ${orcamento.id}
										};
										
										$.getJSON("/orcamento/incluirQuadro?" + parametros, dados, function(json) {
											$("#table-quadros").append($('<tr>').attr('id', 'tr-quadro-' + json.id)
												.append($('<td>').append(json.nome))
												.append($('<td>').append(json.valorTotal))
												.append($('<td>').append($('<a>')
														.attr('href', '${pageContext.request.contextPath}/quadroOrcamento/delete/' + json.id)
														.attr('src', '${pageContext.request.contextPath}/imagens/excluir.png')
														.attr('title', 'Excluir quadro'))));
											$("#listaParametros").empty();
											document.getElementById("valorTotal").value = Number(0.0).toFixed(2);
											document.getElementById("nomeQuadro").value = "";
											alert("Quadro incluido com sucesso!")
										});
									} else {
										alert("Selecione alguns parametros para incluir o quadro!");
									}
									numeroQuadro++;
									document.getElementById("nomeQuadro").value = "Quadro " + numeroQuadro;
								}
								
								function validar() {
									if($("#orcamento-cliente").val() == 0 ) {
										alert("Campo cliente obrigatorio.");
										$("#orcamento-cliente").focus();
										return false;
									}
									return true;
								}
								
								
							
							</script>
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>
				<!-- /.modal -->






























				<fieldset>
					<legend>Quadros</legend>
					<table id="table-quadros"
						class="table table-striped table-bordered table-condensed">
						<tr>
							<th>Quadro</th>
							<th>Valor total</th>
							<th>A&ccedil;&otilde;es</th>
						</tr>
						<c:if test="${not empty orcamento.quadros}">
							<c:forEach items="${orcamento.quadros }" var="quadroFor">
								<tr id="tr-quadro-${quadroFor.id }">
									<td><a href="">${quadroFor.nome }</a></td>
									<td>${quadroFor.valorTotal }</td>
									<td><a
										href="${pageContext.request.contextPath}/quadroOrcamento/delete/${quadroFor.id}"><img
											src="${pageContext.request.contextPath}/imagens/excluir.png" title="Excluir registro" /></a></td>
								</tr>
							</c:forEach>
						</c:if>
					</table>
				</fieldset>
			</div>
		</div>

		<div class="control-group">
			<div class="controls"></div>
		</div>

		<!-- 		<div class="control-group"> -->
		<!-- 			<div class="controls"> -->


		<!-- 			</div> -->
		<!-- 		</div> -->

		<div class="control-group">
			<div class="controls">
				<button class="btn btn-success">Salvar</button>
			</div>
		</div>
</form>

<script
	src="${pageContext.request.contextPath}/javascripts/jquery.mask.min.js"></script>
<script type="text/javascript">
	document.getElementById("nomeQuadro").value = "Quadro " + numeroQuadro;
</script>