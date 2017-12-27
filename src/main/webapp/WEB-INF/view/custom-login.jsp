<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>CRMax Login</title>
    </head>
    <body>

        <form:form action="${pageContext.request.contextPath}/crmax-auth"
                   method="POST">

            <label for="username">
                Username:
                <input id="username" type="text" name="username"/>
            </label>

            <label for="password">
                Password:
                <input id="password" type="password" name="password"/>
            </label>

            <c:if test="${param.error != null}">
                <p>ERROR</p>
            </c:if>

            <input type="submit" value="Login"/>

        </form:form>

    </body>
</html>
