package it.solvingteam.bibliotecaweb.web.servlet.libro;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.solvingteam.bibliotecaweb.model.autore.Autore;
import it.solvingteam.bibliotecaweb.model.libro.GenereLibro;
import it.solvingteam.bibliotecaweb.model.libro.Libro;
import it.solvingteam.bibliotecaweb.service.MyServiceFactory;

/**
 * Servlet implementation class ExecuteUpdateLibroServlet
 */
@WebServlet("/libri/ExecuteUpdateLibroServlet")
public class ExecuteUpdateLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteUpdateLibroServlet() {
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
		String idLibroInput = request.getParameter("libroUpdate");
		String titoloLibroInput = request.getParameter("titolo");
		String genereLibroInput = request.getParameter("genere");
		String tramaLibroInout = request.getParameter("trama");
		String idAutoreLibroInput = request.getParameter("autore_id");
		
		GenereLibro genere = null;
		 if(genereLibroInput != null && !genereLibroInput.isEmpty()){
				genere = GenereLibro.valueOf(genereLibroInput);
			}
		 Autore autore = null;
			if (idAutoreLibroInput != null && !idAutoreLibroInput.isEmpty()) {
				autore = new Autore();
				autore.setId(Long.parseLong(idAutoreLibroInput));
			}
			
		 Libro libro = new Libro();
		 libro.setId(Long.parseLong(idLibroInput));
		 libro.setTitolo(titoloLibroInput);
		 libro.setGenere(genere);
		 libro.setTrama(tramaLibroInout);
		 libro.setAutore(autore);
		 
		 try {
			 request.setAttribute("listaAutori",MyServiceFactory.getAutoreServiceInstance().listAll());
			MyServiceFactory.getLibroServiceInstance().aggiorna(libro);
			request.setAttribute("listaLibriAttribute", MyServiceFactory.getLibroServiceInstance().setAll());
			request.setAttribute("successMessage", "Operazione effettuata con successo");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  request.getRequestDispatcher("formCercaLibro.jsp").forward(request, response);
	}

}
