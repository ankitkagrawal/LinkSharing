<%--
  Created by IntelliJ IDEA.
  User: ankit
  Date: 19/11/14
  Time: 1:55 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Invite</title>
</head>

<body>

<g:form controller="mail" action="sendMail">

    <g:textField name="recipient" ></g:textField>
    <g:select name="id" from="${topicList}" optionKey="id" value="${topicId}"/>
    <g:submitButton name="submit" value="Send Invite"  />
</g:form>




</body>
</html>