<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it">
<head>
	<jsp:include page="/header.jsp" />
	<title>Inserisci nuovo libro</title>
	
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
		        <h5>Inserisci nuovo Libro</h5> 
		    </div>
		    <div class='card-body'>

					<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>

					<form method="post" action="${pageContext.request.contextPath}/libri/ExecuteInsertLibroServlet" class= "needs-validation" novalidate>
					
						<div class="form-row">
						    <div class="form-group col-md-6">
								<label>Titolo <span class="text-danger">*</span></label>
								<input type="text" name="titolo" id="titolo" class="form-control" placeholder="Inserire il titolo" value="${requestScope.libroInsert.titolo}"required>
								<div class="invalid-feedback">
                                   Attenzione! Devi inserire il titolo del libro!
                                 </div>
							</div>
							
							<div class="form-group col-md-6">
                          <label>Genere</label>
                           <select id="listaGeneri" name="genere" class="form-control" >	
		                   		<option value="${NULL}">- Seleziona Genere -</option>
			               		<c:forEach items="${listaGeneri}" var="genere">
			                		<c:if test="${genere != 'NULL' }">
						             	<option value="${genere}" ${genere == genereSelezionato ? 'selected="selected"' : '' }><c:out value="${genere}"/></option>
						            </c:if>
				                </c:forEach>
			                 </select>
                   </div>
							
							<div class="form-group col-md-6">
								<label>Trama <span class="text-danger">*</span></label>
								<input type="text" name="trama" id="trama" class="form-control" placeholder="Inserire la trama" value="${requestScope.libroInsert.trama}"required>
								<div class="invalid-feedback">
                                   Attenzione! Devi inserire la trama del libro!!
                                 </div>
							</div>
							<div class="form-group col-md-6">
	                          <label>Autore</label>
	                             <select id="listaAutori" name="autore_id" class="custom-select browser-default" >	
				                   <option value="">- Seleziona Autori -</option>
					               <c:forEach items="${requestScope.listaAutori}" var="autore">
						             <option value="${autore.id}"${autore.id == autoreSelezionato ? 'selected="selected"' : '' } ><c:out value="${autore.nome}"/>&nbsp;<c:out value="${autore.cognome}"/></option>
					               </c:forEach>
				                 </select>
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