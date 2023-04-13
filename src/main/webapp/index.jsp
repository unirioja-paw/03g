<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PAW</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <p>This is a JSP page</p>

        Server Version: <%= application.getServerInfo()%>
        <br>
        Context path: <%= application.getContextPath()%>
        <br>
        Servlet Version: <%= application.getMajorVersion()%>.<%= application.getMinorVersion()%>
        <br>
        JSP Version: <%= JspFactory.getDefaultFactory().getEngineInfo().getSpecificationVersion()%> <br>


    </body>
</html>
