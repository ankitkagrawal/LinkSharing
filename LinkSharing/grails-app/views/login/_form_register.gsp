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
    <g:textField name="userName" required="" value="${userCommandInstance?.userName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userCommandInstance, field: 'photo', 'error')} required">
    <label for="photo">
        <g:message code="user.photo.label" default="Photo" />
        <span class="required-indicator">*</span>
    </label>
    <input type="file" id="photo" name="photo" value="${userCommandInstance?.photo}"/>

</div>



<div class="fieldcontain ${hasErrors(bean: userCommandInstance, field: 'email', 'error')} required">
    <label for="email">
        <g:message code="user.email.label" default="Email" />
        <span class="required-indicator">*</span>
    </label>
    <g:field type="email" name="email" required="" value="${userCommandInstance?.email}"/>

</div>


<div class="fieldcontain ${hasErrors(bean: userCommandInstance, field: 'password', 'error')} required">
    <label for="password">
        <g:message code="user.password.label" default="Password" />
        <span class="required-indicator">*</span>
    </label>
    <g:field type="password" name="password" maxlength="12" required="" value="${userCommandInstance?.password}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userCommandInstance, field: 'confirmPassword', 'error')} required">
    <label for="confirmPassword">
        <g:message code="user.password.label" default="Confirm Password" />
        <span class="required-indicator">*</span>
    </label>
    <g:field type="password" name="confirmPassword" maxlength="12" required="" value="${userCommandInstance?.confirmPassword}"/>

</div>
