<%@page import="servlet.ConsultaCNPJUtils"%>
<%@page import="model.FornecedorDadosBean"%>
<%FornecedorDadosBean fornecedorBean = (FornecedorDadosBean) request.getAttribute("fornecedorBean");%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Consulta de CNPJ</title>
<!-- Meta tag Keywords -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
function hideURLbar(){ window.scrollTo(0,1); } </script>
<link href="css/style.css" rel="stylesheet" type="text/css" media="all">
<link href="//fonts.googleapis.com/css?family=Cuprum:400,400i,700,700i&amp;subset=cyrillic,cyrillic-ext,latin-ext,vietnamese" rel="stylesheet">
</head>
<body>
<div class="header">
	<h1>Resultado da consulta</h1>
</div>
<div class="w3-main">
	<!-- Main -->
	<div class="about-bottom main-agile book-form">
		<div>
			<p style="text-align: right; padding-bottom: 30px; color: red;"> Data de cria��o: <%=fornecedorBean.getCriadoEm()%> </p>
		</div>
		
		<div class="form-date-w3-agileits">
			<label>N�mero de inscri��o </label>
			<input type="text" value="<%= ConsultaCNPJUtils.formatCNPJ(fornecedorBean.getNumeroInscricao()) %> - <%=fornecedorBean.getEmpresaTipo() %>">
			<label>Data de abertura </label>
			<input type="text" value="<%=fornecedorBean.getDataAbertura() %>">
			<label>Nome Empresarial</label>
			<input type="text" value="<%=fornecedorBean.getNomeEmpresarial() %>">
			<label>Nome Fantasia</label>
			<input type="text" value="<%=fornecedorBean.getNomeFantasia() %>">
			<label>Atividade Econ�mica Principal</label>
			<input type="text" value="<%=fornecedorBean.getAtividadeEconomicaPrincipal() %>">
			<label>Atividade Econ�mica Secund�rias</label>
			<textarea><%=fornecedorBean.getAtividadeEconomicaSecundaria() %></textarea>
			<label>Natureza Jur�dica</label>
			<input type="text" value="<%=fornecedorBean.getNaturezaJuridica() %>">
			<label>Logradouro</label>
			<input type="text" value="<%=fornecedorBean.getLogradouro() %>">
			<label>N�mero</label>
			<input type="text" value="<%=fornecedorBean.getNumero() %>">
			<label>Munic�pio</label>
			<input type="text" value="<%=fornecedorBean.getMunicipio() %>">
			<label>Complemento</label>
			<input type="text" value="<%=fornecedorBean.getComplemento() %>">
			<label>CEP</label>
			<input type="text" value="<%=fornecedorBean.getCep() %>">
			<label>Bairro</label>
			<input type="text" value="<%=fornecedorBean.getBairro() %>">
			<label>UF</label>
			<input type="text" value="<%=fornecedorBean.getUf() %>">
			<label>Endere�o Eletr�nico</label>
			<input type="text" value="<%=fornecedorBean.getEnderecoEletronico() %>">
			<label>Telefone</label>
			<input type="text" value="<%=fornecedorBean.getTelefone() %>">
			<label>EFR</label>
			<input type="text" value="<%=fornecedorBean.getEnteFederativo() %>">
			<label>Situa��o Cadastral</label>
			<input type="text" value="<%=fornecedorBean.getSituacaoCadastral() %>">
			<label>Data da Situa��o Cadastral</label>
			<input type="text" value="<%=fornecedorBean.getDataSituacaoCadastral() %>">
			<label>Motivo de Situa��o Cadastral</label>
			<input type="text" value="<%=fornecedorBean.getMotivoSituacaoCadastral() %>">
			<label>Situa��o Especial</label>
			<input type="text" value="<%=fornecedorBean.getSituacaoEspecial() %>">
			<label>Data da Situa��o Especial</label>
			<input type="text" value="<%=fornecedorBean.getDataSituacaoEspecial() %>">
			
		</div>
		<div align="center"><a href="http://localhost:8080" class="btn btn-primary">Voltar</a></div>
	</div>
	<!-- //Main -->
</div>
<!-- footer -->
<div class="footer-w3l">
	<p>&copy; 2017 | Gabriel Vieira</p>
</div>
<!-- //footer -->
<!-- js-scripts-->
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
		<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
		<script>$(document).ready(function(c) {
		$('.alert-close').on('click', function(c){
			$('.main-agile').fadeOut('slow', function(c){
				$('.main-agile').remove();
			});
		});	  
	});
	</script>
<!-- //js-scripts-->
</body>
</html>