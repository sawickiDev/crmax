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
    String companyName = resource.getString("create_form.company_name");%>
<html>
<head>
    <title>CRMax Dashboard</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" type="text/css"
          href="/webjars/salesforce-lightning-design-system/2.4.1/assets/styles/salesforce-lightning-design-system.min.css"/>

    <link rel="stylesheet" type="text/css" href="/resources/css/create-client-form-style.css"/>
</head>
<body>
    <section role="dialog" tabindex="-1" class="slds-modal slds-fade-in-open">
        <div class="slds-modal__container">
            <header class="slds-modal__header contact-form-header">
                <h2 class="slds-text-heading_medium slds-hyphenate">Create Client</h2>
            </header>
            <div class="slds-grid slds-wrap slds-modal__content slds-p-around_medium">
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
            </div>
            <footer class="slds-modal__footer contact-form-footer">
                <button class="slds-button slds-button_neutral"><%=cancel%></button>
                <button class="slds-button slds-button_brand"><%=create%></button>
            </footer>
        </div>
    </section>
    <div class="slds-backdrop slds-backdrop_open"></div>
</body>
</html>
