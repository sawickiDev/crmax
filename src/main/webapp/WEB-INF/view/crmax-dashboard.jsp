<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ResourceBundle" %>

<%
    ResourceBundle resource = ResourceBundle.getBundle("labels");
    String create = resource.getString("dashboard.create");
    String emptyList = resource.getString("dashboard.emptyList");
    String logout = resource.getString("dashboard.logout");
    String addProduct = resource.getString("dashboard.addProduct");
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
        <link rel="stylesheet" type="text/css" href="/resources/css/dashboard-style.css"/>
    </head>

    <body>

        <div class="slds-page-header
                    dashboard-header
                    slds-grid">

            <div class="logo-type">
                CRMax
            </div>

            <input type="hidden" name="create-client" value="true"/>
            <form:form method="post"
                       action="/logout-page"
                       cssClass="slds-large-size--1-of-12
                                    slds-medium-size--1-of-12
                                    slds-small-size--2-of-12
                                    slds-max-small-size--3-of-12
                                    slds-col_bump-left"
                       cssStyle="margin-bottom:0">
                <button type="submit" class="slds-button neutral-button"><%=logout%></button>
            </form:form>

        </div>

        <div class="slds-size--12-of-12
                    slds-text-align_center
                    slds-hide_small"
             style="background-color: rgba(222, 222, 222, 0.95);color: #505050;">
            <a href="#nav-anchor">Jump to navigation</a>
        </div>

        <div class="slds-grid slds-wrap">
            <div id="nav-anchor"
                 class="slds-large-size--2-of-12
                        slds-medium-size--2-of-12
                        slds-small-size--12-of-12
                        slds-max-small-size--12-of-12
                        slds-large-order--1
                        slds-medium-order--1
                        slds-small-order--3
                        slds-x-small-order--3">
                <nav class="slds-nav-vertical vertical-nav" aria-label="Sub page">
                    <div class="slds-nav-vertical__section slds-wrap">
                        <ul class="slds-max-small-size--12-of-12">
                            <li class="slds-size--12-of-12 slds-nav-vertical__item"><a href="/create-client" class="slds-nav-vertical__action" aria-describedby="entity-header"><%=create%></a></li>
                            <sec:authorize access="hasAuthority('ADMIN_USER_ROLE')">
                                <li class="slds-size--12-of-12 slds-nav-vertical__item"><a href="/products-page" class="slds-nav-vertical__action" aria-describedby="entity-header"><%=addProduct%></a></li>
                            </sec:authorize>
                        </ul>
                    </div>
                </nav>
            </div>
            <div class="slds-grid
                        slds-wrap
                        slds-align_absolute-center
                        slds-large-size--7-of-12
                        slds-medium-size--7-of-12
                        slds-small-size--12-of-12
                        slds-max-small-size--12-of-12
                        slds-order--2">
                <div class="
                        slds-large-size--12-of-12
                        slds-medium-size--12-of-12
                        slds-small-size--12-of-12
                        slds-max-small-size--12-of-12
                        slds-scrollable_y
                        scrollable-pane">
                    <c:if test="${contacts.size() == 0}">
                        <div class="slds-size--6-of-12 empty-prompt slds-align_absolute-center">
                            <i class="material-icons">help</i>
                            <p><%=emptyList%></p>
                        </div>
                    </c:if>
                    <c:forEach var="contact" items="${contacts}">

                        <div class="slds-box
                            slds-grid
                            slds-wrap
                            list-item
                            slds-align_absolute-center
                            slds-m-top_medium
                            slds-large-size--12-of-12
                            slds-medium-size--12-of-12
                            slds-small-size--12-of-12
                            slds-max-small-size--12-of-12">
                            <div class="slds-large-size--1-of-12
                                slds-medium-size--1-of-12
                                slds-small-size--2-of-12
                                slds-max-small-size--2-of-12">
                                <i class="material-icons face-icon">face</i>
                            </div>
                            <div class="slds-grid slds-wrap slds-size--11-of-12">
                                <div class="slds-size--1-of-2
                                slds-text-align_left">
                                    <p class="slds-truncate">${contact.firstName} ${contact.lastName} (${contact.companyName})</p>
                                </div>
                                <div class="slds-size--1-of-2
                                slds-text-align_right">
                                    <p clas="slds-truncate">Value: 1000000 $</p>
                                </div>
                                <div class="slds-size--1-of-2">
                                    <p class="slds-truncate">Newly Created</p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>

            <div class="slds-large-size--2-of-12
                        slds-medium-size--2-of-12
                        slds-large-order--3
                        slds-medium-order--3
                        slds-small-order--1
                        slds-x-small-order--1">
            </div>
        </div>

        <script src="/webjars/jquery/3.2.1/jquery.min.js"></script>
        <script src="/resources/js/dashboard.js"></script>
    </body>
</html>

