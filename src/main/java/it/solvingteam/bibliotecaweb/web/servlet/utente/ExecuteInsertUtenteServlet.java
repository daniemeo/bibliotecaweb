package it.solvingteam.bibliotecaweb.web.servlet.utente;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
			List<String> errori = formValidation(request, response);
			if (!errori.isEmpty()) {
				request.setAttribute("listaRuoli", MyServiceFactory.getRuoloServiceInstance().listAll());
				request.getRequestDispatcher("insertUtente.jsp").forward(request, response);
				return;
			} else {

				Utente utente = new Utente(nomeInput, cognomeInput, usernameInput, passwordInput);
				if (ruoliInInput != null) {
					Set<Ruolo> ruoli = new HashSet<Ruolo>();
					for (String ruo : ruoliInInput) {
						Long id = Long.parseLong(ruo);
						Ruolo ruolo = new Ruolo();
						ruolo.setId(id);
						ruoli.add(ruolo);
						utente.setRuoli(ruoli);
					}
				}

				MyServiceFactory.getUtenteServiceInstance().inserisciNuovo(utente);

				// request.setAttribute("listaUtenti",
				// MyServiceFactory.getUtenteServiceInstance().setAll());

				request.setAttribute("successMessage", "Operazione effettuata con successo");
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("resultsCercaPerUtente.jsp").forward(request, response);
	}

	private List<String> formValidation(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String nomeInput = request.getParameter("nome");
		String cognomeInput = request.getParameter("cognome");
		String usernameInput = request.getParameter("username");
		String passwordInput = request.getParameter("password");
		String[] ruoliInInput = request.getParameterValues("ruoliSelezionati");

		List<String> errori = new ArrayList<>();

		if (nomeInput == null || nomeInput.isEmpty()) {
			errori.add("Attenzione! Devi inserire il nome dell'utente");
		}

		if (cognomeInput == null || cognomeInput.isEmpty()) {
			errori.add("Attenzione! Devi inserire il cognome dell'utente");
		}

		if (usernameInput == null || usernameInput.isEmpty()) {
			errori.add("Attenzione! Devi inserire lo username dell'utente");
		}

		if (passwordInput == null || passwordInput.isEmpty()) {
			errori.add("Attenzione!! Devi inserire la password");
		}
		if (ruoliInInput == null) {
			errori.add("Attenzione!! Devi inserire i ruoli!!");
		} else {
			request.setAttribute("ruoloSelezionato", ruoliInInput);

		}
		if (!errori.isEmpty()) {
			request.setAttribute("errorMessage", errori);
			Set<Ruolo> ruoli = new HashSet<Ruolo>();
			if (ruoliInInput != null) {
				for (String ruo : ruoliInInput) {
					Long id = Long.parseLong(ruo);
					Ruolo ruolo = new Ruolo();
					ruolo.setId(id);
					ruoli.add(ruolo);
				}
			}
			Utente utenteInserito = new Utente(nomeInput, cognomeInput, usernameInput, passwordInput, ruoli);
			request.setAttribute("utenteInsert", utenteInserito);
		}
		return errori;

	}
}
