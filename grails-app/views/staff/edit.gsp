
<%@ page import="me.hcl.seekin.Auth.Role.Staff" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="staff.edit"/></title>
        <g:YUIButtonRessource />
    </head>
    <body>
      <h2><g:message code="staff.edit"/></h2>
      <g:if test="${flash.message}">
      <div class="flash_message"><g:message code="${flash.message}" args="${flash.args}"/></div>
      </g:if>
      <g:hasErrors bean="${staffInstance}">
      <div class="flash_message"><g:renderErrors bean="${staffInstance}" as="list" /></div>
      </g:hasErrors>
      <g:form class="boxed_form" name="crud_panel" method="post" >
          <g:hiddenField name="id" value="${staffInstance?.id}" />
          <g:hiddenField name="version" value="${staffInstance?.version}" />
          
                <p>

                          <label for="user"><g:message code="staff.user" default="User" /></label>
                          <g:select name="user.id" from="${me.hcl.seekin.Auth.User.list()}" optionKey="id" value="${staffInstance?.user?.id}" noSelection="['null': '']" />

                </p>
          
                <p>

                          <label for="authority"><g:message code="staff.authority" default="Authority" /></label>
                          <g:textField name="authority" value="${fieldValue(bean: staffInstance, field: 'authority')}" class="field${hasErrors(bean:staffInstance ,field:'authority','error')}"/>

                </p>
          
                <p>

                          <label for="roleName"><g:message code="staff.roleName" default="Role Name" /></label>
                          

                </p>
          
          <div class="submit yui-skin-sam">
            <g:buildEditButtons />
          </div>
      </g:form>
    </body>
</html>
