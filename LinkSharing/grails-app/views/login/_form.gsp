<%@ page import="linksharing.Login" %>

<div class="fieldcontain ${hasErrors(bean: loginInstance, field: 'username', 'error')} required">
    <label for="username">
        <g:message code="login.username.label" default="Username" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="username" required="" value="${loginInstance?.username}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: loginInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="login.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="password" name="password" required="" value="${loginInstance?.password}"/>

</div>



