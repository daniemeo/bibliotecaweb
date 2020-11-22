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
 * Servlet implementation class ExecuteInsertLibroServlet
 */
@WebServlet("/libri/ExecuteInsertLibroServlet")
public class ExecuteInsertLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteInsertLibroServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String titoloInput = request.getParameter("titolo");
		String genereInput = request.getParameter("genere");
		String tramaInput = request.getParameter("trama");
		String idAutoreInput = request.getParameter("autore_id");
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
		if (genereInput != null && !genereInput.isEmpty()) {
			genere = GenereLibro.valueOf(genereInput);
		}
		Autore autore = null;
		if (idAutoreInput != null && !idAutoreInput.isEmpty()) {
			autore = new Autore();
			autore.setId(Long.parseLong(idAutoreInput));
		}
		Libro libro = new Libro(titoloInput, genere, tramaInput, autore);

		try {
			request.setAttribute("listaAutori", MyServiceFactory.getAutoreServiceInstance().listAll());
			MyServiceFactory.getLibroServiceInstance().inserisciNuovo(libro);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("successMessage", "Operazione effettuata con successo");
		request.getRequestDispatcher("resultCerca.jsp").forward(request, response);
	}

	private List<String> formValidation(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String titoloInput = request.getParameter("titolo");
		String genereInput = request.getParameter("genere");
		String tramaInput = request.getParameter("trama");
		String idAutoreInput = request.getParameter("autore_id");

		GenereLibro genere = null;
		if (genereInput != null && !genereInput.isEmpty()) {
			genere = GenereLibro.valueOf(genereInput);
		}
		List<String> errori = new ArrayList<>();
		Autore autore = new Autore();
		if (titoloInput == null || titoloInput.isEmpty()) {

			errori.add("Attenzione! Devi inserire il titolo del libro");
		}
		if (genereInput == null || genereInput.isEmpty()) {
			errori.add("Attenzione!! Devi inserire il genere del libro");
		} else {
			request.setAttribute("genereSelezionato", genereInput);
			
		}
		if (tramaInput == null || tramaInput.isEmpty()) {
			errori.add("Attenzione!! Devi inserire il genere del libro");
		}
		if (idAutoreInput == null || idAutoreInput.isEmpty()) {
			errori.add("Attenzione!! Devi inserire l'autore del libro");
		} else {
			request.setAttribute("autoreSelezionato", idAutoreInput);
			
		}
		if (!errori.isEmpty()) {
			request.setAttribute("errorMessage", errori);

			if (idAutoreInput != null && !idAutoreInput.isEmpty()) {
				autore = new Autore();
				autore.setId(Long.parseLong(idAutoreInput));
			}
			Libro libro = new Libro(titoloInput, genere, tramaInput, autore);
			request.setAttribute("libroInsert", libro);

		}
		return errori;
	}

}
