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
		String idUtenteInput = request.getParameter("utente");
		String nomeUtenteInput = request.getParameter("nome");
		String cognomeUtenteInput = request.getParameter("cognome");
		String usernameUtenteInput = request.getParameter("username");
		String passwordUtenteInput = request.getParameter("password");
		String statoUtenteInput = request.getParameter("stato");
		String[] ruoliInInput = request.getParameterValues("ruoliSelezionati");
		StatoUtente statoUtente = null;
		 if(statoUtenteInput != null && !statoUtenteInput.isEmpty()){
				statoUtente = StatoUtente.valueOf(statoUtenteInput);
		 }
		 Set<Ruolo> ruoli = new HashSet<Ruolo>();
			for (String ruo : ruoliInInput) {
				Long id = Long.parseLong(ruo);
				Ruolo ruolo = new Ruolo();
				ruolo.setId(id);
				ruoli.add(ruolo);
				
			}
		 Utente utente = new Utente();
		 utente.setId(Long.parseLong(idUtenteInput));
		 utente.setNome(nomeUtenteInput);
		 utente.setCognome(cognomeUtenteInput);
		 utente.setUsername(usernameUtenteInput);
		 utente.setPassword(passwordUtenteInput);
		 utente.setStato(statoUtente);
		 utente.setRuoli(ruoli);
		 
		 try {
			MyServiceFactory.getUtenteServiceInstance().aggiorna(utente);
			request.setAttribute("listaUtentiAttribute", MyServiceFactory.getUtenteServiceInstance().setAll());
			request.setAttribute("successMessage", "Operazione effettuata con successo");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  request.getRequestDispatcher("gestioneUtente.jsp").forward(request, response);
		 
	}

}
