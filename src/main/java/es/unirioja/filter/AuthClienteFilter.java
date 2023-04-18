package es.unirioja.filter;

import es.unirioja.paw.model.Cliente;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Revisar url-pattern de los filter-mapping en web.xml
 */
public class AuthClienteFilter implements Filter {

    private ServletContext context;

    @Override
    public void init(FilterConfig filterConfig) {
        this.context = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        log("preproceso");

        redirectIfClienteNotFound(request, response);
    }

    @Override
    public void destroy() {
    }

    public void log(String msg) {
        context.log(String.format("%s: %s", this.getClass().toString(), msg));
    }

    private boolean isClienteInSession(HttpSession session) {
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        if (cliente != null) {
            log("Cliente encontrado en sesion");
            return true;

        }
        log("Cliente no encontrado en sesion");
        return false;
    }

    private void redirectIfClienteNotFound(ServletRequest request, ServletResponse response) throws IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();
        if (!isClienteInSession(session)) {
            log("Redirigiendo a login");
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login");
        }
    }

}
