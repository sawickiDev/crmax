<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ResourceBundle" %>
<% ResourceBundle resource = ResourceBundle.getBundle("labels");
    String add = resource.getString("products_page.add");
    String back = resource.getString("products_page.back");
    String productName = resource.getString("products_page.product_name");
    String productCode = resource.getString("products_page.product_code");
    String error = resource.getString("products_page.error");
    String success = resource.getString("products_page.success");
    String warning = resource.getString("products_page.warning");
    String dashboard = resource.getString("create_form.dashboard");
    String price = resource.getString("products_page.price");%>
<html>
<head>
    <title>CRMax Dashboard</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" type="text/css"
          href="/webjars/salesforce-lightning-design-system/2.4.1/assets/styles/salesforce-lightning-design-system.min.css"/>

    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" type="text/css" href="/resources/css/products-page-style.css"/>
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
            <a  href="/crmax-dashboard"
                class="slds-button right-button"><%=dashboard%></a>
        </div>

    </div>

    <div class="slds-grid slds-wrap slds-align_absolute-center">
            <form:form action="${pageContext.request.contextPath}/save-product"
                       method="POST"
                       cssClass="slds-grid
                                slds-wrap
                                login-form
                                slds-align_absolute-center
                                slds-large-size--4-of-12
                                slds-medium-size--4-of-12
                                slds-small-size--12-of-12
                                slds-max-small-size--12-of-12
                                slds-p-around_large
                                slds-m-top_medium"
                       modelAttribute="product">
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
                        <div class="slds-large-size--12-of-12
                                    slds-medium-size--12-of-12
                                    slds-small-size--12-of-12
                                    slds-max-small-size--12-of-12
                                    slds-m-bottom_x-small">
                            <label class="cr-label" for="product-name-input"><%=productName%></label>
                            <form:input id="product-name-input"
                                        class="slds-input cr-input"
                                        type="text"
                                        path="productName"
                                        required="true"/>
                            <form:errors path="productName" cssClass="error-box-validation"/>
                        </div>
                        <div class="slds-large-size--12-of-12
                                    slds-medium-size--12-of-12
                                    slds-small-size--12-of-12
                                    slds-max-small-size--12-of-12
                                    slds-m-bottom_x-small">
                            <label class="cr-label" for="product-code-input"><%=productCode%></label>
                            <form:input id="product-code-input"
                                        class="slds-input cr-input"
                                        type="text"
                                        path="productCode"
                                        required="true"/>
                            <form:errors path="productCode" cssClass="error-box-validation"/>
                        </div>
                        <div class="slds-large-size--12-of-12
                                    slds-medium-size--12-of-12
                                    slds-small-size--12-of-12
                                    slds-max-small-size--12-of-12
                                    slds-m-bottom_x-small">
                            <label class="cr-label" for="price-input"><%=price%></label>
                            <form:input id="price-input"
                                        class="slds-input cr-input"
                                        type="text"
                                        path="price"
                                        required="true"/>
                            <form:errors path="price" cssClass="error-box-validation"/>
                        </div>

                        <form:input type="hidden"
                                    path="active"
                                    value="true"
                                    required="true"/>

                        <div class="slds-size--6-of-12
                                slds-m-top_medium
                                slds-p-around_x-small">
                            <a href="/crmax-dashboard" class="slds-button neutral-button"><%=back%></a>
                        </div>
                        <div class="slds-size--6-of-12
                                slds-m-top_medium
                                slds-p-around_x-small">
                            <button type="submit" class="slds-button right-button"><%=add%></button>
                        </div>
            </form:form>

        <div class="slds-size--12-of-12">
            <div class="slds-grid
                        slds-wrap
                        slds-large-size--12-of-12
                        slds-medium-size--12-of-12
                        slds-small-size--12-of-12
                        slds-max-small-size--12-of-12
                        slds-order--2
                        slds-p-around--small">
                <div class="slds-size--12-of-12
                            slds-text-align_center">
                    <p class="header-text">Available Products</p>
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
                        <p class="slds-truncate"><%=productName%></p>
                    </div>
                    <div class="slds-size--1-of-4">
                        <p clas="slds-truncate"><%=productCode%></p>
                    </div>
                    <div class="slds-size--1-of-4">
                        <p class="slds-truncate"><%=price%></p>
                    </div>
                    <div class="slds-size--1-of-4">
                        <p class="slds-truncate">Actions</p>
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

                        <c:if test="${products.size() == 0}">
                            <div class="slds-size--6-of-12
                                        empty-prompt
                                        slds-align_absolute-center"
                                style="height:100%">
                                <i class="material-icons">help</i>
                                <p>Empty List</p>
                            </div>
                        </c:if>

                        <c:forEach var="product" items="${products}">
                            <div class="slds-box
                                        list-item
                                        slds-align_absolute-center
                                        slds-grid
                                        slds-wrap
                                        slds-size--12-of-12">
                                <div class="slds-size--1-of-4
                                slds-text-align_left">
                                    <p class="slds-truncate">${product.productName}</p>
                                </div>
                                <div class="slds-size--1-of-4">
                                    <p clas="slds-truncate">${product.productCode}</p>
                                </div>
                                <div class="slds-size--1-of-4">
                                    <p class="slds-truncate">${product.price}$</p>
                                </div>
                                <div class="slds-size--1-of-4">
                                    <a href="/remove-product?productId=${product.id}">Remove</a>
                                </div>
                            </div>
                        </c:forEach>
                </div>
            </div>
        </div>
    </div>

</body>
</html>
