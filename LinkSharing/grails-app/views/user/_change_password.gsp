<%@ page import="linksharing.UserCommand" %>

<div class="fieldcontain ${hasErrors(bean: user, field: 'password', 'error')} required">
    <label for="password">
        <g:message code="user.password.label" default="New Password" />
        <span class="required-indicator">*</span>
    </label>
    <g:field type="password" name="password" maxlength="12" required="" />

</div>

<div class="fieldcontain ${hasErrors(bean: user, field: 'confirmPassword', 'error')} required">
    <label for="confirmPassword">
        <g:message code="user.password.label" default="Confirm Password" />
        <span class="required-indicator">*</span>
    </label>
    <g:field type="password" name="confirmPassword" maxlength="12" required="" />

</div>

<div class="fieldcontain  required" style="margin-left: 164px;">
    <g:submitButton name="Submit" value="Update"></g:submitButton>
</div>
