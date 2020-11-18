<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="it">
<head>
	<jsp:include page="./header.jsp" />
	<title>Visualizza libro</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="./navbar.jsp" />
	
	<main role="main" class="container">
		
		<div class='card'>
		    <div class='card-header'>
		        Visualizza Libro
		    </div>
		    
		
		    <div class='card-body'>
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Id Libro:</dt>
				  <dd class="col-sm-9"><c:out value="${requestScope.libroPerListaLibro.id}"></c:out></dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Titolo:</dt>
				  <dd class="col-sm-9"><c:out value="${requestScope.libroPerListaLibro.titolo}"></c:out></dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Genere</dt>
				  <dd class="col-sm-9"><c:out value="${requestScope.libroPerListaLibro.genere}"></c:out></dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Autore</dt>
				  <dd class="col-sm-9"><c:out value="${requestScope.libroPerListaLibro.autore.id}"></c:out></dd>
		    	</dl>
		    
		    	
		    	
		    	
		    </div>
		    
		    <div class='card-footer'>
		        <a href="${pageContext.request.contextPath}/PrepareCercaLibroServlet"
		         class='btn btn-outline-secondary' style='width:80px'>
		            <i class='fa fa-chevron-left'></i> Back
		        </a>
		    </div>
		</div>	