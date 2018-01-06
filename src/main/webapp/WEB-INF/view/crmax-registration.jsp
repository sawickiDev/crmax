<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ResourceBundle" %>
<% ResourceBundle resource = ResourceBundle.getBundle("labels");
    String signUp = resource.getString("client_registration.singup");
    String cancel = resource.getString("client_registration.cancel");
    String firstName = resource.getString("client_registration.first_name");
    String lastName = resource.getString("client_registration.last_name");
    String email = resource.getString("client_registration.email");
    String username = resource.getString("client_registration.username");
    String phone = resource.getString("client_registration.phone");
    String supervisor = resource.getString("client_registration.supervisor");
    String password = resource.getString("client_registration.password");
%>

<html>
    <head>
        <title>CRMax Login</title>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="stylesheet" type="text/css"
              href="/webjars/salesforce-lightning-design-system/2.4.1/assets/styles/salesforce-lightning-design-system.min.css"/>

        <link rel="stylesheet" type="text/css" href="/resources/css/login-style.css"/>
        <link rel="stylesheet" type="text/css" href="/resources/css/create-client-form-style.css"/>
        <link rel="stylesheet" type="text/css" href="/resources/css/style-guide.css"/>
    </head>

    <body>

    <form:form action="${pageContext.request.contextPath}/register-user"
               modelAttribute="user"
               method="POST">
        <div class="slds-grid slds-wrap slds-align_absolute-center form-container">

            <c:choose>
                <c:when test="${status == 'SUCCESS'}">
                    <div class="slds-box
                                    success-box
                                    slds-size--12-of-12
                                    slds-m-bottom_medium">
                        <p>User Registered Correct</p>
                    </div>
                </c:when>
                <c:when test="${status == 'WARNING'}">
                    <div class="slds-box
                                    warning-box
                                    slds-size--12-of-12
                                    slds-m-bottom_medium">
                        <p>This User Already Exists</p>
                    </div>
                </c:when>
                <c:when test="${status == 'ERROR'}">
                    <div class="slds-box
                                    error-box
                                    slds-size--12-of-12
                                    slds-m-bottom_medium">
                        <p>Error During User Registering</p>
                    </div>
                </c:when>
            </c:choose>

            <div class="slds-grid slds-wrap slds-align_absolute-center login-form
                        slds-large-size--3-of-12
                        slds-medium-size--5-of-12
                        slds-small-size--7-of-12
                        slds-max-small-size--9-of-12
                        slds-p-around_large">

                <div class="slds-size--12-of-12
                                    slds-m-bottom_x-small">
                    <label class="cr-label" for="first-name-input"><%=firstName%></label>
                    <form:input id="first-name-input"
                                class="slds-input cr-input"
                                type="text"
                                path="firstName"
                                required="true"/>
                    <form:errors path="firstName" cssClass="error-box-validation"/>
                </div>
                <div class="slds-size--12-of-12
                                    slds-m-top_x-small">
                    <label class="cr-label" for="last-name-input"><%=lastName%></label>
                    <form:input id="last-name-input"
                                class="slds-input cr-input"
                                type="text"
                                path="lastName"
                                required="true"/>
                    <form:errors path="lastName" cssClass="error-box-validation"/>
                </div>
                <div class="slds-size--12-of-12
                                    slds-m-top_x-small">
                    <label class="cr-label" for="email-input"><%=email%></label>
                    <form:input id="email-input"
                                class="slds-input cr-input"
                                type="email"
                                path="email"
                                required="true"/>
                    <form:errors path="email" cssClass="error-box-validation"/>
                </div>
                <div class="slds-size--12-of-12
                                        slds-m-top_x-small">
                    <label class="cr-label" for="username-input"><%=username%></label>
                    <form:input id="username-input"
                                class="slds-input cr-input"
                                type="text"
                                path="username"
                                required="true"/>
                    <form:errors path="username" cssClass="error-box-validation"/>
                </div>
                <div class="slds-size--12-of-12
                                    slds-m-top_x-small">
                    <label class="cr-label" for="phone-input"><%=phone%></label>
                    <form:input id="phone-input"
                                class="slds-input cr-input"
                                type="number"
                                path="phoneNumber"/>
                    <form:errors path="phoneNumber" cssClass="error-box-validation"/>
                </div>
                <div class="slds-large-size--12-of-12
                                slds-medium-size--12-of-12
                                slds-small-size--12-of-12
                                slds-max-small-size--12-of-12
                                slds-m-bottom_x-small">
                    <label class="cr-label" for="supervisors"><%=supervisor%></label>
                    <form:select id="supervisors" path="supervisorCache" cssClass="slds-input cr-input">
                        <c:forEach var="admin" items="${admins}" varStatus="loop">
                            <form:option
                                value="${admin.username}"
                                selected="${loop.index == 0 ? 'selected' : ''}">${admin.firstName} ${admin.lastName}</form:option>
                        </c:forEach>
                    </form:select>
                    <form:errors path="supervisorCache" cssClass="error-box-validation"/>
                </div>
                <div class="slds-size--12-of-12
                                    slds-m-top_x-small">
                    <label class="cr-label" for="password-input"><%=password%></label>
                    <form:input id="password-input"
                                class="slds-input cr-input"
                                type="text"
                                path="passwordId"/>
                    <form:errors path="passwordId" cssClass="error-box-validation"/>
                </div>
                <div class="slds-large-size--6-of-12
                            slds-medium-size--6-of-12
                            slds-small-size--12-of-12
                            slds-max-small-size--12-of-12
                            slds-m-top_medium
                            slds-p-around_x-small">
                    <a href="/crmax-login" class="slds-button neutral-button"><%=cancel%></a>
                </div>
                <div class="slds-large-size--6-of-12
                            slds-medium-size--6-of-12
                            slds-small-size--12-of-12
                            slds-max-small-size--12-of-12
                            slds-m-top_medium
                            slds-p-around_x-small">
                    <button type="submit" class="slds-button right-button"><%=signUp%></button>
                </div>

            </div>
        </div>

    </form:form>
    </body>
</html>
