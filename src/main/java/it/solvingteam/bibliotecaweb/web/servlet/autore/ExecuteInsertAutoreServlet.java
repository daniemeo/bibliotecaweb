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
 * Servlet implementation class ExecuteInsertAutoreServlet
 */
@WebServlet("/ExecuteInsertAutoreServlet")
public class ExecuteInsertAutoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteInsertAutoreServlet() {
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
		String nomeInInput = request.getParameter("nome");
		String cognomeInput = request.getParameter("cognome");
		String dataInput = request.getParameter("dataNascita");
		
		if(nomeInInput.isEmpty() || cognomeInput.isEmpty() || dataInput.isEmpty()) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
		    request.getRequestDispatcher("inserisciAutore.jsp").forward(request, response);
		    return;
		}

	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate localDate = LocalDate.parse(dataInput,formatter);
		Autore autoreInstance = new Autore(nomeInInput,cognomeInput, localDate);
		
		
		try {
			MyServiceFactory.getAutoreServiceInstance().inserisciNuovo(autoreInstance);
			request.setAttribute("listaAutoriAttribute", MyServiceFactory.getAutoreServiceInstance().listAll());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("successMessage", "Operazione effettuata con successo");
		request.getRequestDispatcher("formCercaAutore.jsp").forward(request, response);
	}

}
