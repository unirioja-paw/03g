package es.unirioja.filter;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebFilter(filterName = "ForwardLoggingFilter", urlPatterns = {"/*"}, dispatcherTypes = DispatcherType.FORWARD)
public class ForwardLoggingFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private ServletContext context;

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        logger.info("DispatcherType: {}", request.getDispatcherType());

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        logger.info("RequestURI: {}", httpServletRequest.getRequestURI());

        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) {
        this.context = filterConfig.getServletContext();
    }

}
