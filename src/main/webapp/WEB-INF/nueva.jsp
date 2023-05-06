<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nueva Pregunta</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h1>Nueva Pregunta</h1>
		<!-- modelAttribute crea un objeto vacío de pregunta
		Y con los campos del usuario se llenan los atributos 
		Obj Pregunta.setContenido("USUARIO INGRESA")
		 -->
		<form:form action="/crear" method="post" modelAttribute="pregunta">
			<div>
				<form:label path="contenido">Pregunta</form:label>
				<form:input path="contenido" class="form-control"/>
				<form:errors path="*" class="text-danger"/>
			</div>
			<div>
				<label>Etiquetas</label>
				<input type="text" name="textoEtiquetas" class="form-control" />
			</div>
			<div class="mt-3">
				<input type="submit" value="Guardar" class="btn btn-success" />
			</div>
		</form:form>
	</div>
</body>
</html>