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
			<div class='card-header'>Risultati ricerca autore</div>
			<div class='card-body'>
			  <c:forEach items="${sessionScope.utente.ruoli}" var="ruoli">
					<c:if test="${ruoli.codice == 'ADMIN_ROLE' || ruoli.codice == 'CLASSIC_ROLE' }">
						<a class="btn btn-primary " href="${pageContext.request.contextPath}/autori/PrepareInsertAutore">Add New</a>
				    </c:if> 
			  </c:forEach>	
			</div>
			<div class='table-responsive'>
				<table class='table table-striped '>
					<thead>
						<tr>

							<th>Nome</th>
							<th>Cognome</th>
							<th>Data di Nascita</th>


						</tr>
					</thead>
					<tbody>

						<c:forEach items="${requestScope.listaAutori}" var="autore">
							<tr>
								<td><c:out value="${autore.nome}"></c:out></td>
								<td><c:out value="${autore.cognome}"></c:out></td>
								<td><c:out value="${autore.dataNascita}"></c:out></td>
								
								<td><a class="btn  btn-sm btn-outline-secondary" href="${pageContext.request.contextPath}/autori/VisualizzaAutoreServlet?idDaInviareComeParametro=${autore.id}"> Visualizza</a> 
								  <c:forEach items="${sessionScope.utente.ruoli}" var="ruoli">
					              		<c:if test="${ruoli.codice == 'ADMIN_ROLE' || ruoli.codice == 'CLASSIC_ROLE' }">
											<a class="btn  btn-sm btn-outline-primary ml-2 mr-2" href="${pageContext.request.contextPath}/autori/PrepareUpdateAutoreSerlvlet?idDaInviareExecuteUpdate=${autore.id}">Edit</a>
                                    	</c:if> 
			   					  </c:forEach>	
  								  <c:forEach items="${sessionScope.utente.ruoli}" var="ruoli">
					              		<c:if test="${ruoli.codice == 'ADMIN_ROLE' || ruoli.codice == 'CLASSIC_ROLE' }">
											<a class="btn btn-outline-danger btn-sm" href="${pageContext.request.contextPath}/autori/PrepareDeleteAutoreServlet?idDaInviareComeParametro=${autore.id}">Delete</a>
										</c:if> 
			   					  </c:forEach>	
								</td>




							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class='card-footer'>
		        <a href="formCercaAutore.jsp"
		         class='btn btn-outline-secondary' style='width:80px'>
		            <i class='fa fa-chevron-left'></i> Back
		        </a>
		    </div>
				

			</div>
		</div>
	</main>
	<jsp:include page="/footer.jsp" />
</body>
</html>