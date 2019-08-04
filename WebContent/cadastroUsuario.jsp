<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de Usuário</title>
</head>
<body>
	<h1>Cadastro de Usuário</h1>
	<form action="ServletUsuario" method="post">
		<input type="hidden" name="id" value="${user.id}"/>
		<input type="text" name="login" value="${user.login}">
		<br />
		<input type="password" name="password" value="${user.senha}">
		<br />
		<input type="submit" value="Salvar">
	</form>
	<table border="1">
		<tr>
			<td>USUÁRIO</td>
			<td>SENHA</td>
			<td>AÇÃO</td>
		</tr>
		<c:forEach items="${usuarios}" var="user">
		<tr>
			<td><c:out value="${user.login}" /></td>
			<td><c:out value="${user.senha}"/></td>
			<!-- Envia a acao para o Servlet e de la para o DAO -->
			<td>
				<a href="ServletUsuario?acao=delete&user=${user.login}">Deletar</a>
				|
				<a href="ServletUsuario?acao=editar&user=${user.login}">Editar</a>
			</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>