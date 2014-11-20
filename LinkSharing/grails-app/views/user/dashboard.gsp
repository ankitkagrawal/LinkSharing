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
    %{--<div class="header" >
        <g:message code="default.welcome.label" args='["${user?.firstName}"]' />
        <g:link controller="login" action="logoutHander" >Logout</g:link>
    </div>--}%

    <div class="right-box">
        <g:render template="inbox"></g:render>
    </div>
    <div class="left-box">
        <g:render template="profile"></g:render>

        <div class="trendingTopics">
           <g:render template="trending_topic"></g:render>
        </div>
        <div class="trendingTopics">
            <g:render template="subscriptions"></g:render>
        </div>

        <div class="trendingTopics">

            <table class="topicTable">
                <th>Create Topic</th>

                <tr><td>
                    <g:hasErrors bean="${topicInstance}">
                        <ul class="errors" role="alert">
                            <g:eachError bean="${topicInstance}" var="error">
                                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                            </g:eachError>
                        </ul>
                    </g:hasErrors>
                </td></tr>
                <tr><td>
                <g:form controller="topic" action="save" >
                    %{--<fieldset class="form">--}%
                        <g:render template="topic_create"/>
                    %{--</fieldset>--}%
                    %{--<fieldset class="buttons">--}%
                        <g:submitButton name="create" class="save" value="${message(code: 'default.register.label1', default: 'Create')}" />
                    %{--</fieldset>--}%
                </g:form>
                </td></tr>
                <tr><td>
                </td></tr>

            </table>

        </div>
    </div>
</div>
</body>
</html>
