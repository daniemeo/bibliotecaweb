package it.solvingteam.bibliotecaweb.web.servlet.utente;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.solvingteam.bibliotecaweb.model.utente.StatoUtente;
import it.solvingteam.bibliotecaweb.model.utente.Utente;

/**
 * Servlet implementation class PreprareCercaUtenteServlet
 */
@WebServlet("/utenti/PrepareCercaUtenteServlet")
public class PrepareCercaUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrepareCercaUtenteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		List<String> listaStati = Stream.of(StatoUtente.values()).map(Enum::name).collect(Collectors.toList());
		
		request.setAttribute("listaStati", listaStati);
		request.getRequestDispatcher("gestioneUtente.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
