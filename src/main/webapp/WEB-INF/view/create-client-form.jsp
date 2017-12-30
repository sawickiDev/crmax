<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ResourceBundle" %>
<% ResourceBundle resource = ResourceBundle.getBundle("labels");
    String create = resource.getString("create_form.create");
    String cancel = resource.getString("create_form.cancel");
    String firstName = resource.getString("create_form.first_name");
    String lastName = resource.getString("create_form.last_name");
    String email = resource.getString("create_form.email");
    String phone = resource.getString("create_form.phone");
    String companyName = resource.getString("create_form.company_name");
    String dashboard = resource.getString("create_form.dashboard");
    String success = resource.getString("create_form.success");
    String warning = resource.getString("create_form.warning");
    String error = resource.getString("create_form.error");%>
<html>
<head>
    <title>CRMax Dashboard</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" type="text/css"
          href="/webjars/salesforce-lightning-design-system/2.4.1/assets/styles/salesforce-lightning-design-system.min.css"/>

    <link rel="stylesheet" type="text/css" href="/resources/css/create-client-form-style.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/style-guide.css"/>
</head>
<body>

    <div class="slds-page-header
                dashboard-header
                slds-grid">

        <div class="logo-type">
            CRMax
        </div>

        <input type="hidden" name="create-client" value="true"/>
        <div class="slds-col_bump-left
                            slds-large-size--1-of-12
                            slds-medium-size--1-of-12
                            slds-small-size--2-of-12
                            slds-max-small-size--3-of-12">
            <a  href="/create-client"
                class="slds-button right-button"><%=dashboard%></a>
        </div>

    </div>

    <form:form action="${pageContext.request.contextPath}/save-client"
               method="POST"
               modelAttribute="client">
        <div class="slds-grid slds-wrap slds-align_absolute-center form-container">
            <div class="slds-grid slds-wrap slds-align_absolute-center login-form
                        slds-large-size--3-of-12
                        slds-medium-size--5-of-12
                        slds-small-size--7-of-12
                        slds-max-small-size--9-of-12
                        slds-p-around_large">

                <c:choose>
                    <c:when test="${status == 'SUCCESS'}">
                        <div class="slds-box
                                    success-box
                                    slds-size--12-of-12
                                    slds-m-bottom_medium">
                            <p><%=success%></p>
                        </div>
                    </c:when>
                    <c:when test="${status == 'WARNING'}">
                        <div class="slds-box
                                    warning-box
                                    slds-size--12-of-12
                                    slds-m-bottom_medium">
                            <p><%=warning%></p>
                        </div>
                    </c:when>
                    <c:when test="${status == 'ERROR'}">
                        <div class="slds-box
                                    error-box
                                    slds-size--12-of-12
                                    slds-m-bottom_medium">
                            <p><%=error%></p>
                        </div>
                    </c:when>
                </c:choose>

                <div class="slds-size--12-of-12
                                    slds-m-bottom_x-small">
                    <label class="cr-label" for="first-name-input"><%=firstName%></label>
                    <input id="first-name-input"
                           class="slds-input cr-input"
                           type="text"
                           name="firstName"/>
                </div>
                <div class="slds-size--12-of-12
                                    slds-m-top_x-small">
                    <label class="cr-label" for="last-name-input"><%=lastName%></label>
                    <input id="last-name-input"
                           class="slds-input cr-input"
                           type="text"
                           name="lastName"/>
                </div>
                <div class="slds-size--12-of-12
                                    slds-m-top_x-small">
                    <label class="cr-label" for="email-input"><%=email%></label>
                    <input id="email-input"
                           class="slds-input cr-input"
                           type="email"
                           name="email"/>
                </div>
                <div class="slds-size--12-of-12
                                    slds-m-top_x-small">
                    <label class="cr-label" for="phone-input"><%=phone%></label>
                    <input id="phone-input"
                           class="slds-input cr-input"
                           type="text"
                           name="phone"/>
                </div>
                <div class="slds-size--12-of-12
                                    slds-m-top_x-small">
                    <label class="cr-label" for="company-name-input"><%=companyName%></label>
                    <input id="company-name-input"
                           class="slds-input cr-input"
                           type="text"
                           name="companyName"/>
                </div>

                <div class="slds-size--6-of-12
                            slds-m-top_medium
                            slds-p-around_x-small">
                    <a href="/crmax-dashboard" class="slds-button neutral-button"><%=cancel%></a>
                </div>
                <div class="slds-size--6-of-12
                            slds-m-top_medium
                            slds-p-around_x-small">
                    <button type="submit" class="slds-button right-button"><%=create%></button>
                </div>

            </div>
        </div>

    </form:form>

</body>
</html>
