package it.solvingteam.bibliotecaweb.web.servlet.libro;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.MysqlType;

import it.solvingteam.bibliotecaweb.model.autore.Autore;
import it.solvingteam.bibliotecaweb.model.libro.GenereLibro;
import it.solvingteam.bibliotecaweb.model.libro.Libro;
import it.solvingteam.bibliotecaweb.service.MyServiceFactory;

/**
 * Servlet implementation class ExecuteCercaLibroServlet
 */
@WebServlet("/libri/ExecuteCercaLibroServlet")
public class ExecuteCercaLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteCercaLibroServlet() {
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
		String titoloInputParam = request.getParameter("titolo");
		String tramaInputParam = request.getParameter("trama");
		String genereInputParam = request.getParameter("genere");
		String autoreInputParam = request.getParameter("autore_id");
        GenereLibro genere = null;
        //if(titoloInputParam == null && titoloInputParam.isEmpty() && tramaInputParam == null && tramaInputParam.isEmpty() && genereInputParam == null 
        	//	  && genereInputParam.isEmpty() && autoreInputParam == null && autoreInputParam.isEmpty()) {
        	//request.getRequestDispatcher("listaLibri.jsp").forward(request, response);
        //}
		if(genereInputParam != null && !genereInputParam.isEmpty()){
			genere = GenereLibro.valueOf(genereInputParam);
		}
		Autore autore = null;
		if (autoreInputParam != null && !autoreInputParam.isEmpty()) {
			autore = new Autore();
			autore.setId(Long.parseLong(autoreInputParam));
		}
		Libro libro = new Libro(titoloInputParam, genere, tramaInputParam, autore);
		try {
			request.setAttribute("listaAutori",MyServiceFactory.getAutoreServiceInstance().listAll());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("listaLibri", MyServiceFactory.getLibroServiceInstance().findByLibro(libro));

		request.getRequestDispatcher("resultCerca.jsp").forward(request, response);

	}

}
