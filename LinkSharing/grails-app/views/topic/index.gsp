<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'login.label', default: 'Login')}" />
    <title><g:message code="default.topic.label" args="[entityName]" /></title>
</head>
<body>


<div id="create-login" class="content scaffold-create" role="main">
    <h1><g:message code="default.topic.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
</div>
%{--<div class="header" >
    <g:message code="default.welcome.label" args='["${session["user"].firstName}"]' />
    <g:link controller="login" action="logoutHander" >Logout</g:link>
</div>--}%

<div class="right-box">
<g:render template="post_list"></g:render>

</div>
<div class="left-box" >
        <g:render template="topic_details"></g:render>
    <div class="trendingTopics">
        <g:render template="topic_user_list"></g:render>
    </div>
</div>
</body>
</html>