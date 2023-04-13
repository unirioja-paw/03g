package es.unirioja.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Ejemplo de filtro con anotacion @WebFilter. No se define en web.xml
 *
 * Prueba a generar un Filter desde NetBeans sin marcar la opcion "Add
 * information to deployment descriptor (web.xml)"
 */
@WebFilter(filterName = "SimpleAnnotatedFilter", urlPatterns = {"/*"})
public class SimpleAnnotatedFilter implements Filter {

    private ServletContext context;

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {


        //  BeforeProcessing
        log("preproceso");

        chain.doFilter(request, response);

        // AfterProcessing
        log("postproceso");

    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) {
        this.context = filterConfig.getServletContext();
    }

    public void log(String msg) {
        context.log(String.format("%s: %s", this.getClass().toString(), msg));
    }

}
