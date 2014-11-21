<%@ page import="linksharing.UserCommand" %>

<div class="fieldcontain ${hasErrors(bean: userCommandInstance, field: 'firstName', 'error')} required">
    <label for="firstName">
        <g:message code="user.firstName.label" default="First Name" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="firstName" required="" value="${userCommandInstance?.firstName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userCommandInstance, field: 'lastName', 'error')} required">
    <label for="lastName">
        <g:message code="user.lastName.label" default="Last Name" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="lastName" required="" value="${userCommandInstance?.lastName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userCommandInstance, field: 'userName', 'error')} required">
    <label for="userName">
        <g:message code="user.userName.label" default="User Name" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="userName" required="" value="${userCommandInstance?.userName}" disabled="true"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userCommandInstance, field: 'email', 'error')} required">
    <label for="email">
        <g:message code="user.email.label" default="Email" />
        <span class="required-indicator">*</span>
    </label>
    <g:field type="email" name="email" required="" value="${userCommandInstance?.email}" disabled="true"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userCommandInstance, field: 'photo', 'error')} required">
    <label for="photo">
        <g:message code="user.photo.label" default="Photo" />
    </label>
    <input type="file" id="photo" name="photo" value="${userCommandInstance?.photo}"/>

</div>

<div class="fieldcontain  required" style="margin-left: 164px;">
    %{--<g:field type="password" name="confirmPassword" maxlength="12" required="" value="${userCommandInstance?.confirmPassword}"/>--}%
    <g:submitButton name="Submit" value="Update"></g:submitButton>
</div>