<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it">
<head>
	<jsp:include page="./header.jsp" />
	<title>modifica autore</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="./navbar.jsp" />
	
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
                    
                     
					
					
					

					<form method="post" action="ExecuteUpdateAutoreServlet" novalidate="novalidate">
					<div class="form-row">
					<input type="hidden" name="autoreUpdate" id="autoreUpdate" value="${requestScope.autoreUpdate.id}" >
						
						
							<div class="form-group col-md-6">
								<label>Nome <span class="text-danger"></span></label>
								<input type="text" name="nome" id="nome" class="form-control" value="${requestScope.autoreUpdate.nome}" >
							</div>
							
							
							<div class="form-group col-md-6">
								<label>Cognome <span class="text-danger"></span></label>
								<input type="text" name="cognome"  id="cognome" class="form-control" value="${requestScope.autoreUpdate.cognome}" >
							</div>
						</div>
						
						<div class="form-row">	
							<div class="form-group col-md-3">
								<label>Data di nascita (yyyy-mm-dd)<span class="text-danger"></span></label>
								<input type="datetime" class="form-control" name="dataDiNascita" id="dataDiNascita" value="${requestScope.autoreUpdate.dataNascita}" >
							</div>
							
						</div>
						<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
					

					</form>

		    
		    
			<!-- end card-body -->			   
		    </div>
		</div>	
	
	
	<!-- end container -->	
	</main>
	<jsp:include page="./footer.jsp" />
	
</body>
</html>