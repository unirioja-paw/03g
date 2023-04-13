<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PAW</title>
        <link href="assets/css/style.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <h1>Contador de visitas</h1>

        <section>
            <h2>Visitas por URL</h2>
            <c:forEach items="${statsCounter}" var="entry">
                <div style="margin: 30px 0; ">
                    <h3>URL: ${entry.key}</h3>
                    <div style="color: deepskyblue; font-weight: bold; margin: 4px 0;">
                        <label>Visitas: </label>
                        ${entry.value} hits
                    </div>
                </div>
            </c:forEach>
        </section>

    </body>
</html>
