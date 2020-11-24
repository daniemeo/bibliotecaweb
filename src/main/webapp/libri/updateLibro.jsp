<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it">
<head>
<jsp:include page="/header.jsp" />
<title>modifica libro</title>

<!-- style per le pagine diverse dalla index -->
<link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
  <script type="text/javascript">
    
    $(document).ready(function() {
    	$("form").submit(function( event ) {
    		
			$("#errorTitolo").hide();
			$("#errorGenere").hide();
			$("#errorTrama").hide();
			$("#errorAutore").hide();
			var controlli = true;
			if(!$("#titolo")[0].value) {
				$("#errorTitolo").show();
				controlli= false;
			}
			if(!$("#listaGeneri")[0].value){
				$("#errorGenere").show();
				controlli= false;
			}
			if(!$("#trama")[0].value){
				$("#errorTrama").show();
				controlli= false;
			}
			if(!$("#listaAutori")[0].value){
				$("#errorAutore").show();
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
	   <c:forEach items= "${requestScope.errorMessage}" var= "errore">
		 <div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}"role="alert">
			${errore}
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		</c:forEach>

		<div class='card'>
			<div class='card-header'>
				<h5>Modifica libro</h5>
			</div>
			<div class='card-body'>
				<form method="post" action="${pageContext.request.contextPath}/libri/ExecuteUpdateLibroServlet" novalidate="novalidate">
					<div class="form-row">
						<input type="hidden" name="libroUpdate" id="libroUpdate" value="${requestScope.libroUpdate.id}">
						<div class="form-group col-md-6">
							<label>Titolo <span class="text-danger">*</span></label> 
							<input type="text" name="titolo" id="titolo" class="form-control" value="${requestScope.libroUpdate.titolo}">
							<div class="invalid-feedback" id= "errorTitolo">
                                   Attenzione! Devi inserire il titolo del libro!
                            </div>
						</div>
						
						<div class="form-group col-md-6">
							<label>Genere</label> 
							<select id="listaGeneri" name="genere" class="custom-select browser-default" value="${libro.genere}">
								<option value="${NULL}">- Seleziona Genere -</option>
								<c:forEach items="${listaGeneri}" var="genere">
									<c:if test="${genere != 'NULL' }">
										<option value="${genere}" ${genere == genereSelezionato ? 'selected="selected"' : '' }><c:out value="${genere}" /></option>
									</c:if>
								</c:forEach>
							</select>
							 <div class="invalid-feedback" id= "errorGenere">
                                 Attenzione! Devi inserire il genere del libro!
                             </div>
						</div>
						
						<div class="form-group col-md-6">
							<label>Trama <span class="text-danger">*</span></label>
							<input type="text" name="trama" id="trama" class="form-control" value="${requestScope.libroUpdate.trama}">
							<div class="invalid-feedback" id= "errorTrama">
                                   Attenzione! Devi inserire la trama del libro!!
                            </div>
						</div>
						<div class="form-group col-md-6">
							<label>Autore</label> 
							<select id="listaAutori" name="autore_id" class="custom-select browser-default">
								<option value="">- Seleziona Autori -</option>
								<c:forEach items="${requestScope.listaAutori}" var="autore">
									<option value="${autore.id}"${autore.id == autoreSelezionato ? 'selected="selected"' : '' }>
										<c:out value="${autore.nome}" />&nbsp; <c:out value="${autore.cognome}" />
									</option>
								</c:forEach>
							</select>
							  <div class="invalid-feedback" id= "errorAutore">
                               		 Attenzione! Devi inserire l'autore del libro!
                           	  </div>
						</div>
					</div>
					<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
				</form>
			</div>
			<div class='card-footer'>
				<a href="formCercaLibro.jsp" class='btn btn-outline-secondary'
					style='width: 80px'> <i class='fa fa-chevron-left'></i> Back
				</a>
			</div>
			<!-- end card-body -->
		</div>
		<!-- end container -->
	</main>
	<jsp:include page="/footer.jsp" />

</body>
</html>