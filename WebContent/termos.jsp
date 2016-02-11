<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Coletor Twitter</title>
  <link rel="stylesheet" href="resources/css/layoutUsuario.css">
<title>Termos</title>
</head>
<body bgcolor=white>
<img src="imagens/engrenagem.png" width="200" height="150" id="imgsettings">
	<div id="topo">
	<br><br>
        <div id="menu">
			<ul>
			<li><a href="/Tcc/InicioServlet" >Inicio</a></li>
			<li><a href="/Tcc/PalavrasChaveServlet?p=a" >Termos</a></li>
			<li><a href="/Tcc/HistoricoServlet" >Historico</a></li>
			<li><a href="/Tcc/entidades.jsp" >Entidades</a></li>
			</ul>
		</div>
    </div>	
	<div id="conteudo" >
		<center><h1 style="font-family: Times New Roman">Cadastro de Termos</h1></center><br><br>
		
				Cadastro de Termos:
			<form action="PalavrasChaveServlet" method="post">
			<input type="text" name="palavraChave" size="20">
			<input type="submit" name="param" value="Salvar">
			</form><br>
			Obs: Para validar é necessário reiniciar o servidor Stream.<br><br>
			<font color="red">
			<% if(request.getAttribute("msg2")!=null){%>
			<%=request.getAttribute("msg2")%>
			<%} %>
			</font>
			<br><br><br>
			
			<center>
			<table border="1" cellpadding="2" cellspacing="2">
				  <tr>
				   <th colspan="2">Termos Cadastrados no Sistema</th>
				  </tr>
				  <tr bgcolor="white">
				   <th>Termos</th>
				   <th>-</th>
				  </tr>
				  <c:forEach var="palavras" items='<%=request.getAttribute("list")%>'>
				   <tr>
				    <td>${palavras.palavra}</td>
				    <td><center><a href="/Tcc/PalavrasChaveServlet?p=remover&palavra=${palavras.palavra}" onclick="return confirm('Deseja realmente remover?')">
				    <u><img src="imagens/deletered.png" width="20" height="20"></u></a></center></td>
				   </tr>
				  </c:forEach>
				 </table>
			</center>
			
		</div>
       
       <div id="boas_vindas">
           <br> Bem Vindo ${sessionScope.login} ! <br>
       </div>
       
       
       <div id="sair">
		<script LANGUAGE="JavaScript" TYPE="text/javascript">
		function myFunction() {
   			alert("Logout efetuado com sucesso!");
		}
		</script>
		<br><a href="/Tcc/logout.jsp" onclick="myFunction()"> <img src="imagens/logout.png" width="40" height="40"></a>
	</div>
</body>
</html>