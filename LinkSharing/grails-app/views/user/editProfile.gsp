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

    <div class="right-box">

        <div class="editProfile">
            <g:hasErrors bean="${userCommandInstance}">
                <ul class="errors" role="alert">
                    <g:eachError bean="${userCommandInstance}" var="error">
                        <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                    </g:eachError>
                </ul>
            </g:hasErrors>

            <g:form controller="user" action="update" enctype="multipart/form-data">
            <g:render template="edit_details"/>
            </g:form>
        </div>

        <div class="changePassword">
            <g:form controller="user" action="changePassword">
            <g:render template="change_password"/>
            </g:form>
        </div>

    </div>
    <div class="left-box">
        <g:render template="profile"></g:render>

        <div class="trendingTopics">
            <ls:showTopics topicType="Topics" topicList="${topicList}"></ls:showTopics>

        </div>

    </div>
</div>
</body>
</html>
