package it.solvingteam.bibliotecaweb.web.servlet.utente;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.solvingteam.bibliotecaweb.model.utente.Utente;
import it.solvingteam.bibliotecaweb.service.MyServiceFactory;

/**
 * Servlet implementation class ExecuteDeleteUtenteServlet
 */
@WebServlet("/utenti/ExecuteDeleteUtenteServlet")
public class ExecuteDeleteUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteDeleteUtenteServlet() {
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
	String idUtenteInput = request.getParameter("idDaInviareComeParametro");
			
			if( idUtenteInput == null || idUtenteInput.isEmpty()) {
				
	  			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
				request.getRequestDispatcher("utenti/gestioneUtente.jsp").forward(request, response);
				return;
			}
			Utente utente;
			try {
				 utente = MyServiceFactory.getUtenteServiceInstance().get(Long.parseLong(idUtenteInput));
				MyServiceFactory.getUtenteServiceInstance().rimuovi(utente);
				//MyServiceFactory.getUtenteServiceInstance().rimuovi.get(Long.parseLong(idUtenteInput))
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("successMessage", "Operazione effettuata con successo");
			request.getRequestDispatcher("gestioneUtente.jsp").forward(request, response);
		}

}
