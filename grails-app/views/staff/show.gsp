
<%@ page import="me.hcl.seekin.Auth.Role.Staff" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="staff.show" /></title>
        <tm:resources />
    </head>
    <body>
        <h2><g:message code="staff.show" /></h2>
        <div class="boxed_form">
            <g:if test="${flash.message}">
            <div class="flash_message"><g:message code="${flash.message}" transparent="true" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
			<h3><g:fieldValue bean="${staffInstance}" field="user" /></h3>
			<p>
			  <label><g:message code="user.address" /></label>
			  <span class="field_value"><g:link controller="address" action="show" id="${staffInstance?.user?.address?.id}"><g:fieldValue bean="${staffInstance?.user}" field="address" /></g:link></span>
			</p>
			<p>
			  <label><g:message code="user.phone" /></label>
			  <span class="field_value"><g:fieldValue bean="${staffInstance?.user}" field="phone" /></span>
			</p>
			<g:if test="${staffInstance?.user?.showEmail}">
			<p>
			  <label><g:message code="user.email" /></label>
			  <span class="field_value"><g:fieldValue bean="${staffInstance?.user}" field="email" /></span>
			</p>
			</g:if>
			<h4>
			  <g:message code="message.private" /> :
			  <g:link controller="message" action="write" id="${staffInstance?.user?.id}">
				  <img src="${resource(dir:'images/icons',file:'private_msg.png')}" alt="${message(code:'message.private')}"/>
			  </g:link>
			</h4>
         </div>
       </body>
</html>
