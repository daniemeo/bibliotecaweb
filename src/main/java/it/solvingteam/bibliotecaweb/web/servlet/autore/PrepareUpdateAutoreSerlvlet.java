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
 * Servlet implementation class PrepareUpdateAutoreSerlvlet
 */
@WebServlet("/autori/PrepareUpdateAutoreSerlvlet")
public class PrepareUpdateAutoreSerlvlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrepareUpdateAutoreSerlvlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idAutore=request.getParameter("idDaInviareExecuteUpdate");
		try {
			Autore autore = MyServiceFactory.getAutoreServiceInstance().get(Long.parseLong(idAutore));
			if (autore == null) {
				request.setAttribute("errorMessage", "Attenzione, l'id dell'utete che hai selezionato non risulta nella nostra lista ");
				request.getRequestDispatcher("formCercaAutore.jsp");
			}
			request.setAttribute("autoreUpdate", autore);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("updateAutore.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
