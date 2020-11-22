<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/header.jsp" />
<meta charset="ISO-8859-1">
<title>Cerca Autore</title>
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
		       Cerca Autore
		    </div>
         
	    <div class='card-body'>
	      <form action="${pageContext.request.contextPath}/autori/ExecuteCercaAutoreServlet" method="post">
	         <div class="form-row">
				<div class="form-group col-md-6">
	              <label for="nome">Nome </label>
        			<input type="text" id="nome" name="nome"  class="form-control">
	        	</div>
	        	<div class="form-group col-md-6">
	                <label for="cognome">Cognome</label>
	                 <input type="text" id="cognome" name="cognome"  class="form-control">
	            </div>
	           
	             
			 </div>			
				 <button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Cerca</button>
	       
	
          </form>
	    </div>
	     <div class='card-footer'>
		        <a href="${pageContext.request.contextPath}/home.jsp"
		         class='btn btn-outline-secondary' style='width:80px'>
		            <i class='fa fa-chevron-left'></i> Back
		        </a>
		 </div>
	     
	   
	      
	      
		  	</div>
	 
	  
		
</main>	

	<jsp:include page="/footer.jsp" />
</body>
</html>