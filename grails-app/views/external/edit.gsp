
<%@ page import="me.hcl.seekin.Auth.Role.External" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="external.edit"/></title>
        
        <tm:resources />
    </head>
    <body>
      <h2><g:message code="external.edit"/></h2>
      <g:if test="${flash.message}">
      <div class="flash_message"><g:message code="${flash.message}" transparent="true" args="${flash.args}"/></div>
      </g:if>
      <g:hasErrors bean="${externalInstance}">
      <div class="flash_message"><g:renderErrors bean="${externalInstance}" as="list" /></div>
      </g:hasErrors>
      <g:form class="boxed_form" name="crud_panel" method="post" >
          <g:hiddenField name="id" value="${externalInstance?.id}" />
          <g:hiddenField name="version" value="${externalInstance?.version}" />
          
                <p>

                          <label for="user"><g:message code="external.user" default="User" /></label>
                          <g:select name="user.id" from="${me.hcl.seekin.Auth.User.list()}" optionKey="id" value="${externalInstance?.user?.id}" noSelection="['null': '']" />

                </p>
          
                <p>

                          <label for="company"><g:message code="external.company" default="Company" /></label>
                          <g:select name="company.id" from="${me.hcl.seekin.Internship.Company.list()}" optionKey="id" value="${externalInstance?.company?.id}" noSelection="['null': '']" />

                </p>
          
                <p>

                          <label for="formerStudent"><g:message code="external.formerStudent" default="Former Student" /></label>
                          <g:checkBox name="formerStudent" value="${externalInstance?.formerStudent}" />

                </p>
          
                <p>

                          <label for="authority"><g:message code="external.authority" default="Authority" /></label>
                          <g:textField name="authority" value="${externalInstance.authority}" class="field${hasErrors(bean:externalInstance ,field:'authority','error')}"/>

                </p>
          
                <p>

                          <label for="roleName"><g:message code="external.roleName" default="Role Name" /></label>
                          

                </p>
          
          <div class="actionpad yui-skin-sam">
            <g:buildEditButtons />
          </div>
      </g:form>
    </body>
</html>
