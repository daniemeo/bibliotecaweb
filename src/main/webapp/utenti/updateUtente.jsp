<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it">
<head>
	<jsp:include page="/header.jsp" />
	<title>Modifica Utente </title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
     <script type="text/javascript">
    
    $(document).ready(function() {
    	$("form").submit(function( event ) {
    		
			$("#errorNome").hide();
			$("#errorCognome").hide();
			$("#errorUsername").hide();
			$("#errorStato").hide();
			$("#errorRuoli").hide();
			
			var controlli = true;
			if(!$("#nome")[0].value) {
				$("#errorNome").show();
				controlli= false;
			}
			if(!$("#cognome")[0].value){
				$("#errorCognome").show();
				controlli= false;
			}
			if(!$("#username")[0].value){
				$("#errorUsername").show();
				controlli= false;
			}
			if(!$("#listaStati")[0].value){
				$("#errorStato").show();
				controlli= false;
			}
			
			if(!$("input[type='checkbox']").is(":checked")){
				$("#errorRuoli").show();
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
		        <h5>Modifica Utente</h5> 
		    </div>
		    <div class='card-body'>

					<form method="post" action="${pageContext.request.contextPath}/utenti/ExecuteUpdateUtenteServlet" class= "needs-validation" novalidate>
					   <div class="form-row">
					    <input type="hidden" name="utente" id="utente" value="${requestScope.utente.id}" >
						
						<div class="form-row">
						    <div class="form-group col-md-6">
								<label>Nome <span class="text-danger"></span></label>
								<input type="text" name="nome" id="nome" class="form-control" placeholder="Inserire il nome" value="${utente.nome}"required>
								<div class="invalid-feedback" id= "errorNome">
                                   Attenzione! Devi inserire il nome dell'utente!
                                 </div>
							</div>
							
							<div class="form-group col-md-6">
								<label>Cognome </label>
								<input type="text" name="cognome" id="cognome" class="form-control" placeholder="Inserire il cognome"  value="${utente.cognome}">
								<div class="invalid-feedback" id= "errorCognome">
                                   Attenzione! Devi inserire il cognome dell'utente!
                                 </div>
							</div>
							
							<div class="form-group col-md-6">
								<label>Username</label>
								<input type="text" name="username" id="username" class="form-control" placeholder="Inserire Username"  value="${utente.username}">
								<div class="invalid-feedback" id= "errorUsername">
                                   Attenzione! Devi inserire lo username dell'utente!
                                 </div>
							</div>
							
							<div class="form-group col-md-6">
	                          <label>Stato</label>
	                             <select id="listaStati" name="stato" class="form-control" value="${utente.stato}">	
				                   <option value="${NULL}">- Seleziona Stato -</option>
					               <c:forEach items="${listaStati}" var="stato">
					                <c:if test="${stato != 'NULL' }">
						             <option value="${stato}"  ${stato == utente.stato ? 'selected="selected"' : '' }><c:out value="${stato}"/></option>
						            </c:if>
					               </c:forEach>
				                 </select>
				                 <div class="invalid-feedback" id= "errorStato">
                                   Attenzione! Devi inserire lo stato dell'utente!
                                 </div>
	                          </div>
	                        
							
							
							 <div class="custom-control custom-checkbox">
								   
								   <c:forEach items="${requestScope.listaRuoli}" var="ruolo" >
							    		<input type="checkbox" class="" id="ruolo${ruolo.id}" name="ruoliSelezionati" value="${ruolo.id}"
							    		<c:forEach items="${utente.ruoli}" var="ruoloUtente" >
							    		 	${ruolo.id == ruoloUtente.id ? 'checked="checked"' : '' }
							    		</c:forEach>
							    		
							    		
							    		/>
								    	${ruolo.codice}
								    	<br>
								   </c:forEach>
								   <div class="invalid-feedback" id= "errorRuoli">
                                   Attenzione devi inserire i ruoli! 
                                 </div>
							 </div>
							</div>
						</div>
						
						<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
					
					</form>
					
					
				</div>
				<div class='card-footer'>
					<a href="resultsCercaPerUtente.jsp"
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