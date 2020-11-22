<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it">
<head>
	<jsp:include page="/header.jsp" />
	<title>Visualizza libro</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="/navbar.jsp" />
	
	<main role="main" class="container">
		
		<div class='card'>
		    <div class='card-header'>
		        Visualizza Libro
		    </div>
		    
		
		    <div class='card-body'>
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Id Autore:</dt>
				  <dd class="col-sm-9"><c:out value="${requestScope.autorePerListaAutore.id}"></c:out></dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Nome:</dt>
				  <dd class="col-sm-9"><c:out value="${requestScope.autorePerListaAutore.nome}"></c:out></dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Cognome</dt>
				  <dd class="col-sm-9"><c:out value="${requestScope.autorePerListaAutore.cognome}"></c:out></dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Data di nascita</dt>
				  <dd class="col-sm-9"><c:out value="${requestScope.autorePerListaAutore.dataNascita}"></c:out></dd>
		    	</dl>
		    
		    	
		    	
		    	
		    </div>
		    
		    <div class='card-footer'>
		        <a href="resultsCercaPerAutore.jsp"
		         class='btn btn-outline-secondary' style='width:80px'>
		            <i class='fa fa-chevron-left'></i> Back
		        </a>
		    </div>
		</div>	
	</main>
	

</body>

</html>