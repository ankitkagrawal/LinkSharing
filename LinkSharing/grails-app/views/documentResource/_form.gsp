<%@ page import="linksharing.DocumentResource" %>
<%@ page import="linksharing.DocumentResourceCommand" %>

<div class="fieldcontain ${hasErrors(bean: documentResourceCommandInstance, field: 'title', 'error')} required">
    <label for="title">
        <g:message code="documentResource.title.label" default="Title" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="title" required="" value="${documentResourceCommandInstance?.title}"/>

</div>
<div class="fieldcontain ${hasErrors(bean: documentResourceCommandInstance, field: 'url', 'error')} required">
    <label for="title">
        <g:message code="documentResource.document.label" default="Document" />
        <span class="required-indicator">*</span>
    </label>
    %{--<g:i name="url" required="" value="${documentResourceCommandInstance?.url}"/>--}%
    <input type="file" id="document" name="document" value="${documentResourceCommandInstance?.document}" />
</div>

<div class="fieldcontain ${hasErrors(bean: documentResourceCommandInstance, field: 'description', 'error')} required">
    <label for="description">
        <g:message code="documentResource.description.label" default="Description" />
        <span class="required-indicator">*</span>
    </label>
    <g:textArea name="description" cols="20" rows="5" maxlength="1024" required="" value="${documentResourceCommandInstance?.description}"/>

</div>


<div class="fieldcontain ${hasErrors(bean: documentResourceCommandInstance, field: 'topic', 'error')} required">
    <label for="topic">
        <g:message code="documentResource.topic.label" default="Topic" />
        <span class="required-indicator">*</span>
    </label>
    <g:select id="topic" name="topicId" from="${topicList}"  optionKey="id" required="" value="${name}" class="many-to-one"/>

</div>