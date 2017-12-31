<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ResourceBundle" %>

<%
    ResourceBundle resource = ResourceBundle.getBundle("labels");
    String create = resource.getString("dashboard.create");
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
            <div class="slds-col_bump-left
                        slds-large-size--1-of-12
                        slds-medium-size--1-of-12
                        slds-small-size--2-of-12
                        slds-max-small-size--3-of-12">
                <a  href="/create-client"
                    class="slds-button right-button"><%=create%></a>
            </div>

        </div>

        <div class="slds-grid slds-wrap slds-align_absolute-center">
            <div class="slds-scrollable_y
                        scrollable-pane
                        slds-large-size--7-of-12
                        slds-medium-size--8-of-12
                        slds-small-size--12-of-12
                        slds-max-small-size--12-of-12">
                <div class="slds-box
                            slds-grid
                            slds-wrap
                            slds-align_absolute-center
                            list-item
                            slds-m-top_medium">
                    <div class="slds-large-size--1-of-12
                                slds-medium-size--1-of-12
                                slds-small-size--2-of-12
                                slds-max-small-size--2-of-12">
                        <i class="material-icons face-icon">face</i>
                    </div>
                    <div class="slds-grid slds-wrap slds-size--11-of-12">
                        <div class="slds-size--1-of-2
                                slds-text-align_left">
                            <p class="slds-truncate">Marek Wlodarek (Accenture)</p>
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
            </div>
        </div>

        <script src="/webjars/jquery/3.2.1/jquery.min.js"></script>
        <script src="/resources/js/dashboard.js"></script>
    </body>
</html>

