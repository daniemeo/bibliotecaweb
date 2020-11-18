package it.solvingteam.bibliotecaweb.web.servlet;

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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		String usernameDaPagina = request.getParameter("username");
		String passwordDaPagina = request.getParameter("password");
        Utente utente;
        UtenteService utenteService = MyServiceFactory.getUtenteServiceInstance();
      		try {
      			utente= utenteService.cercaPerUsernameEPassword(usernameDaPagina, passwordDaPagina);
      			if(utente != null) {
      				request.getRequestDispatcher("home.jsp").forward(request, response);
    			} else {request.getRequestDispatcher("index.jsp").forward(request, response);
      			}
      		}catch(Exception e) {
    			e.printStackTrace();
    		}
	}

}
