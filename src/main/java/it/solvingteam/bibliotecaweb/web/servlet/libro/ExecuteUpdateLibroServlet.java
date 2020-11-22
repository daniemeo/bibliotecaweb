package it.solvingteam.bibliotecaweb.web.servlet.libro;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
		String idLibroInput = request.getParameter("libroUpdate");
		String titoloLibroInput = request.getParameter("titolo");
		String genereLibroInput = request.getParameter("genere");
		String tramaLibroInput = request.getParameter("trama");
		String idAutoreLibroInput = request.getParameter("autore_id");

		try {
			List<String> errori = formValidation(request, response);
			if (!errori.isEmpty()) {
				List<String> listaGeneri = Stream.of(GenereLibro.values()).map(Enum::name).collect(Collectors.toList());
				request.setAttribute("listaGeneri", listaGeneri);
				request.setAttribute("listaAutori", MyServiceFactory.getAutoreServiceInstance().listAll());
				request.getRequestDispatcher("inserisciLibro.jsp").forward(request, response);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		GenereLibro genere = null;
		if (genereLibroInput != null && !genereLibroInput.isEmpty()) {
			genere = GenereLibro.valueOf(genereLibroInput);
		}

		Autore autore = null;
		if (idAutoreLibroInput != null && !idAutoreLibroInput.isEmpty()) {
			autore = new Autore();
			autore.setId(Long.parseLong(idAutoreLibroInput));
		}

		try {
			Libro libroDaDb = MyServiceFactory.getLibroServiceInstance().get(Long.parseLong(idLibroInput));
			libroDaDb.setTitolo(titoloLibroInput);
			libroDaDb.setGenere(genere);
			libroDaDb.setTrama(tramaLibroInput);
			libroDaDb.setAutore(autore);

			request.setAttribute("listaAutori", MyServiceFactory.getAutoreServiceInstance().listAll());
			MyServiceFactory.getLibroServiceInstance().aggiorna(libroDaDb);
			request.setAttribute("listaLibriAttribute", MyServiceFactory.getLibroServiceInstance().setAll());
			request.setAttribute("successMessage", "Operazione effettuata con successo");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("formCercaLibro.jsp").forward(request, response);
	}

	private List<String> formValidation(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String idLibroInput = request.getParameter("libroUpdate");
		String titoloLibroInput = request.getParameter("titolo");
		String genereLibroInput = request.getParameter("genere");
		String tramaLibroInput = request.getParameter("trama");
		String idAutoreLibroInput = request.getParameter("autore_id");
		
		GenereLibro genere = null;
		
		if (genereLibroInput != null && !genereLibroInput.isEmpty()) {
			genere = GenereLibro.valueOf(genereLibroInput);
		}
		List<String> errori = new ArrayList<>();
		Autore autore = new Autore();
		if (titoloLibroInput == null || titoloLibroInput.isEmpty()) {

			errori.add("Attenzione! Devi inserire il titolo del libro");
		}
		if (genereLibroInput == null || genereLibroInput.isEmpty()) {
			errori.add("Attenzione!! Devi inserire il genere del libro");
		} else {
			request.setAttribute("genereSelezionato", genereLibroInput);
			
		}
		if (tramaLibroInput == null || tramaLibroInput.isEmpty()) {
			errori.add("Attenzione!! Devi inserire il genere del libro");
		}
		if (idAutoreLibroInput == null || idAutoreLibroInput.isEmpty()) {
			errori.add("Attenzione!! Devi inserire l'autore del libro");
		} else {
			request.setAttribute("autoreSelezionato", idAutoreLibroInput);
			
		}
		if (!errori.isEmpty()) {
			request.setAttribute("errorMessage", errori);

			if (idAutoreLibroInput != null && !idAutoreLibroInput.isEmpty()) {
				autore = new Autore();
				autore.setId(Long.parseLong(idAutoreLibroInput));
			}
			Libro libro = new Libro(titoloLibroInput, genere, tramaLibroInput, autore);
			request.setAttribute("libroUpdate", libro);

		}
		return errori;
	}
}
