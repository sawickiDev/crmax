<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ResourceBundle" %>
<% ResourceBundle resource = ResourceBundle.getBundle("labels");
    String username = resource.getString("login.username");
    String password = resource.getString("login.password");
    String error = resource.getString("login.error"); %>

<html>
    <head>
        <title>CRMax Login</title>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="stylesheet" type="text/css"
              href="/webjars/salesforce-lightning-design-system/2.4.1/assets/styles/salesforce-lightning-design-system.min.css"/>

        <link rel="stylesheet" type="text/css" href="/resources/css/login-style.css"/>
        <link rel="stylesheet" type="text/css" href="/resources/css/style-guide.css"/>
    </head>

    <body>

    <form:form action="${pageContext.request.contextPath}/crmax-auth"
               method="POST">
        <div class="slds-grid slds-wrap slds-align_absolute-center form-container">

            <div class="slds-grid slds-wrap slds-align_absolute-center login-form
                        slds-large-size--3-of-12
                        slds-medium-size--5-of-12
                        slds-small-size--7-of-12
                        slds-max-small-size--9-of-12
                        slds-p-around_large">

                    <div class="slds-size--12-of-12
                                slds-m-bottom_x-small">
                        <label for="username-input"/>
                        <input id="username-input"
                               class="slds-input cr-input"
                               type="text"
                               name="username"
                               placeholder="<%=username%>"/>
                    </div>
                    <div class="slds-size--12-of-12
                                slds-m-top_x-small">
                        <label for="password-input"/>
                        <input id="password-input"
                               class="slds-input
                           cr-input"
                               type="password"
                               placeholder="<%=password%>"
                               name="password"/>
                    </div>
                    <c:if test="${param.error != null}">
                        <div class="slds-box
                        <div class="slds-box
                                    error-box
                                    slds-size--12-of-12
                                    slds-m-top_medium">
                            <p><%=error%></p>
                        </div>
                    </c:if>
                    <div class="slds-large-size--6-of-12
                                slds-medium-size--6-of-12
                                slds-small-size--12-of-12
                                slds-max-small-size--12-of-12
                                slds-m-top_medium
                                slds-p-around_x-small">
                        <a href="/crmax-register" class="slds-button left-button">Sign up</a>
                    </div>
                    <div class="slds-large-size--6-of-12
                                slds-medium-size--6-of-12
                                slds-small-size--12-of-12
                                slds-max-small-size--12-of-12
                                slds-m-top_medium
                                slds-p-around_x-small">
                        <button type="submit" class="slds-button right-button">Sign in</button>
                    </div>

            </div>
        </div>

    </form:form>
    </body>
</html>
