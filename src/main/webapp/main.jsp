<%String cnpj = (String) request.getAttribute("cnpj");
String showCaptcha = (String) request.getAttribute("showCaptcha");
String mensagem = (String) request.getAttribute("mensagem");
String tipoMensagem = (String) request.getAttribute("tipoMensagem");
if (showCaptcha == null || showCaptcha.isEmpty()) showCaptcha = "none";%>
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
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all">
<link href="css/font-awesome.css" rel="stylesheet" type="text/css" media="all">
<link href="//fonts.googleapis.com/css?family=Cuprum:400,400i,700,700i&amp;subset=cyrillic,cyrillic-ext,latin-ext,vietnamese" rel="stylesheet">
</head>
<body>
<div class="header">
	<h1>Consulta de CNPJ</h1>
</div>
<div class="w3-main">
	<% if (mensagem != null) { %>
		<div class="alert <%=tipoMensagem%>">
		 <i class="fa fa-exclamation-triangle" aria-hidden="true"></i> <%=mensagem %>
		</div>
	<%} %>
	<!-- Main -->
	<div class="about-bottom main-agile book-form">

		<form action="/consultaCNPJ" method="post">
			<div class="form-date-w3-agileits">
				<div style="display:<%=showCaptcha%>;">
					<div class="make wow shake">
						<img src="captcha.png" class="img-responsive center-block" alt="Captcha">
					</div>
					<label>Imagem</label>
					<input type="text" name="captcha" placeholder="Inserir a Captcha">
				</div>
				<div>
					<label>CNPJ</label>
					<input type="text" name="cnpj" placeholder="Inserir o CNPJ" required="required" value="<%=cnpj != null ? cnpj : ""%>">
				</div>
			</div>
			<div class="make wow shake">
				  <input type="submit" value="Enviar">
			</div>
		</form>
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