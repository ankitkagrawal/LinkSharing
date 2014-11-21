<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="Grails"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${assetPath(src: 'favicon.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${assetPath(src: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${assetPath(src: 'apple-touch-icon-retina.png')}">
  		<asset:stylesheet src="application.css"/>
		<asset:javascript src="application.js"/>
		<g:layoutHead/>
	</head>
	<body>

    <div style="background-image: linear-gradient(top,#ADC8F9,#D2EEFF); background-image:-moz-linear-gradient(top,#ADC8F9,#D2EEFF); background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0, #ADC8F9), color-stop(1, #D2EEFF));">
        <div id="ls_logo" style="float: left;padding: 20px;padding-left: 50px;">
            <g:link controller="user" action="dashboard">
                <asset:image src="link.png" width="80" height="80" alt="Home"></asset:image>
            </g:link>
        </div>
        <div id="ls_text" style="text-align: left;padding-top: 30px;padding-left: 158px;"><asset:image src="LinkSharing.png" alt="Home"></asset:image></div>
    </div>

    <div style="height: 45px; background-image:-moz-linear-gradient(top,#ADC8F9,#D2EEFF); background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0, #ADC8F9), color-stop(1, #D2EEFF));">
        <div style="float: left;margin-left: 310px;padding: 11px;">
            <g:form controller="topic" action="search">
                <g:textField id="search_text" style="border: none;border-radius: 10px;background: white url(${grailsApplication.config.assets.location}/search.jpg) no-repeat 0% 0%;padding-left: 24px;" name="search_text" value="Search..." onfocus="toggleSearch()" onblur="toggleSearch()"></g:textField>
            </g:form>
        </div>

        <g:if test="${session['user']}">

        <div style="float: right; padding: 7px;">
            <div style="float: left;">
            <asset:image src="chat-icon1.ico" width="30" height="30" alt="Home" title="Create New Topic"></asset:image>
            <g:link controller="mail" action="index" ><asset:image src="mail-icon.png" width="30" height="30" alt="Home" title="Send Invitation"></asset:image></g:link>
            <g:link controller="linkResource" action="create" ><asset:image src="link-icon1.png" width="30" height="30" alt="linkResource" title="Add Link Resource"></asset:image></g:link>
            <g:link controller="documentResource" action="create" ><asset:image src="doc-icon.png" width="30" height="30" alt="docResource" title="Add Document Resource"></asset:image></g:link>
            </div>
            <div id='cssmenu'>
                <ul>
                    <li class='active has-sub'><a href='#'><span>${session.user?.firstName}</span></a>
                        <ul>
                            <li><g:link controller="user" action="editProfile" ><span>Profile</span></g:link></li>
                            <li><g:link controller="login" action="logoutHander" ><span>Logout</span></g:link></li>
                        </ul>
                    </li>
                </ul>
            </div>

        </div>
        </g:if>

    </div>

	<g:layoutBody/>
	<div class="footer" role="contentinfo"></div>
	<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>

	</body>
</html>
