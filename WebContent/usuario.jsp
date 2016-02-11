<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Coletor Twitter</title>
  <link rel="stylesheet" href="resources/css/layoutUsuario.css">
<title>Gerenciamento do Usuario</title>
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
			<center><h1 style="font-family: Times New Roman">Informações do Stream</h1></center>
			<br><br>
			
			<font style="font-family: tahoma">
			&nbsp;&nbsp;<b>Status:</b>
			<% if(request.getAttribute("status")!=null){%>
				<% if(request.getAttribute("status").equals("ATIVO")){%>
					<b><font color="green"><%=request.getAttribute("status")%></font></b>
				<%} %>
				
				<% if(request.getAttribute("status").equals("INATIVO")){%>
					<b><font color="red"><%=request.getAttribute("status")%></font></b>
				<%} %>
			<%} %>
			<br><br>
			</font>
			
			&nbsp;&nbsp;<b>Neste status desde:</b>
			<% if(request.getAttribute("data")!=null){%>
				<% if(request.getAttribute("status").equals("ATIVO")){%>
					<b><font color="green"><%=request.getAttribute("data")%></font></b>
				<%} %>
				
				<% if(request.getAttribute("status").equals("INATIVO")){%>
					<b><font color="red"><%=request.getAttribute("data")%></font></b>
				<%} %>
			<%} %>
			
			<br><br>
			&nbsp;&nbsp;<b>Iniciar/Parar:</b><br><br>
			&nbsp;&nbsp;<a href="/Tcc/StreamTweetServlet?param=start"><img src="imagens/startbutton.jpg" width="50" height="50"></a>
			&nbsp;&nbsp;<a href="/Tcc/StreamTweetServlet?param=stop"><img src="imagens/stopbutton.png" width="50" height="50"></a><br><br>
			
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