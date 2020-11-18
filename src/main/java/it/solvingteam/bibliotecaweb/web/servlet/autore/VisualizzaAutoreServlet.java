package it.solvingteam.bibliotecaweb.web.servlet.autore;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.solvingteam.bibliotecaweb.model.autore.Autore;
import it.solvingteam.bibliotecaweb.service.MyServiceFactory;
import it.solvingteam.bibliotecaweb.service.autore.AutoreService;

/**
 * Servlet implementation class VisualizzaAutoreServlet
 */
@WebServlet("/VisualizzaAutoreServlet")
public class VisualizzaAutoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzaAutoreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String parametroIdAutoreDiCuiVoglioIlDettaglio = request.getParameter("idDaInviareComeParametro");
		
		Autore result;
		try {
			AutoreService service = MyServiceFactory.getAutoreServiceInstance();
			result = service.get(Long.parseLong(parametroIdAutoreDiCuiVoglioIlDettaglio));
			request.setAttribute("autorePerListaAutore", result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("formVisualizzaAutore.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
