package es.unirioja.filter;

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CompressionFilter implements Filter {
    
    protected int umbralCompresion = 0;
    
    public void init(FilterConfig filterConfig) {
        String str = filterConfig.getInitParameter("umbralCompresion");
        if (str != null) {
            umbralCompresion = Integer.parseInt(str);
        }
    }
    
    private boolean isCompressionAccepted(ServletRequest request) {
        Enumeration e = ((HttpServletRequest) request).getHeaders("Accept-Encoding");
        while (e.hasMoreElements()) {
            String name = (String) e.nextElement();
            if (name.indexOf("gzip") != -1) {
                return true;
            }
        }
        return false;
        
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        
        if (isCompressionAccepted(request)) {
            System.out.println("Encoding requested.");
            ((HttpServletResponse) response).setHeader("Content-Encoding", "gzip");
            CompressionResponseWrapper wrapper = new CompressionResponseWrapper((HttpServletResponse) response);
            try {
                chain.doFilter(request, wrapper);
            } finally {
                try {
                    wrapper.finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Encoding not requested.");
            chain.doFilter(request, response);
        }
        
    }
    
    @Override
    public void destroy() {
    }
    
}
