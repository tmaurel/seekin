
<%@ page import="me.hcl.seekin.Auth.Role" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="role.create" /></title>
        <g:YUIButtonRessource />
    </head>
    <body>  
      <h2><g:message code="role.create" /></h2>
      <g:if test="${flash.message}">
      <div class="flash_message"><g:message code="${flash.message}" args="${flash.args}" /></div>
      </g:if>
      <g:hasErrors bean="${roleInstance}">
      <div class="flash_message">
          <g:renderErrors bean="${roleInstance}" as="list" />
      </div>
      </g:hasErrors>
      <g:form class="boxed_form" name="crud_panel" action="save" method="post" >
          
                <p>
                      <label for="authority"><g:message code="role.authority" default="Authority" />:</label>
                      <g:textField name="authority" value="${fieldValue(bean: roleInstance, field: 'authority')}" />

                </p>
          
                <p>
                      <label for="description"><g:message code="role.description" default="Description" />:</label>
                      <g:textField name="description" value="${fieldValue(bean: roleInstance, field: 'description')}" />

                </p>
          
          <div class="submit yui-skin-sam">
             <g:buildCreateButtons />
          </div>

      </g:form>

    </body>
</html>
