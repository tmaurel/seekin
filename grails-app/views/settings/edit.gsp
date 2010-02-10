
<%@ page import="me.hcl.seekin.Util.Settings" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="settings.edit"/></title>
        <g:YUIButtonRessource />
    </head>
    <body>
      <h2><g:message code="settings.edit"/></h2>
      <g:if test="${flash.message}">
      <div class="flash_message"><g:message code="${flash.message}" args="${flash.args}"/></div>
      </g:if>
      <g:hasErrors bean="${settingsInstance}">
      <div class="flash_message"><g:renderErrors bean="${settingsInstance}" as="list" /></div>
      </g:hasErrors>
      <g:form class="boxed_form" name="crud_panel" method="post"  enctype="multipart/form-data">
          <g:hiddenField name="id" value="${settingsInstance?.id}" />
          <g:hiddenField name="version" value="${settingsInstance?.version}" />
          
                <p>

                          <label for="applicationName"><g:message code="settings.applicationName" default="Application Name" /></label>
                          <g:textField name="applicationName" value="${fieldValue(bean: settingsInstance, field: 'applicationName')}" />

                </p>
          
                <p>

                          <label for="logo"><g:message code="settings.logo" default="Logo" /></label>
                          <input type="file" id="logo" name="logo" />

                </p>
          
                <p>

                          <label for="emailAdmin"><g:message code="settings.emailAdmin" default="Email Admin" /></label>
                          <g:textField name="emailAdmin" value="${fieldValue(bean: settingsInstance, field: 'emailAdmin')}" />

                </p>
          
          <div class="submit yui-skin-sam">
            <g:YUISubmitbutton value="update" action="update"/>
          </div>
      </g:form>
    </body>
</html>
