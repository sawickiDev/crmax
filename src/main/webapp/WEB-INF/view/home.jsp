<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>hello-page</title>
</head>
<body>

    <h1>Hello CRMAX</h1>

    <c:forEach var="item" items="${users}">
        <p>${item.firstName}</p>
    </c:forEach>

</body>
</html>
