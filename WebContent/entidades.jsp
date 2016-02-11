<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Coletor Twitter</title>
  <link rel="stylesheet" href="resources/css/layoutUsuario.css">
<title>Entidades</title>
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
		<center><h1 style="font-family: Times New Roman">Carregar Entidades</h1></center><br><br>
		
		<% if(request.getAttribute("msg2")!=null){%>
			<b><font color="red"><%=request.getAttribute("msg2")%></font></b>
		<%} %>
		
		
		
		<br><br>
		
		<form method="post" action="UploadServlet" enctype="multipart/form-data">  
		    
		        <b>Selecione o Arquivo:</b><br>
		        
		        <input type="file" name="arquivo">&nbsp;&nbsp;&nbsp;
		        <input type="image" src="imagens/upload.jpg" width="30" height="30" value="Envia"> 
		        
		 
   		</form>  
		<br><br>
		<div id="texto">
		<b>O arquivo deverá estar estruturado da seguinte forma:</b><br><br>
		
		[tipo_entidade]<br>
		nome_entidade<br>
		nome_entidade<br>
		...<br>
		[tipo_entidade]<br>
		nome_entidade<br>
		...<br>
		</div>
		<br><br>
		
		
		
		
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