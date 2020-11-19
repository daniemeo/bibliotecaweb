package it.solvingteam.bibliotecaweb.web.servlet.utente;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.solvingteam.bibliotecaweb.model.ruolo.Ruolo;
import it.solvingteam.bibliotecaweb.model.utente.Utente;
import it.solvingteam.bibliotecaweb.service.MyServiceFactory;

/**
 * Servlet implementation class ExecuteInsertUtenteServlet
 */
@WebServlet("/utenti/ExecuteInsertUtenteServlet")
public class ExecuteInsertUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteInsertUtenteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nomeInput = request.getParameter("nome");
		String cognomeInput = request.getParameter("cognome");
		String usernameInput = request.getParameter("username");
		String passwordInput = request.getParameter("password");
		String[] ruoliInInput = request.getParameterValues("ruoliSelezionati");
		try {
			Set<Ruolo> ruoli = new HashSet<Ruolo>();
			for (String ruo : ruoliInInput) {
				Long id = Long.parseLong(ruo);
				Ruolo ruolo = MyServiceFactory.getRuoloServiceInstance().get(id);
				ruoli.add(ruolo);
			}

			Utente utente = new Utente(nomeInput, cognomeInput, usernameInput, passwordInput, ruoli);
			MyServiceFactory.getUtenteServiceInstance().inserisciNuovo(utente);
			request.setAttribute("listaUtenti", MyServiceFactory.getUtenteServiceInstance().setAll());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("successMessage", "Operazione effettuata con successo");
		request.getRequestDispatcher("resultsCercaPerUtente.jsp").forward(request, response);

	}

}
