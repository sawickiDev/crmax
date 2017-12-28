<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ResourceBundle" %>
<% ResourceBundle resource = ResourceBundle.getBundle("labels");
    String create = resource.getString("dashboard.create"); %>

<html>
    <head>
        <title>CRMax Dashboard</title>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="stylesheet" type="text/css"
              href="/webjars/salesforce-lightning-design-system/2.4.1/assets/styles/salesforce-lightning-design-system.min.css"/>

        <link rel="stylesheet" type="text/css" href="/resources/css/main-style.css"/>
    </head>

    <body>

        <div class="slds-page-header
                    dashboard-header
                    slds-grid">

            <div class="logo-type">
                CRMax
            </div>

            <div class="slds-col_bump-left">
                <button type="submit" class="slds-button create-button"><%=create%></button>
            </div>

        </div>

    </body>
</html>

