<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it">
<head>
	<jsp:include page="/header.jsp" />
	<title>Inserisci nuovo</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
    <script type="text/javascript">
    
    $(document).ready(function() {
    	$("form").submit(function( event ) {
			$("#errorNome").hide();
			$("#errorCognome").hide();
			$("#errorData").hide();
			var controlli = true;
			if(!$("#nome")[0].value) {
				$("#errorNome").show();
				controlli= false;
			}
			if(!$("#cognome")[0].value){
				$("#errorCognome").show();
				controlli= false;
			}
			if(!$("#dataNascita")[0].value){
				$("#errorData").show();
				controlli= false;
			}
			
			if(!controlli) {
				event.preventDefault();
			} 
		});
    }) 
		

</script>
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
		        <h5>Inserisci nuovo Autore</h5> 
		    </div>
		    
		    <div class='card-body'>

					<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>

					<form method="post" id= "formAutore" action="${pageContext.request.contextPath}/autori/ExecuteInsertAutoreServlet" class= "needs-validation" novalidate>
					
						<div class="form-row">
							<div class="form-group col-md-6">
								<label>Nome <span class="text-danger">*</span></label>
								<input type="text" name="nome" id="nome" class="form-control" placeholder="Inserire il nome"  value="${requestScope.autoreInsert.nome}" required>
								<div class="invalid-feedback" id= "errorNome">
                                   Attenzione! Devi inserire il nome dell'autore!
                                 </div>
							</div>
							
							<div class="form-group col-md-6">
								<label>Cognome <span class="text-danger">*</span></label>
								<input type="text" name="cognome" id="cognome" class="form-control" placeholder="Inserire il cognome" value="${requestScope.autoreInsert.cognome}" required>
								<div class="invalid-feedback" id = "errorCognome">
                                   Attenzione! Devi inserire il cognome dell'autore!!
                                 </div>
							</div>
						</div>
						
						<div class="form-row">	
							<div class="form-group col-md-3">
								<label>Data di nascita <span class="text-danger">*</span></label>
								<input type="date" class="form-control" name="dataNascita" id="dataNascita" placeholder="Inserire la data di nascita" value="${requestScope.autoreInsert.dataNascita}" required>
								<div class="invalid-feedback" id = "errorData">
                                   Attenzione! Devi la data di nascita dell'autore!!
                                 </div>
							</div>
							
						</div>
						
                   
						
						<button type="submit" class="btn btn-primary">Conferma</button>
					</form>
					
					
					</div>
					<div class='card-footer'>
					<a href="resultsCercaPerAutore.jsp"
						class='btn btn-outline-secondary' style='width: 80px'> <i
						class='fa fa-chevron-left'></i> Back
					</a>
				</div>
			</div>
				
					 
	
	
		
	
	
	<!-- end container -->	
	</main>
	<jsp:include page="/footer.jsp" />

</body>
</html>