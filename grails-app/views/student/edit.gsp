
<%@ page import="me.hcl.seekin.Auth.Role.Student" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="student.edit"/></title>
        <g:YUIButtonRessource />
    </head>
    <body>
      <h2><g:message code="student.edit"/></h2>
      <g:if test="${flash.message}">
      <div class="flash_message"><g:message code="${flash.message}" args="${flash.args}"/></div>
      </g:if>
      <g:hasErrors bean="${studentInstance}">
      <div class="flash_message"><g:renderErrors bean="${studentInstance}" as="list" /></div>
      </g:hasErrors>
      <g:form class="boxed_form" name="crud_panel" method="post" >
          <g:hiddenField name="id" value="${studentInstance?.id}" />
          <g:hiddenField name="version" value="${studentInstance?.version}" />
          
                <p>

                          <label for="user"><g:message code="student.user" default="User" /></label>
                          <g:select name="user.id" from="${me.hcl.seekin.Auth.User.list()}" optionKey="id" value="${studentInstance?.user?.id}" noSelection="['null': '']" />

                </p>
          
                <p>

                          <label for="visible"><g:message code="student.visible" default="Visible" /></label>
                          <g:checkBox name="visible" value="${studentInstance?.visible}" />

                </p>
          
                <p>

                          <label for="authority"><g:message code="student.authority" default="Authority" /></label>
                          <g:textField name="authority" value="${fieldValue(bean: studentInstance, field: 'authority')}" />

                </p>
          
                <p>

                          <label for="roleName"><g:message code="student.roleName" default="Role Name" /></label>
                          

                </p>
          
          <div class="submit yui-skin-sam">
            <g:buildEditButtons />
          </div>
      </g:form>
    </body>
</html>
