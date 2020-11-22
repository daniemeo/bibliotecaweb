package it.solvingteam.bibliotecaweb.web.servlet.autore;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.solvingteam.bibliotecaweb.model.autore.Autore;
import it.solvingteam.bibliotecaweb.service.MyServiceFactory;

/**
 * Servlet implementation class ExecuteInsertAutoreServlet
 */
@WebServlet("/autori/ExecuteInsertAutoreServlet")
public class ExecuteInsertAutoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteInsertAutoreServlet() {
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
		String nomeInInput = request.getParameter("nome");
		String cognomeInput = request.getParameter("cognome");
		String dataInput = request.getParameter("dataNascita");

		try {
			List<String> errori = formValidation(request, response);
			if (!errori.isEmpty()) {
				request.getRequestDispatcher("inserisciAutore.jsp").forward(request, response);
				return;
				
			} else {
				Autore autoreInstance = new Autore(nomeInInput, cognomeInput);
				if (dataInput != null ) {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDate localDate = LocalDate.parse(dataInput, formatter);
					autoreInstance.setDataNascita(localDate);
				}
				MyServiceFactory.getAutoreServiceInstance().inserisciNuovo(autoreInstance);
				request.setAttribute("successMessage", "Operazione effettuata con successo");
				request.getRequestDispatcher("formCercaAutore.jsp").forward(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private List<String> formValidation(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String nomeInInput = request.getParameter("nome");
		String cognomeInput = request.getParameter("cognome");
		String dataInput = request.getParameter("dataNascita");
		List<String> errori = new ArrayList<>();
		

		if (nomeInInput == null || nomeInInput.isEmpty()) {
			errori.add("Attenzione! Devi inserire il nome dell'autore");
		}
		if (cognomeInput == null || cognomeInput.isEmpty()) {
			errori.add("Attenzione!! Devi inserire il cognome dell'autore");
		}
		if (dataInput == null || dataInput.isEmpty()) {

			errori.add("Attenzione!! Devi inserire la data di nascita dell'autore");
		}

		if (!errori.isEmpty()) {
			request.setAttribute("errorMessage", errori);
			Autore autoreInserito = new Autore(nomeInInput, cognomeInput);
			if (dataInput != null && !dataInput.isEmpty()) {

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate localDate = LocalDate.parse(dataInput, formatter);
				autoreInserito.setDataNascita(localDate);
			}
			
			request.setAttribute("autoreInsert", autoreInserito);
		}
		return errori;
	}

}
