package es.unirioja.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(filterName = "RequestCounter", urlPatterns = {"/*"})
public class RequestCounterFilter implements Filter {

    private final String STATS_KEY = "request_stats_counter";

    private ServletContext context;

    @Override
    public void init(FilterConfig filterConfig) {
        this.context = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        //  BeforeProcessing
        log("preproceso");

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String url = httpServletRequest.getRequestURL().toString();
        Map<String, Integer> counterMap = (HashMap<String, Integer>) context.getAttribute(STATS_KEY);

        if (counterMap == null) {
            counterMap = new HashMap<>();
            counterMap.put(url, 1);
            context.setAttribute("request_stats_counter", counterMap);
        } else if (!counterMap.containsKey(url)) {
            counterMap.put(url, 1);
        } else {
            counterMap.put(url, counterMap.get(url) + 1);
        }

        chain.doFilter(request, response);

        // AfterProcessing
        log("postproceso");

    }

    @Override
    public void destroy() {
    }

    public RequestCounterFilter() {
    }

    // TODO: otra vez repitiendo codigo. Te acuerdas del principio DRY?
    public void log(String msg) {
        context.log(String.format("%s: %s", this.getClass().toString(), msg));
    }

}
