<script type="text/javascript">
	var precos = [];
	var parametrosAux = '';
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
				N&uacute;mero:
				<span class="label label-default">${orcamento.numero}</span>
				<input type="hidden" name="orcamento.numero" value="${orcamento.numero}"/>
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
											placeholder="Nome do quadro" />
									</div>
								</div>
								<div class="control-group">
									<div class="controls">
										<select id="orcamento-parametros" class="input-xlarge">
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
										<input type="text" id="quantidadeAmostras"
											placeholder="Quantidade de amostras" class="integer" />
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
									var valor = $("#orcamento-parametros option:selected").text();
									var idParametro = $("#orcamento-parametros option:selected").val();
									var precoParametro = precos[idParametro];
									var incluir = true;
									parametrosAux += valor + ", ";
									
									if(idParametro == 0) {
										alert("Selecione um paramâtro para ser adicionado.");
										incluir = false;
									}
									if(precoParametro == 0) {
										alert("Parametro sem preço!");
										incluir = false;
									}
									$("input[name=parametros]").each(function(index, obj){
										if(idParametro == obj.value) {
											alert("Parâmetro já adicionado, escolha outro parâmetro!");
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
									$("#orcamento-parametros").val(0);
								}
								
								
								function removerParametro(item, idParametro) {
									var campoValorAtual = $("input=[id=valorTotal]");
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
									var quantidadeAmostras = $("#quantidadeAmostras").val();
									var nome = $("#nomeQuadro").val();
									var parametros = '';
									var valorTotal = $("#valorTotal").val();
									$("input[name=parametros]").each(function(index, obj){
										parametros += 'quadro.parametros[' + index + '].id=' + obj.value + '&';
									});
									parametros = parametros.substring(0, parametros.length - 1);
									
									var incluir = true;
									
									if(nome == '' && incluir) {
										alert("Campo nome é de preenchimento obrigatório.");
										$("#nomeQuadro").focus();
										incluir = false;
									}
									
									if(parametros == '' && incluir) {
										alert("Selecione alguns parametros para incluir o quadro.");
										incluir = false;
									}
										
									if(quantidadeAmostras == '' && incluir) {
										alert("Campo quantidade de amostras é de preenchimento obrigatório.");
										$("#quantidadeAmostras").focus();
										incluir = false;
									}
									if(incluir) {
										var dados = {
											"quadro.nome" : nome,
											"quadro.valorTotal" : valorTotal,
											"quadro.orcamento.id" : '${orcamento.id}',
											"quadro.quantidadeAmostras" : quantidadeAmostras
										};
										
										$.getJSON("/orcamento/incluirQuadro?" + parametros, dados, function(json) {
											parametrosAux = parametrosAux.substring(0, parametrosAux.length - 2);
											$("#table-quadros").append($('<tr>').attr('id', 'tr-quadro-' + json.id)
												.append($('<td>').append(json.nome))
												.append($('<td>').append(parametrosAux))
												.append($('<td>').append(json.valorTotal))
												.append($('<td>').append(json.quantidadeAmostras))
												.append($('<td>')
														.append($('<a>').attr('href', '#').attr('title', 'Excluir quadro').click(function(){ deletarQuadro(json.id);})
																.append($('<img>').attr('src', '${pageContext.request.contextPath}/imagens/excluir.png')))));
											$("#listaParametros").empty();
											document.getElementById("valorTotal").value = Number(0.0).toFixed(2);
											document.getElementById("nomeQuadro").value = "";
											parametrosAux = '';
											alert("Quadro incluido com sucesso!");
										});
									}
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
							<th>Par&acirc;metros</th>
							<th>Valor (R$)</th>
							<th>Quantidade Amostras</th>
							<th>A&ccedil;&otilde;es</th>
						</tr>
						<c:if test="${not empty orcamento.quadros}">
							<c:forEach items="${orcamento.quadros }" var="quadroFor">
								<tr id="tr-quadro-${quadroFor.id }">
									<td>${quadroFor.nome }</td>
									<td>
										<c:set scope="page" var="virgula"/>
										<span>
										<c:forEach items="${quadroFor.parametros }" var="parametroQuadro">
											${virgula }&nbsp;${parametroQuadro.nome }
											<c:set var="virgula" value=","/>
										</c:forEach> 
										</span>
									</td>
									<td>${quadroFor.valorTotal }</td>
									<td>${quadroFor.quantidadeAmostras }</td>
									<td><a
										href="#" onclick="deletarQuadro(${quadroFor.id });"><img
											src="${pageContext.request.contextPath}/imagens/excluir.png" title="Excluir registro" /></a></td>
								</tr>
							</c:forEach>
						</c:if>
					</table>
				</fieldset>
			</div>
		</div>

		<div class="control-group">
			<div class="controls">
				<input type="text" name="orcamento.valorColeta" placeholder="Valor da Coleta" value="${orcamento.valorColeta }" class="money1" required="required"/>
			</div>
		</div>
		
		<div class="control-group">
			<div class="controls">
				<input type="text" name="orcamento.valorKM" placeholder="KM rodado (R$) x kilometragem" value="${orcamento.valorKM }" class="money1" required="required"/>
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<input type="text" name="orcamento.diasValidade" placeholder="Dias de validade" value="${orcamento.diasValidade == 0 ? "" : orcamento.diasValidade}" class="integer" required="required"/>
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<fieldset>
					<legend>Total or&ccedil;amento</legend>
					<h1>
						<c:if test="${ not empty orcamento.valorTotal }">
						R$
						</c:if>
						<span id="valorTotalOrcamento" class="money3">${ orcamento.valorTotal }</span>
					</h1>
				</fieldset>
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<button class="btn btn-success">Salvar / Calcular Or&ccedil;amento</button>
<%-- 				<a class="btn btn-warning" onclick="calcularValorTotalOrcamento('${orcamento.id}');" href="#">Calcular Or&ccedil;amento</a> --%>
			</div>
		</div>
</form>

<script
	src="${pageContext.request.contextPath}/javascripts/jquery.mask.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('.integer').mask("##0", {
			reverse : true,
			maxlength : true
		});
		
		$('.money1').mask("###.##0,00", {
			reverse : true,
			maxlength : true
		});
		
		$('.money3').mask("##.#00,00", {
			reverse : true,
			maxlength : false
		});
		
	});


	function deletarQuadro(id) {
		$.getJSON("/orcamentos/deletarQuadro/" + id, function(json) {
			$('#tr-quadro-'+json.id).remove();
		});
	}
</script>