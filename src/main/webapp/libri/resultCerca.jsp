<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<c:if test="${sessionScope.utente == null }">
	<c:redirect url="index.jsp" />
</c:if>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="/header.jsp" />
<title>Pagina dei risultati</title>

<!-- style per le pagine diverse dalla index -->
<link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">

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
			<div class='card-header'>Risultati ricerca libro</div>
			<div class='card-body'>
				<a class="btn btn-primary "
					href="${pageContext.request.contextPath}/libri/PrepareInsertLibroServlet">Add New</a>
			</div>



			<div class='table-responsive'>
				<table class='table table-striped '>
					<thead>
						<tr>

							<th>Titolo</th>
							<th>Trama</th>
							<th>Genere</th>
							<th>Autore</th>

						</tr>
					</thead>
					<tbody>

						<c:forEach items="${requestScope.listaLibri}" var="libro">
							<tr>
								<td><c:out value="${libro.titolo}"></c:out></td>
								<td><c:out value="${libro.trama}"></c:out></td>
								<td><c:out value="${libro.genere}"></c:out></td>
								<td><c:out value="${libro.autore.nome}"></c:out></td>
								<td><a class="btn  btn-sm btn-outline-secondary"
									href="${pageContext.request.contextPath}/libri/VisualizzaLibroServlet?idDaInviareComeParametro=${libro.id}">
										Visualizza</a> <a
									class="btn  btn-sm btn-outline-primary ml-2 mr-2"
									href="${pageContext.request.contextPath}/libri/PrepareUpdateLibroServlet?idDaInviareExecuteUpdate=${libro.id}">Edit</a>


									<a class="btn btn-outline-danger btn-sm"
									href="${pageContext.request.contextPath}/libri/PrepareDeleteLibroServlet?idDaInviareComeParametro=${libro.id}">Delete</a>

								</td>



							</tr>
						</c:forEach>
					</tbody>
				</table>
				</div>
			</div>
	  
		
	</main>	
<jsp:include page="/footer.jsp" />
</body>
</html>