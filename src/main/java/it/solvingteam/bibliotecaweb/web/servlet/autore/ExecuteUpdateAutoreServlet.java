package it.solvingteam.bibliotecaweb.web.servlet.autore;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.solvingteam.bibliotecaweb.model.autore.Autore;
import it.solvingteam.bibliotecaweb.service.MyServiceFactory;

/**
 * Servlet implementation class ExecuteUpdateAutoreServlet
 */
@WebServlet("/ExecuteUpdateAutoreServlet")
public class ExecuteUpdateAutoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteUpdateAutoreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idAutoreInput = request.getParameter("autoreUpdate");
		String nomeAutoreInput = request.getParameter("nome");
		String cognomeAutoreInput = request.getParameter("cognome");
		String dataAutoreInput = request.getParameter("dataDiNascita");
		
		Autore autore = new Autore();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd") ;
	    LocalDate localDate = LocalDate.parse(dataAutoreInput,formatter);
	    autore.setId(Long.parseLong(idAutoreInput));
	    autore.setNome(nomeAutoreInput);
	    autore.setCognome(cognomeAutoreInput);
	    autore.setDataNascita(localDate);
	    
	    
	    try {
	    	
	    	MyServiceFactory.getAutoreServiceInstance().aggiorna(autore);
			request.setAttribute("listaAutoriAttribute", MyServiceFactory.getAutoreServiceInstance().listAll());
			request.setAttribute("successMessage", "Operazione effettuata con successo");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    request.getRequestDispatcher("formCercaAutore.jsp").forward(request, response);
		
	}

}
