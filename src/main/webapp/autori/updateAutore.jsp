<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it">
<head>
<jsp:include page="/header.jsp" />
<title>Modifica Autore</title>

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
			if(!$("#dataDiNascita")[0].value){
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

		<div class="alert alert-danger alert-dismissible fade show d-none"
			role="alert">
			Operazione fallita!
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<c:forEach items="${errori }" var="errore">
			<div
				class="alert alert-danger alert-dismissible fade show ${errore==null?'d-none': ''}"
				role="alert">
				${errore}
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
		
		</c:forEach>

		<div class='card'>
			<div class='card-header'>
				<h5>Modifica Autore</h5>
			</div>
			<div class='card-body'>

				<form method="post"
					action="${pageContext.request.contextPath}/autori/ExecuteUpdateAutoreServlet"
					novalidate="novalidate">
					<div class="form-row">
						<input type="hidden" name="autoreUpdate" id="autoreUpdate"
							value="${requestScope.autoreUpdate.id}">


						<div class="form-group col-md-6">
							<label>Nome <span class="text-danger"></span></label>
							 <input type="text" name="nome" id="nome" class="form-control" value="${requestScope.autoreUpdate.nome}">
				              <div class="invalid-feedback" id= "errorNome">
                                   Attenzione! Devi inserire il nome dell'autore!!
                              </div>
				       </div>


						<div class="form-group col-md-6">
							<label>Cognome <span class="text-danger"></span></label>
							 <input type="text" name="cognome" id="cognome" class="form-control" value="${requestScope.autoreUpdate.cognome}">
							 <div class="invalid-feedback" id= "errorCognome">
                                   Attenzione! Devi inserire il cognome dell'autore!!
                              </div>
						</div>
					</div>

					<div class="form-row">
						<div class="form-group col-md-3">
							<label>Data di nascita<span
								class="text-danger"></span></label> 
								<input type="date" class="form-control" name="dataDiNascita" id="dataDiNascita" value="${requestScope.autoreUpdate.dataNascita}">
								<div class="invalid-feedback" id= "errorData">
                                   Attenzione! Devi inserire la data di nascita dell'autore!!
                              </div>
						</div>

					</div>
					<button type="submit" name="submit" value="submit" id="submit"
						class="btn btn-primary">Conferma</button>


				</form>

			</div>
			<div class='card-footer'>
				<a href="resultsCercaPerAutore.jsp"
					class='btn btn-outline-secondary' style='width: 80px'> <i
					class='fa fa-chevron-left'></i> Back
				</a>
			</div>

			<!-- end card-body -->
		</div>



		<!-- end container -->
	</main>

	<jsp:include page="/footer.jsp" />
</body>

</html>