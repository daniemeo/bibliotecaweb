<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/header.jsp" />
<meta charset="ISO-8859-1">
<title>Cerca Libro</title>
<link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
</head>
<body>
<jsp:include page="/navbar.jsp" />
 <main role="main" class="container">
 
  <div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
		  ${errorMessage}
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		<div class="alert alert-success alert-dismissible fade show ${successMessage==null?'d-none': ''}" role="alert">
		  ${successMessage}
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		<div class="alert alert-danger alert-dismissible fade show d-none" role="alert">
		  Esempio di operazione fallita!
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		<div class="alert alert-info alert-dismissible fade show d-none" role="alert">
		  Aggiungere d-none nelle class per non far apparire
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
   
    <div class='card'>
		    <div class='card-header'>
		       Cerca Libro
		    </div>
		    <div class='card-body'>
		    <a class="btn btn-primary " href="libri/PrepareInsertLibroServlet">Add New</a>
		 </div>
		    
     
	  <!-- Main jumbotron for a primary marketing message or call to action -->
	  
	    <div class='card-body'>
	      <form action="libri/ExecuteCercaLibroServlet" method="post">
	         <div class="form-row">
				<div class="form-group col-md-6">
	              <label>Titolo </label>
        			<input type="text" name="titolo" >
	        	</div>
	        	<div class="form-group col-md-6">
	                <label>Trama</label>
	                 <input type="text" name="trama" >
	            </div>
	            <div class="form-group col-md-6">
	                <label>Genere</label>
	                <input type="text" name="genere" >
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
				 <button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Cerca</button>
	       
	
          </form>
	    </div>
	   
	      
	      	<div class='table-responsive'>
		    	<table class='table table-striped ' >
	                <thead>
	                    <tr>
	                        
	                        <th>Titolo</th>
	                        <th>Trama</th>
	                        <th>Genere</th>
	                        <th> Autore </th>
	                        
	                    </tr>
	                </thead>
		            <tbody>   
		            
					    <c:forEach items="${requestScope.listaLibri}" var= "libro">
						     <tr >
						           <td><c:out value="${libro.titolo}"></c:out></td>
						           <td><c:out value="${libro.trama}"></c:out></td>
						           <td><c:out value="${libro.genere}"></c:out></td>
						           <td><c:out value="${libro.autore.nome}"></c:out></td>
						          <td>
						           <a class="btn  btn-sm btn-outline-secondary" href="libri/VisualizzaLibroServlet?idDaInviareComeParametro=${libro.id}"> Visualizza</a>
									
									<a class="btn  btn-sm btn-outline-primary ml-2 mr-2" href="libri/PrepareUpdateLibroServlet?idDaInviareExecuteUpdate=${libro.id}">Edit</a>
									
									
									<a class="btn btn-outline-danger btn-sm" href="libri/PrepareDeleteLibroServlet?idDaInviareComeParametro=${libro.id}">Delete</a>
									
								</td>
		                		
						           
				                   
				             </tr>
					    </c:forEach>
				  	</tbody> 
			  	</table>
		  	</div>
	   </div>
	  </div>
		
</main>	
</body>
</html>