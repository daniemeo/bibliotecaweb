package it.solvingteam.bibliotecaweb.web.servlet.utente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.solvingteam.bibliotecaweb.model.utente.Utente;
import it.solvingteam.bibliotecaweb.service.MyServiceFactory;
import it.solvingteam.bibliotecaweb.service.utente.UtenteService;

/**
 * Servlet implementation class VisualizzaUtenteServlet
 */
@WebServlet("/utenti/VisualizzaUtenteServlet")
public class VisualizzaUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzaUtenteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 String idUtenteDettaglio = request.getParameter("idDaInviareComeParametro");
	 Utente utente;
	 UtenteService service = MyServiceFactory.getUtenteServiceInstance();
	 
	 try {
		utente = service.get(Long.parseLong(idUtenteDettaglio));
		request.setAttribute("utente", utente);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		request.getRequestDispatcher("formVisualizzaUtente.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
