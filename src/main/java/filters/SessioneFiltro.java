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

/**
 * Servlet Filter implementation class SessioneFiltro
 */
@WebFilter("/SessioneFiltro")
public class SessioneFiltro implements Filter {

    private String context;

	/**
     * Default constructor. 
     */
    public SessioneFiltro() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	 context = fConfig.getServletContext().getContextPath();
	 
	 
	}
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest= (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		String percorso = httpServletRequest.getRequestURI();
		
		if(percorso.equals("/bibliotecaweb")|| percorso.equals("/bibliotecaweb/LoginServlet")|| percorso.equals("/bibliotecaweb/LogoutServlet")) {
			chain.doFilter(request, response);
		}
		else {
			if(httpServletRequest.getSession().getAttribute("utente") == null) {
				httpServletResponse.sendRedirect(context);
			}
			else { 
				chain.doFilter(request, response);
			}
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */


}
