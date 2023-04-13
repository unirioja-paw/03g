<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PAW</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <section>
            <h2>img</h2>
            <div>
                <img 
                    style="width: 600px;"
                    src="${pageContext.request.contextPath}/RandomImageServlet" 
                    alt="Enlace a imagen generada dinámicamente"/>
            </div>
        </section>

        <section>
            <h2>a href</h2>
            <a href="${pageContext.request.contextPath}/RandomImageServlet">Enlace a imagen generada dinámicamente</a>
        </section>
    </body>
</html>
