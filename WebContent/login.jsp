<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Login</title>
<link rel="stylesheet" href="resources/css/loginstyle.css">
</head>
<body>
<img src="imagens/fundo-gradiente.jpg" id="imgfundo">
	<div id="central">
		<center>
		<img src="imagens/logotwitter.png" width="150" height="150">
		<br><br>
			<form name="LoginForm" target="_parent" method="post"
				action="LoginServlet">
				<table cellpadding="0" cellspacing="0">
					<tr>
						<td colspan="2" class="acesso">
							<center><h3>Coletor Twitter</h3></center>
						</td>
					</tr>
					<tr>
						<td class="rotulo_login"><p>Login:&nbsp;</p></td>
						<td><input class="input_login" style="width: 206px" name="login" type="text" value="" /></td>
					</tr>
					<tr>
						<td class="rotulo_login"><p>Senha:&nbsp;</p></td>
						<td><input class="input_senha" style="width: 206px" type="password" name="senha" value="" /></td>
					</tr>
					
					<tr>
						<td><input class="botao" type="image" id="imglogin" src="imagens/loginbutton.jpg" width="90" height="35" value="Entrar"><td>
					</tr>
					
					<tr>
					
						<td colspan="2">
						<center>
						<%if(request.getAttribute("msg")!=null){%>
							<% String usuario = (String) request.getAttribute("msg");%>
							<font color=red><%=usuario%></font>
						<%} %>
						</center>
						</td>
					</tr>
				</table>
			</form>
			</center>
		</div>
	
<body>
</html>
