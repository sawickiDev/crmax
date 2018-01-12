<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ResourceBundle" %>
<% ResourceBundle resource = ResourceBundle.getBundle("labels");
    String create = resource.getString("client_detail.create");
    String back = resource.getString("client_detail.cancel");
    String dashboard = resource.getString("create_form.dashboard");
    String success = resource.getString("client_detail.success");
    String error = resource.getString("client_detail.error");
    String cancel = resource.getString("create_form.cancel");
    String firstName = resource.getString("create_form.first_name");
    String lastName = resource.getString("create_form.last_name");
    String email = resource.getString("create_form.email");
    String phone = resource.getString("create_form.phone");
    String companyName = resource.getString("create_form.company_name");
    String warning = resource.getString("create_form.warning");
%>
<html>
<head>
    <title>CRMax Dashboard</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" type="text/css"
          href="/webjars/salesforce-lightning-design-system/2.4.1/assets/styles/salesforce-lightning-design-system.min.css"/>

    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" type="text/css" href="/resources/css/style-guide.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/client-detail-style.css"/>
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
            <a  href="/crmax-dashboard"
                class="slds-button right-button"><%=dashboard%></a>
        </div>

    </div>

    <div class="slds-grid slds-wrap">

        <div class="slds-grid slds-wrap
                    slds-m-top_medium
                    slds-large-size--12-of-12
                    slds-medium-size--12-of-12
                    slds-small-size--12-of-12
                    slds-max-small-size--12-of-12">

            <c:choose>
                <c:when test="${status == 'SUCCESS'}">
                    <div class="slds-box
                                    success-box
                                    slds-size--12-of-12
                                    slds-m-bottom_medium">
                        <p>${successMessage}</p>
                    </div>
                </c:when>
                <c:when test="${status == 'ERROR'}">
                    <div class="slds-box
                                    error-box
                                    slds-size--12-of-12
                                    slds-m-bottom_medium">
                        <p>${errorMessage}</p>
                    </div>
                </c:when>
            </c:choose>
            <div class="slds-grid
                    slds-wrap
                    login-form
                    slds-text-align_center
                    slds-large-size--4-of-12
                    slds-medium-size--4-of-12
                    slds-small-size--12-of-12
                    slds-max-small-size--12-of-12
                    slds-p-around--xx-large"
                    style="height:450px;">
                <div class="slds-size--12-of-12">
                    <i class="material-icons face-icon">face</i>
                </div>
                <div class="slds-size--12-of-12">
                    <p class="slds-truncate details-text">First Name: ${contact.firstName}</p>
                </div>
                <div class="slds-size--12-of-12">
                    <p class="slds-truncate details-text">Last Name: ${contact.lastName}</p>
                </div>
                <div class="slds-size--12-of-12">
                    <p class="slds-truncate details-text">Email: ${contact.email}</p>
                </div>
                <div class="slds-size--12-of-12">
                    <p class="slds- details-text">Phone: ${contact.phone}</p>
                </div>
                <div class="slds-size--12-of-12">
                    <p class="slds-truncate details-text">Company Name: ${contact.companyName}</p>
                </div>
                <sec:authorize access="hasAuthority('ADMIN_USER_ROLE')">
                    <div class="slds-size--6-of-12
                                slds-m-top_medium
                                slds-p-around_x-small
                                slds-align_absolute-center ">
                        <a href="/delete-client?clientEmail=${client.email}"
                           class="slds-button neutral-button">Delete</a>
                    </div>
                </sec:authorize>
            </div>
            <form:form action="${pageContext.request.contextPath}/save-interaction"
                       method="POST"
                       cssClass="slds-grid
                            slds-wrap
                            login-form
                            slds-large-size--4-of-12
                            slds-medium-size--4-of-12
                            slds-small-size--12-of-12
                            slds-max-small-size--12-of-12
                            slds-p-around_large"
                       cssStyle="margin:0;height:450px;"
                       modelAttribute="interaction">
                <div class="slds-size--12-of-12
                            slds-text-align_center">
                    <p class="header-form">Create Interaction</p>
                </div>
                <input type="hidden" name="contactEmail" value="${contact.email}"/>
                <div class="slds-large-size--12-of-12
                                slds-medium-size--12-of-12
                                slds-small-size--12-of-12
                                slds-max-small-size--12-of-12
                                slds-m-bottom_x-small">
                    <label class="cr-label" for="stages">Stage</label>
                    <form:select id="stages" path="stage" cssClass="slds-input cr-input">
                        <c:forEach var="stage" items="${stages}" varStatus="loop">
                            <form:option
                                    value="${stage}"
                                    selected="${loop.index == 0 ? 'selected' : ''}">${stage}</form:option>
                        </c:forEach>
                    </form:select>
                    <form:errors path="stage" cssClass="error-box-validation"/>
                </div>
                <div class="slds-large-size--12-of-12
                                slds-medium-size--12-of-12
                                slds-small-size--12-of-12
                                slds-max-small-size--12-of-12
                                slds-m-bottom_x-small">
                    <label class="cr-label" for="start-date-input">Start Date</label>
                    <form:input id="start-date-input"
                                class="slds-input cr-input"
                                type="date"
                                path="startDate"
                                required="true"/>
                    <form:errors path="startDate" cssClass="error-box-validation"/>
                </div>
                <div class="slds-large-size--12-of-12
                                slds-medium-size--12-of-12
                                slds-small-size--12-of-12
                                slds-max-small-size--12-of-12
                                slds-m-bottom_x-small">
                    <label class="cr-label" for="end-date-input">End Date</label>
                    <form:input id="end-date-input"
                                class="slds-input cr-input"
                                type="date"
                                path="endDate"
                                required="true"/>
                    <form:errors path="endDate" cssClass="error-box-validation"/>
                </div>
                <div class="slds-large-size--12-of-12
                                        slds-medium-size--12-of-12
                                        slds-small-size--12-of-12
                                        slds-max-small-size--12-of-12
                                        slds-m-bottom_x-small">
                    <label class="cr-label" for="products-picklist">Products</label>
                    <form:select id="products-picklist"
                                 path="productsSelected"
                                 cssClass="slds-input cr-input"
                                 multiple="true"
                                 size="4">
                        <c:forEach var="product" items="${products}" varStatus="loop">
                            <form:option
                                    value="${product.id}">${product.productCode} (${product.price})$</form:option>
                        </c:forEach>
                    </form:select>
                    <form:errors path="stage" cssClass="error-box-validation"/>
                </div>

                <div class="slds-size--6-of-12
                            slds-m-top_medium
                            slds-p-around_x-small
                            slds-align_absolute-center">
                    <button type="submit" class="slds-button right-button"><%=create%></button>
                </div>
            </form:form>
            <form:form action="${pageContext.request.contextPath}/update-client"
                       method="POST"
                       cssClass="slds-grid
                            slds-wrap
                            login-form
                            slds-large-size--4-of-12
                            slds-medium-size--4-of-12
                            slds-small-size--12-of-12
                            slds-max-small-size--12-of-12
                            slds-p-around_large"
                       cssStyle="margin:0;height:450px;"
                       modelAttribute="client">
                <div class="slds-size--12-of-12
                            slds-text-align_center">
                    <p class="header-form">Update Client Data</p>
                </div>
                <input type="hidden" name="contactEmail" value="${contact.email}"/>
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
                <sec:authorize access="hasAuthority('ADMIN_USER_ROLE')">
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
                </sec:authorize>
                <sec:authorize access="hasAuthority('STANDARD_USER_ROLE')">
                    <form:input id="email-input"
                                class="slds-input cr-input"
                                type="hidden"
                                path="email"
                                value="${contact.email}"
                                required="true"/>
                </sec:authorize>
                <div class="slds-size--12-of-12
                                    slds-m-top_x-small">
                    <label class="cr-label" for="phone-input"><%=phone%></label>
                    <form:input id="phone-input"
                                class="slds-input cr-input"
                                type="number"
                                path="phone"/>
                    <form:errors path="phone" cssClass="error-box-validation"/>
                </div>
                <div class="slds-size--12-of-12
                                    slds-m-top_x-small">
                    <label class="cr-label" for="company-name-input"><%=companyName%></label>
                    <form:input id="company-name-input"
                                class="slds-input cr-input"
                                type="text"
                                path="companyName"/>
                    <form:errors path="companyName" cssClass="error-box-validation"/>
                </div>

                <div class="slds-size--6-of-12
                            slds-m-top_medium
                            slds-p-around_x-small
                            slds-align_absolute-center ">
                    <button type="submit" class="slds-button right-button">Update</button>
                </div>
            </form:form>
        </div>
        <div class="slds-size--12-of-12">
            <div class="slds-grid
                        slds-wrap
                        slds-align_absolute-center
                        slds-large-size--12-of-12
                        slds-medium-size--12-of-12
                        slds-small-size--12-of-12
                        slds-max-small-size--12-of-12
                        slds-order--2
                        slds-p-around--small">
                <div class="slds-size--12-of-12
                            slds-text-align_center">
                    <p class="header-text">Interactions</p>
                </div>
                <div class="slds-box
                            list-item
                            slds-align_absolute-center
                            slds-grid
                            slds-wrap
                            slds-size--12-of-12"
                     style="background-color: rgba(194, 199, 204, 0.48);">
                    <div class="slds-size--1-of-4
                                slds-text-align_left">
                        <p class="slds-truncate">Stage</p>
                    </div>
                    <div class="slds-size--1-of-4">
                        <p clas="slds-truncate">Start Date</p>
                    </div>
                    <div class="slds-size--1-of-4">
                        <p class="slds-truncate">End Date</p>
                    </div>
                    <div class="slds-size--1-of-4">
                        <p class="slds-truncate">Products</p>
                    </div>
                </div>
                <div class="
                        slds-large-size--12-of-12
                        slds-medium-size--12-of-12
                        slds-small-size--12-of-12
                        slds-max-small-size--12-of-12
                        slds-scrollable_y
                        scrollable-pane"
                        style="height:200px">

                    <c:if test="${interactions.size() == 0}">
                        <div class="slds-size--6-of-12
                                        empty-prompt
                                        slds-align_absolute-center"
                             style="height:100%">
                            <i class="material-icons">help</i>
                            <p>Empty List</p>
                        </div>
                    </c:if>
                    <c:forEach var="interaction" items="${interactions}">
                        <div class="slds-box
                                        list-item
                                        slds-align_absolute-center
                                        slds-grid
                                        slds-wrap
                                        slds-size--12-of-12">
                            <div class="slds-size--1-of-4
                                slds-text-align_left">
                                <p class="slds-truncate">${interaction.stage}</p>
                            </div>
                            <div class="slds-size--1-of-4">
                                <p class="slds-truncate">${interaction.startDate}</p>
                            </div>
                            <div class="slds-size--1-of-4">
                                <p class="slds-truncate">${interaction.endDate}</p>
                            </div>
                            <div class="slds-size--1-of-4">
                                <p class="slds-truncate">${interaction.products}</p>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
