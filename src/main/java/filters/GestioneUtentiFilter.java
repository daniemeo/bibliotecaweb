package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.solvingteam.bibliotecaweb.model.ruolo.CodiceRuolo;
import it.solvingteam.bibliotecaweb.model.ruolo.Ruolo;
import it.solvingteam.bibliotecaweb.model.utente.Utente;

/**
 * Servlet Filter implementation class GestioneUtentiFilter
 */
@WebFilter("/utenti/*")
public class GestioneUtentiFilter implements Filter {

	private String context;

	/**
	 * Default constructor.
	 */
	public GestioneUtentiFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		if (httpServletRequest.getSession() == null || httpServletRequest.getSession().getAttribute("utente") == null) {
			// request.setAttribute("errorMessage", "Attenzione sono presenti errori di
			// validazione");
			httpServletResponse.sendRedirect(context);
			// return;
		} else {
			Utente utente = (Utente) httpServletRequest.getSession().getAttribute("utente");

			boolean isAdmin = false;
			for (Ruolo ruolo : utente.getRuoli()) {
				if (CodiceRuolo.ADMIN_ROLE == ruolo.getCodice()) {
					isAdmin = true;
				}
			}
			if (isAdmin) {
				chain.doFilter(request, response);
			} else {
				httpServletResponse.sendRedirect(context);
			}
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		context = fConfig.getServletContext().getContextPath();
	}

}
