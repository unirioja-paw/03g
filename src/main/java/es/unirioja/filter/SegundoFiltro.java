package es.unirioja.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Filtro configurado despues del TiempoRespuesta en web.xml
 */
public class SegundoFiltro implements Filter {

    private ServletContext context;

    @Override
    public void init(FilterConfig filterConfig) {
        this.context = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        log("preproceso");

        chain.doFilter(request, response);

        log("postproceso");
    }

    @Override
    public void destroy() {
    }

    public void log(String msg) {
        context.log(String.format("%s: %s", this.getClass().toString(), msg));
    }

}
