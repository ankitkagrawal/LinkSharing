<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'login.label', default: 'Login')}" />
    <title><g:message code="default.login.label" args="[entityName]" /></title>
</head>
<body>


<div id="create-login" class="content scaffold-create" role="main">
    <h1><g:message code="default.topic.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
</div>


<div class="right-box">
<g:render template="post_list"></g:render>

</div>
<div class="left-box" >
    <div class="profile">
        %{--<g:render template="topicDetails"></g:render>--}%
    </div>
    <div class="trendingTopics">
        %{--<g:render template="topicUserList"></g:render>--}%
    </div>
</div>
</body>
</html>