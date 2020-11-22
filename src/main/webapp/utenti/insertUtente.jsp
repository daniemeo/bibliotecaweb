<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it">
<head>
	<jsp:include page="/header.jsp" />
	<title>Inserisci nuovo utente </title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
    
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
		<c:forEach items= "${requestScope.errorMessage}" var= "errore">
		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
		  ${errore}
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		</c:forEach>
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Inserisci nuovo Utente</h5> 
		    </div>
		    <div class='card-body'>

					<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>

					<form method="post" action="${pageContext.request.contextPath}/utenti/ExecuteInsertUtenteServlet" class= "needs-validation" novalidate>
					
						<div class="form-row">
						    <div class="form-group col-md-6">
								<label>Nome <span class="text-danger">*</span></label>
								<input type="text" name="nome" id="nome" class="form-control" placeholder="Inserire il nome" value="${requestScope.utenteInsert.nome}"required>
								<div class="invalid-feedback">
                                   Attenzione! Devi inserire il nome dell'utente!
                                 </div>
							</div>
							
							<div class="form-group col-md-6">
								<label>Cognome <span class="text-danger">*</span></label>
								<input type="text" name="cognome" id="cognome" class="form-control" placeholder="Inserire il cognome" value="${requestScope.utenteInsert.cognome}"required>
								<div class="invalid-feedback">
                                   Attenzione! Devi inserire il cognome dell'utente!!
                                 </div>
							</div>
							
							<div class="form-group col-md-6">
								<label>Username<span class="text-danger">*</span></label>
								<input type="text" name="username" id="username" class="form-control" placeholder="Inserire Username" value="${requestScope.utenteInsert.username}"required>
								<div class="invalid-feedback">
                                   Attenzione! 
                                 </div>
							</div>
							<div class="form-group col-md-6">
								<label>Password<span class="text-danger">*</span></label>
								<input type="text" name="password" id="password" class="form-control" placeholder="Inserire Password" value="${requestScope.utenteInsert.password}"required>
								<div class="invalid-feedback">
                                   Attenzione! 
                                 </div>
							</div>
							
							
							 <div class="custom-control custom-checkbox">
								   
								    <c:forEach items="${requestScope.listaRuoli}" var="ruolo" >
							    		<input type="checkbox" class="" id="ruolo${ruolo.id}" name="ruoliSelezionati" value="${ruolo.id}"
								    		<c:forEach items="${utenteInsert.ruoli}" var="ruoloUtente" >
								    		 	${ruolo.id == ruoloUtente.id ? 'checked="checked"' : '' }
								    		</c:forEach>
							    		/>
								    	${ruolo.codice}
								    	<br>
								   </c:forEach>
							 </div>
						</div>
						<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
					</form>
					
					</div>
			</div>
				
					 
	
	
		
	
	
	<!-- end container -->	
	</main>
	<jsp:include page="/footer.jsp" />

</body>
</html>