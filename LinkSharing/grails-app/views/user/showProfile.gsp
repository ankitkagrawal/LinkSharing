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
        <g:render template="posts"></g:render>
    </div>
    <div class="left-box">
        <g:render template="profile"></g:render>

        <div class="trendingTopics">
            <ls:showTopics topicType="Topics Created" topicList="${subscribedTopicList}"></ls:showTopics>

        </div>

    </div>
</div>
</body>
</html>
