package it.solvingteam.bibliotecaweb.web.servlet.autore;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.solvingteam.bibliotecaweb.model.autore.Autore;
import it.solvingteam.bibliotecaweb.service.MyServiceFactory;




/**
 * Servlet implementation class PrepareDeleteAutoreServlet
 */
@WebServlet("/PrepareDeleteAutoreServlet")
public class PrepareDeleteAutoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrepareDeleteAutoreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
			Autore autore= MyServiceFactory.getAutoreServiceInstance().get(Long.parseLong(request.getParameter("idDaInviareComeParametro")));
			if (autore == null) {
			request.setAttribute("errorMessage", "Attenzione!! Questo autore non esiste!!!");
			
			request.getRequestDispatcher("PrepareCercaAutoreServlet").forward(request, response);
			
			return;
			}
			
			request.setAttribute("delete", autore);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        request.getRequestDispatcher("deleteAutore.jsp").forward(request, response);
	}        
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
