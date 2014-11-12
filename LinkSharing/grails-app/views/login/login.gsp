<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'login.label', default: 'Login')}" />
    <title><g:message code="default.login.label" args="[entityName]" /></title>
</head>
<body>


<div id="create-login" class="content scaffold-create" role="main">
    <h1><g:message code="default.login.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${loginInstance}">
        <ul class="errors" role="alert">
            <g:eachError bean="${loginInstance}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:form controller="login" action="index" >
        <fieldset class="form">
            <g:render template="form"/>
        </fieldset>
        <fieldset class="buttons">
            <g:submitButton name="Submit" class="save" value="${message(code: 'default.button.login.label', default: 'Submit')}" />
            <input type="reset" value="Reset"/>
        </fieldset>
    </g:form>
</div>

<!-- ------------------------    Register           ------------------------ -->

<div id="create-user" class="content scaffold-create" role="main">
    <h1><g:message code="default.register.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${userInstance}">
        <ul class="errors" role="alert">
            <g:eachError bean="${userInstance}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:form controller="user" action='save' >
        <fieldset class="form">
            <g:render template="form_register"/>
        </fieldset>
        <fieldset class="buttons">
            <g:submitButton name="create" class="save" value="${message(code: 'default.register.label', default: 'Create')}" />
            <input type="reset" value="Reset"/>
        </fieldset>
    </g:form>
</div>


<!-- ------------------------    Register           ------------------------ -->
</body>
</html>
