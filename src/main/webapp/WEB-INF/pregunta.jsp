<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pregunta</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h1>${pregunta.contenido}</h1>
		<div>
			<c:forEach items="${pregunta.etiquetas}" var="etiqueta">
				<span class="badge bg-secondary">${etiqueta.tema}</span>
			</c:forEach>
		</div>
		<div class="row">
			<div class="col-6">
				<table class="table table-hover">
					<thead>
						<tr>
							<td>Respuestas</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pregunta.respuestas}" var="respuesta">
							<tr>
								<td>${respuesta.texto}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="col-6">
				<form:form action="/respuesta" method="post" modelAttribute="respuesta">
					<form:label path="texto">Ingresa tu respuesta</form:label>
					<form:textarea path="texto" class="form-control" />
					<form:errors path="texto" class="text-danger" />
					<form:hidden path="pregunta" value="${pregunta.id}" />
					<input type="submit" class="btn btn-primary" value="Guardar Respuesta" >
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>