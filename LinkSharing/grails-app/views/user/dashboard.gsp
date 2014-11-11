<%@ page import="linksharing.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
    <title><g:message code="default.dashboard.label" args="[entityName]" /></title>
</head>
<body>

<div id="show-user" class="content scaffold-show" role="main">
    <h1><g:message code="default.dashboard.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <div class="header" >
        <g:message code="default.welcome.label" args='["${session["user"].firstName}"]' />
        <g:link controller="login" action="logoutHander" >Logout</g:link>
    </div>

    <div class="right-box">
        <div class="inbox">
            <table>
                <th>header</th>
                <tr><td>row1</td></tr>
                <tr><td>row1</td></tr>
                <tr><td>row1</td></tr>
            </table>

        </div>

    </div>
    <div class="left-box">dsfsdsfdfsd</div>




</div>
</body>
</html>
