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
 * Servlet implementation class ExecuteUpdateAutoreServlet
 */
@WebServlet("/autori/ExecuteUpdateAutoreServlet")
public class ExecuteUpdateAutoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteUpdateAutoreServlet() {
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
		String idAutoreInput = request.getParameter("autoreUpdate");
		String nomeAutoreInput = request.getParameter("nome");
		String cognomeAutoreInput = request.getParameter("cognome");
		String dataAutoreInput = request.getParameter("dataDiNascita");

		try {
			List<String> errori = formValidation(request, response);
			if (!errori.isEmpty()) {
				request.getRequestDispatcher("updateAutore.jsp").forward(request, response);
				return;

			} else {
				Autore autoreDaDb = MyServiceFactory.getAutoreServiceInstance().get(Long.parseLong(idAutoreInput));

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate localDate = LocalDate.parse(dataAutoreInput, formatter);
				autoreDaDb.setNome(nomeAutoreInput);
				autoreDaDb.setCognome(cognomeAutoreInput);
				autoreDaDb.setDataNascita(localDate);

				MyServiceFactory.getAutoreServiceInstance().aggiorna(autoreDaDb);
				request.setAttribute("successMessage", "Operazione effettuata con successo");
				request.setAttribute("listaAutoriAttribute", MyServiceFactory.getAutoreServiceInstance().listAll());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.getRequestDispatcher("formCercaAutore.jsp").forward(request, response);

	}

	private List<String> formValidation(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String idAutoreInput = request.getParameter("autoreUpdate");
		String nomeAutoreInput = request.getParameter("nome");
		String cognomeAutoreInput = request.getParameter("cognome");
		String dataAutoreInput = request.getParameter("dataDiNascita");
		List<String> errori = new ArrayList<>();

		if (idAutoreInput == null || idAutoreInput.isEmpty()) {
			errori.add("Attenzione! ID non presente");
		}
		if (nomeAutoreInput == null || nomeAutoreInput.isEmpty()) {
			errori.add("Attenzione! Devi inserire il nome dell'autore");
		}
		if (cognomeAutoreInput == null || cognomeAutoreInput.isEmpty()) {
			errori.add("Attenzione!! Devi inserire il cognome dell'autore");
		}
		if (dataAutoreInput == null || dataAutoreInput.isEmpty()) {

			errori.add("Attenzione!! Devi inserire la data di nascita dell'autore");
		}

		if (!errori.isEmpty()) {
			request.setAttribute("errori", errori);
			Autore autoreInserito = new Autore(nomeAutoreInput, cognomeAutoreInput);
			if (idAutoreInput != null && !idAutoreInput.isEmpty()) {
				autoreInserito.setId(Long.parseLong(idAutoreInput));
			}
			if (dataAutoreInput != null && !dataAutoreInput.isEmpty()) {

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate localDate = LocalDate.parse(dataAutoreInput, formatter);
				autoreInserito.setDataNascita(localDate);
			}

			request.setAttribute("autoreUpdate", autoreInserito);
		}
		return errori;
	}

}
