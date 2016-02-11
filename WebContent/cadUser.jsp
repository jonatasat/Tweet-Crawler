<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Usuario</title>
</head>
<body>
<form action="CriaUsuarioServlet" method="post">
Login: <input type="text" name="login" size=20><br>
Senha: <input type="password" name="senha" size=20><br>
<input TYPE="submit" name="param" value="Novo"><br>
<input TYPE="submit" name="param" value="Cancelar">
</form>
</body>
</html>