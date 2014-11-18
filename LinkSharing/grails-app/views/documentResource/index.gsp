
<%@ page import="linksharing.DocumentResource" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'documentResource.label', default: 'DocumentResource')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-documentResource" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-documentResource" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="description" title="${message(code: 'documentResource.description.label', default: 'Description')}" />
					
						<g:sortableColumn property="title" title="${message(code: 'documentResource.title.label', default: 'Title')}" />
					
						<th><g:message code="documentResource.user.label" default="User" /></th>
					
						<g:sortableColumn property="path" title="${message(code: 'documentResource.path.label', default: 'Path')}" />
					
						<th><g:message code="documentResource.topic.label" default="Topic" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${documentResourceInstanceList}" status="i" var="documentResourceInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${documentResourceInstance.id}">${fieldValue(bean: documentResourceInstance, field: "description")}</g:link></td>
					
						<td>${fieldValue(bean: documentResourceInstance, field: "title")}</td>
					
						<td>${fieldValue(bean: documentResourceInstance, field: "user")}</td>
					
						<td>${fieldValue(bean: documentResourceInstance, field: "path")}</td>
					
						<td>${fieldValue(bean: documentResourceInstance, field: "topic")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${documentResourceInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
