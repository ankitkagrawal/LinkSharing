<%@ page import="linksharing.Visibility" %>

%{--<div class="fieldcontain ${hasErrors(bean: topicInstance, field: 'name', 'error')} required">
    <label for="name">
        <g:message code="user.name.label" default="Name" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="name" required="" value="${topicInstance?.name}"/>

</div>
<div class="fieldcontain ${hasErrors(bean: topicInstance, field: 'visibility', 'error')} required">
    <label for="visibility">
        <g:message code="user.visibility.label" default="Visibility" />
        <span class="required-indicator">*</span>
    </label>
    <g:select from="${Visibility.values()}" name="visibility" required="" value="${topicInstance?.visibility}"/>

</div>--}%



<g:message code="user.name.label" default="Name" />
<span class="required-indicator">*</span>
<g:textField name="name" size="15" required="" value="${topicInstance?.name}"/>

<g:message code="user.visibility.label" default="Visibility" />
<span class="required-indicator">*</span>
<g:select from="${Visibility.values()}" name="visibility" required="" value="${topicInstance?.visibility}"/>