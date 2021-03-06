package it.solvingteam.bibliotecaweb.web.servlet.autore;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.solvingteam.bibliotecaweb.service.MyServiceFactory;

/**
 * Servlet implementation class ExecuteDeleteAutoreServlet
 */
@WebServlet("/autori/ExecuteDeleteAutoreServlet")
public class ExecuteDeleteAutoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteDeleteAutoreServlet() {
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
		String idAutoreInput = request.getParameter("idDaInviareComeParametro");
		
		if( idAutoreInput == null || idAutoreInput.isEmpty()) {
			
  			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("autori/formCercaAutore.jsp").forward(request, response);
		
  		return; 
		}
		
		try {
	        MyServiceFactory.getAutoreServiceInstance().rimuovi(Long.parseLong(idAutoreInput));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("successMessage", "Operazione effettuata con successo");
		request.getRequestDispatcher("resultsCercaPerAutore.jsp").forward(request, response);
		
  		
	}

}
