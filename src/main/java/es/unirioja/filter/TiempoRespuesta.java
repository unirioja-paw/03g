package es.unirioja.filter;

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

public class TiempoRespuesta implements Filter {

    private ServletContext context;

    @Override
    public void init(FilterConfig filterConfig) {
        this.context = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        long start = System.currentTimeMillis();

        log("preproceso");

        chain.doFilter(request, response);

        log("postproceso");
        long end = System.currentTimeMillis();

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        long timeElapsed = end - start;
        log("Tiempo peticion " + httpServletRequest.getRequestURI() + ": " + timeElapsed + " ms");
        // TODO: que unidad de medida tiene este valor?

        // y lo ponemos en cabecera de respuesta
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Tiempo-Peticion", String.valueOf(timeElapsed));
    }

    @Override
    public void destroy() {
    }

    public void log(String msg) {
        context.log(String.format("%s: %s", this.getClass().toString(), msg));
    }

}
