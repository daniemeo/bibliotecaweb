<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/header.jsp" />
<meta charset="ISO-8859-1">
<title>Cerca Utente</title>
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
		      Utente
		    </div>
		<div class='card-body'>
		    <a class="btn btn-primary " href="PrepareInsertUtente">Add New</a>
		 </div>
      
         
	  
	    <div class='card-body'>
	      <form action="ExecuteCercaUtenteServlet" method="post">
	         <div class="form-row">
				<div class="form-group col-md-6">
	              <label>Nome </label>
        			<input type="text" name="nome" >
	        	</div>
	        	<div class="form-group col-md-6">
	                <label>Cognome</label>
	                 <input type="text" name="cognome" >
	            </div>
	           <div class="form-group col-md-6">
	              <label>Username </label>
        			<input type="text" name="username" >
	        	</div>
	            <div class="form-group col-md-6">
	                <label>Stato</label>
	                 <input type="text" name="stato" >
	            </div>
	             
			 </div>			
				 <button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Cerca</button>
	       
	
          </form>
	    </div>
	   
	   
	      
	      	<div class='table-responsive'>
		    	<table class='table table-striped ' >
	                <thead>
	                    <tr>
	                        
	                        <th>Nome</th>
	                        <th>Cognome</th>
	                        <th>Username </th>
	                        <th>Stato </th>
	                        <th>Ruolo</th>
	                        
	                    </tr>
	                </thead>
		            <tbody>   
		            
					    <c:forEach items="${requestScope.listaAutori}" var= "autore">
						     <tr >
						           <td><c:out value="${autore.nome}"></c:out></td>
						           <td><c:out value="${autore.cognome}"></c:out></td>
						           <td><c:out value="${autore.dataNascita}"></c:out></td>
						           <td>
						           <a class="btn  btn-sm btn-outline-secondary" href="VisualizzaAutoreServlet?idDaInviareComeParametro=${autore.id}"> Visualizza</a>
									
									<a class="btn  btn-sm btn-outline-primary ml-2 mr-2" href="PrepareUpdateAutoreSerlvlet?idDaInviareExecuteUpdate=${autore.id}">Edit</a>
								
									
									<a class="btn btn-outline-danger btn-sm" href="PrepareDeleteAutoreServlet?idDaInviareComeParametro=${autore.id}">Delete</a>
									
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