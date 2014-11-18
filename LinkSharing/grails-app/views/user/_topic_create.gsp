<%@ page import="linksharing.Visibility" %>


<g:message code="user.name.label" default="Name" />
<span class="required-indicator">*</span>
<g:textField name="name" size="15" required="" value="${topicInstance?.name}"/>

<g:message code="user.visibility.label" default="Visibility" />
<span class="required-indicator">*</span>
<g:select from="${Visibility.values()}" name="visibility" required="" value="${topicInstance?.visibility}"/>