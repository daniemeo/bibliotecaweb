package it.solvingteam.bibliotecaweb.web.servlet.utente;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.solvingteam.bibliotecaweb.model.utente.StatoUtente;
import it.solvingteam.bibliotecaweb.model.utente.Utente;
import it.solvingteam.bibliotecaweb.service.MyServiceFactory;

/**
 * Servlet implementation class ExecuteCercaUtenteServlet
 */
@WebServlet("/utenti/ExecuteCercaUtenteServlet")
public class ExecuteCercaUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteCercaUtenteServlet() {
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
		String nomeInputParam = request.getParameter("nome");
		String cognomeInputParam = request.getParameter("cognome");
		String usernameInputParam = request.getParameter("username");
		String statoInInput = request.getParameter("stato");
		StatoUtente stato = null;
		
		if ( statoInInput != null && !statoInInput.isEmpty()) {
			stato = StatoUtente.valueOf(statoInInput);
		}
		
		Utente utente = new Utente(nomeInputParam, cognomeInputParam, usernameInputParam, stato);
		
		try {
			request.setAttribute("listaUtenti", MyServiceFactory.getUtenteServiceInstance().findByUtente(utente));
			//request.setAttribute("listaUtentiAttribute", MyServiceFactory.getUtenteServiceInstance().setAll());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("resultsCercaPerUtente.jsp").forward(request, response);
	}

}
