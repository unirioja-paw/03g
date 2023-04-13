package es.unirioja.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * NO USAR
 *
 * Ejemplo para ver que para poder alterar la respuesta del Servlet hay que usar
 * otras tecnicas como los Wrappers
 */
public class HtmlStampFilter implements Filter {

    private ServletContext context;

    @Override
    public void init(FilterConfig filterConfig) {
        this.context = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        PrintWriter out = httpServletResponse.getWriter();

        log("preproceso");

        out.println("<!-- Text printed before chain.doFilter -->");

//        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
//        outputStream.println("dummy");
        // y al menos no usamos getOutputStream porque podriamos haber obtenido un error 500:
        // IllegalStateException: getWriter() ya ha sido llamado para esta respuesta
        //
        chain.doFilter(request, response);

        log("postproceso");

        out.println(
                String.format("<div class='app-badge'>Made with love (%s)</div>", new Date())
        );
    }

    @Override
    public void destroy() {
    }

    public void log(String msg) {
        context.log(String.format("%s: %s", this.getClass().toString(), msg));
    }

}
