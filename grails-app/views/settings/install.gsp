
<%@ page import="me.hcl.seekin.Util.Settings" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="settings.create" /></title>
        <g:YUIButtonRessource />
    </head>
    <body>  
      <h2><g:message code="settings.install" /></h2>
      <g:if test="${flash.message}">
      <div class="flash_message"><g:message code="${flash.message}" args="${flash.args}" /></div>
      </g:if>
      <g:hasErrors>
      <div class="flash_message">
          <g:renderErrors bean="${settingsInstance}" as="list" />
          <g:renderErrors bean="${adminInstance}" as="list" />
      </div>
      </g:hasErrors>
      <g:form class="boxed_form" name="crud_panel" action="install" method="post" >
		<h3><g:message code="settings.application" /></h3>
		<p>
			  <label for="applicationName"><g:message code="settings.applicationName" default="Application Name" /></label>
			  <g:textField name="applicationName" value="${fieldValue(bean: settingsInstance, field: 'applicationName')}" />
		</p>
		<h3><g:message code="settings.admin" /></h3>
		<p>
			  <label for="emailAdmin"><g:message code="user.email" /></label>
			  <g:textField name="emailAdmin" value="${fieldValue(bean: adminInstance, field: 'email')}" />
		</p>
		<p>
			  <label for="firstNameAdmin"><g:message code="user.firstName" /></label>
			  <g:textField name="firstNameAdmin" value="${fieldValue(bean: adminInstance, field: 'firstName')}" />
		</p>
		<p>
			  <label for="lastNameAdmin"><g:message code="user.lastName" /></label>
			  <g:textField name="lastNameAdmin" value="${fieldValue(bean: adminInstance, field: 'lastName')}" />
		</p>
		<p>
			  <label for="passwordAdmin"><g:message code="user.password" /></label>
			  <g:passwordField name="passwordAdmin" value="" />
		</p>
		<p>
			  <label for="repasswordAdmin"><g:message code="user.repassword" /></label>
			  <g:passwordField name="repasswordAdmin" value="" />
		</p>
		<div class="actionpad yui-skin-sam">
		   <g:YUISubmitbutton value="create" action="install"/>
		</div>
      </g:form>
    </body>
</html>
