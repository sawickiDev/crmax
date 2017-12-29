<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ResourceBundle" %>
<c:set var="showForm" value="false"/>
<% ResourceBundle resource = ResourceBundle.getBundle("labels");
    String create = resource.getString("dashboard.create");
    Boolean showForm = false;
    System.out.println("create-client");
    System.out.println(request.getParameter("create-client"));
    String createClientParam = request.getParameter("create-client");
    if(createClientParam != null && createClientParam.equals("true")){
        System.out.println("hello");
        showForm = true;
        pageContext.setAttribute("showForm", true);
    }
    %>

<html>
    <head>
        <title>CRMax Dashboard</title>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="stylesheet" type="text/css"
              href="/webjars/salesforce-lightning-design-system/2.4.1/assets/styles/salesforce-lightning-design-system.min.css"/>

        <link rel="stylesheet" type="text/css" href="/resources/css/dashboard-style.css"/>
    </head>

    <body>

        <form:form action="./crmax-dashboard" method="GET">
            <div class="slds-page-header
                        dashboard-header
                        slds-grid">

                <div class="logo-type">
                    CRMax
                </div>

                    <input type="hidden" name="create-client" value="true"/>
                    <div class="slds-col_bump-left">
                        <button type="submit"
                                class="slds-button create-button"
                                onclick="showCreateClientForm();"><%=create%></button>
                    </div>

            </div>
        </form:form>
        <c:if test="${showForm == true}">
            <jsp:include page="create-client-form.jsp" flush="true"/>
        </c:if>
        <script src="/webjars/jquery/3.2.1/jquery.min.js"></script>
        <script src="/resources/js/dashboard.js"></script>
    </body>
</html>

