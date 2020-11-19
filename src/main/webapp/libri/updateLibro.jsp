<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it">
<head>
	<jsp:include page="/header.jsp" />
	<title>modifica libro</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="/navbar.jsp" />
	
	<main role="main" class="container">
	
		<div class="alert alert-danger alert-dismissible fade show d-none" role="alert">
			 	Operazione fallita!
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
			    <span aria-hidden="true">&times;</span>
			  </button>
		</div>
		
		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
		  ${errorMessage}
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>modifica l'elemento</h5> 
		    </div>
		    <div class='card-body'>
                    
                     
					
					
					

					<form method="post" action="libri/ExecuteUpdateLibroServlet" novalidate="novalidate">
					<div class="form-row">
					<input type="hidden" name="libroUpdate" id="libroUpdate" value="${requestScope.libroUpdate.id}" >
						
						
							<div class="form-group col-md-6">
								<label>Titolo <span class="text-danger"></span></label>
								<input type="text" name="titolo" id="titolo" class="form-control" value="${requestScope.libroUpdate.titolo}" >
							</div>
							
							
							<div class="form-group col-md-6">
								<label>Genere <span class="text-danger"></span></label>
								<input type="text" name="genere"  id="genere" class="form-control" value="${requestScope.libroUpdate.genere}" >
							</div>
							
							<div class="form-group col-md-6">
								<label>Trama <span class="text-danger"></span></label>
								<input type="text" name="trama"  id="trama" class="form-control" value="${requestScope.libroUpdate.trama}" >
							</div>
						
						
						<div class="form-group col-md-6">
	                          <label>Autore</label>
	                             <select id="listaAutori" name="autore_id" class="custom-select browser-default" >	
				                   <option value="">- Seleziona Autori -</option>
					               <c:forEach items="${requestScope.listaAutori}" var="autore">
						             <option value="${autore.id}"><c:out value="${autore.nome}"/>&nbsp;<c:out value="${autore.cognome}"/></option>
					               </c:forEach>
				                 </select>
	                          </div>
	                    </div>
					 	
					
					
						
						<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
					 </form>	

					

		    
		    
			<!-- end card-body -->			   
		    </div>
		
	
	
	<!-- end container -->	
	</main>
	<jsp:include page="/footer.jsp" />
	
</body>
</html>