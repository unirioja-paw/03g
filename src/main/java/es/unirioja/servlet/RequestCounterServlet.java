package es.unirioja.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "RequestCounterServlet", urlPatterns = {"/stats"})
public class RequestCounterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext context = request.getSession().getServletContext();
        request.setAttribute("statsCounter",
                context.getAttribute("request_stats_counter")
        );
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/requestCounter.jsp");
        rd.forward(request, response);
    }

}
