<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="/header.jsp" />
<meta charset="ISO-8859-1">
<title>Cerca Libro</title>
<link href="${pageContext.request.contextPath}/assets/css/global.css"
	rel="stylesheet">
</head>
<body>
	<jsp:include page="/navbar.jsp" />

	<main role="main" class="container">

		<div
			class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}"
			role="alert">
			${errorMessage}
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<div
			class="alert alert-success alert-dismissible fade show ${successMessage==null?'d-none': ''}"
			role="alert">
			${successMessage}
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<div class="alert alert-danger alert-dismissible fade show d-none"
			role="alert">
			Esempio di operazione fallita!
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<div class="alert alert-info alert-dismissible fade show d-none"
			role="alert">
			Aggiungere d-none nelle class per non far apparire
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>

         <div class='card'>
		    <div class='card-header'>
		        <h5>Cerca libro</h5> 
		    </div>

		<!-- Main jumbotron for a primary marketing message or call to action -->

		<div class='card-body'>
			<form
				action="${pageContext.request.contextPath}/libri/ExecuteCercaLibroServlet"
				method="post">
				<div class="form-row">
					<div class="form-group col-md-6">
						<label>Titolo </label> <input type="text" name="titolo">
					</div>
					<div class="form-group col-md-6">
						<label>Trama</label> <input type="text" name="trama">
					</div>
					<div class="form-group col-md-6">
						<label>Genere</label> <input type="text" name="genere">
					</div>
					<div class="form-group col-md-6">
						<label>Autore</label> <select id="listaAutori" name="autore_id"
							class="custom-select browser-default">
							<option value="">- Seleziona Autori -</option>
							<c:forEach items="${requestScope.listaAutori}" var="autore">
								<option value="${autore.id}"><c:out
										value="${autore.nome}" />&nbsp;
									<c:out value="${autore.cognome}" /></option>
							</c:forEach>
						</select>
					</div>
				</div>
				<button type="submit" name="submit" value="submit" id="submit"
					class="btn btn-primary">Cerca</button>


			</form>
			</div>
		</div>





	</main>
</body>
</html>