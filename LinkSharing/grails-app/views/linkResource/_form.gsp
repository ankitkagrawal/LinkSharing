<%@ page import="linksharing.LinkResource" %>

<div class="fieldcontain ${hasErrors(bean: linkResourceCommandInstance, field: 'title', 'error')} required">
    <label for="title">
        <g:message code="linkResource.title.label" default="Title" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="title" required="" value="${linkResourceCommandInstance?.title}"/>

</div>
<div class="fieldcontain ${hasErrors(bean: linkResourceCommandInstance, field: 'url', 'error')} required">
    <label for="title">
        <g:message code="linkResource.url.label" default="Link" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="url" required="" value="${linkResourceCommandInstance?.url}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: linkResourceCommandInstance, field: 'description', 'error')} required">
	<label for="description">
		<g:message code="linkResource.description.label" default="Description" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="description" cols="20" rows="5" maxlength="1024" required="" value="${linkResourceCommandInstance?.description}"/>

</div>


<div class="fieldcontain ${hasErrors(bean: linkResourceCommandInstance, field: 'topic', 'error')} required">
	<label for="topic">
		<g:message code="linkResource.topic.label" default="Topic" />
		<span class="required-indicator">*</span>
	</label>
    <g:select id="topic" name="topicId" from="${topicList}"  optionKey="id" required="" value="${name}" class="many-to-one"/>

</div>


