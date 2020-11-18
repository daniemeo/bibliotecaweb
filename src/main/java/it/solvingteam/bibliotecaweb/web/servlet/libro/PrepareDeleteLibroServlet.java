package it.solvingteam.bibliotecaweb.web.servlet.libro;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.solvingteam.bibliotecaweb.model.libro.Libro;
import it.solvingteam.bibliotecaweb.service.MyServiceFactory;

/**
 * Servlet implementation class PrepareDeleteLibroServlet
 */
@WebServlet("/PrepareDeleteLibroServlet")
public class PrepareDeleteLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrepareDeleteLibroServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Libro libro = MyServiceFactory.getLibroServiceInstance().get(Long.parseLong(request.getParameter("idDaInviareComeParametro")));
			if (libro == null) {
				request.setAttribute("errorMessage", "Attenzione!! Questo libro non esiste!!!");
				request.getRequestDispatcher("PrepareCercaLibroServlet").forward(request, response);
				
				return;
			}
			request.setAttribute("delete", libro);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 request.getRequestDispatcher("deleteLibro.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
