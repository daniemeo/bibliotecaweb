package it.solvingteam.bibliotecaweb.web.servlet.utente;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.solvingteam.bibliotecaweb.model.autore.Autore;
import it.solvingteam.bibliotecaweb.model.ruolo.Ruolo;
import it.solvingteam.bibliotecaweb.model.utente.StatoUtente;
import it.solvingteam.bibliotecaweb.model.utente.Utente;
import it.solvingteam.bibliotecaweb.service.MyServiceFactory;

/**
 * Servlet implementation class ExecuteUpdateUtenteServlet
 */
@WebServlet("/utenti/ExecuteUpdateUtenteServlet")
public class ExecuteUpdateUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteUpdateUtenteServlet() {
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
		String idUtenteInput = request.getParameter("utente");
		String nomeUtenteInput = request.getParameter("nome");
		String cognomeUtenteInput = request.getParameter("cognome");
		String usernameUtenteInput = request.getParameter("username");
		String statoUtenteInput = request.getParameter("stato");
		String[] ruoliInInput = request.getParameterValues("ruoliSelezionati");
		StatoUtente statoUtente = null;
		
		try {
			List<String> errori = formValidation(request, response);
			if (!errori.isEmpty()) {
				request.setAttribute("listaStati", Stream.of(StatoUtente.values()).map(Enum::name).collect(Collectors.toList()));
				request.setAttribute("listaRuoli", MyServiceFactory.getRuoloServiceInstance().listAll());
				request.getRequestDispatcher("updateUtente.jsp").forward(request, response);
				return;
			} else {

				if (statoUtenteInput != null && !statoUtenteInput.isEmpty()) {
					statoUtente = StatoUtente.valueOf(statoUtenteInput);
				}
				Set<Ruolo> ruoli = new HashSet<Ruolo>();
				if (ruoliInInput != null) {
					for (String ruo : ruoliInInput) {
						Long id = Long.parseLong(ruo);
						Ruolo ruolo = new Ruolo();
						ruolo.setId(id);
						ruoli.add(ruolo);

					}
				}
				// togliere la password!! bisogna ripescare l'oggetto dal db (recuperandolo
				// attraverso l' id) e ci si settano tutti gli altri parametri!
				Utente utenteDaDb = MyServiceFactory.getUtenteServiceInstance().get(Long.parseLong(idUtenteInput));
				utenteDaDb.setNome(nomeUtenteInput);
				utenteDaDb.setCognome(cognomeUtenteInput);
				utenteDaDb.setUsername(usernameUtenteInput);
				utenteDaDb.setStato(statoUtente);
				utenteDaDb.setRuoli(ruoli);

				MyServiceFactory.getUtenteServiceInstance().aggiorna(utenteDaDb);
				request.setAttribute("listaUtentiAttribute", MyServiceFactory.getUtenteServiceInstance().setAll());
				request.setAttribute("successMessage", "Operazione effettuata con successo");
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("gestioneUtente.jsp").forward(request, response);
		

	}
	private List<String> formValidation(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String idUtenteInput = request.getParameter("utente");
		String nomeUtenteInput = request.getParameter("nome");
		String cognomeUtenteInput = request.getParameter("cognome");
		String usernameUtenteInput = request.getParameter("username");
		String statoUtenteInput = request.getParameter("stato");
		String[] ruoliInInput = request.getParameterValues("ruoliSelezionati");
		List<String> errori = new ArrayList<>();
		if(idUtenteInput == null || idUtenteInput.isEmpty()) {
			errori.add("Attenzione! Stai cercando di modificare un utente inesistente");
		}
		if (nomeUtenteInput == null || nomeUtenteInput.isEmpty()) {
			errori.add("Attenzione! Devi inserire il nome dell'utente");
		}

		if (cognomeUtenteInput == null || cognomeUtenteInput.isEmpty()) {
			errori.add("Attenzione! Devi inserire il cognome dell'utente");
		}

		if (usernameUtenteInput == null || usernameUtenteInput.isEmpty()) {
			errori.add("Attenzione! Devi inserire lo username dell'utente");
		}

		if (statoUtenteInput == null || statoUtenteInput.isEmpty()) {
			errori.add("Attenzione!! Devi inserire lo stato");
		}else {
			request.setAttribute("statoSelezionato", statoUtenteInput);
			
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
			StatoUtente statoUtente = null;
			if (statoUtenteInput != null && !statoUtenteInput.isEmpty()) {
			statoUtente  = StatoUtente.valueOf(statoUtenteInput);
			}
			Utente utenteModificato = new Utente(nomeUtenteInput, cognomeUtenteInput, usernameUtenteInput, statoUtente, ruoli);
			if (idUtenteInput != null && !idUtenteInput.isEmpty()) {
				
				utenteModificato.setId(Long.parseLong(idUtenteInput));
			}
			
			request.setAttribute("utente", utenteModificato);
		}
		return errori;

	}
}
