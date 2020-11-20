package it.solvingteam.bibliotecaweb.web.servlet.utente;

import java.util.List;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import it.solvingteam.bibliotecaweb.model.utente.StatoUtente;
import it.solvingteam.bibliotecaweb.model.utente.Utente;
import it.solvingteam.bibliotecaweb.service.MyServiceFactory;

/**
 * Servlet implementation class PrepareUpdateUtenteServlet
 */
@WebServlet("/utenti/PrepareUpdateUtenteServlet")
public class PrepareUpdateUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrepareUpdateUtenteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idUtenteInput = request.getParameter("idDaInviareExecuteUpdate");
		Utente utente;
		try {
			utente = MyServiceFactory.getUtenteServiceInstance().get(Long.parseLong(idUtenteInput));
			if (utente == null) {
				request.setAttribute("errorMessage", "Attenzione, l'id dell'autore che hai inserito non risulta nella nostra lista ");
				request.getRequestDispatcher("gestioneUtente.jsp");
			}
			List<String> listaStati = Stream.of(StatoUtente.values()).map(Enum::name).collect(Collectors.toList());
		
			
			request.setAttribute("listaRuoli", MyServiceFactory.getRuoloServiceInstance().listAll());
			request.setAttribute("listaStati", listaStati);
			request.setAttribute("utente", utente);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("updateUtente.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
