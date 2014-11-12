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
        <g:message code="default.welcome.label" args='["${user?.firstName}"]' />
        <g:link controller="login" action="logoutHander" >Logout</g:link>
    </div>

    <div class="right-box">
        <g:render template="inbox"></g:render>

    </div>
    <div class="left-box">
        <g:render template="profile"></g:render>

        <div class="trendingTopics">

            <table class="topicTable">
                <th>Trending Topics</th>
                <% trendingTopics.each { topic -> %>
                <tr><td>
               <a href="" class="topicName"> <% println topic.name    %></a><br>
                <a class="topicDetail"><% println "Subscriptions Post"%>
                <br>
                 <% println topic.subscripstions.size()+"   "+topic.resources.size()    %></a>
                </td></tr>

                <% } %>
            </table>

        </div>

    </div>




</div>
</body>
</html>
