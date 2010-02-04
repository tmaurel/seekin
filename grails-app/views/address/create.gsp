
<%@ page import="me.hcl.seekin.Util.Address" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="address.create" /></title>
        <g:YUIButtonRessource />
    </head>
    <body>  
      <h2><g:message code="address.create" /></h2>
      <g:if test="${flash.message}">
      <div class="flash_message"><g:message code="${flash.message}" args="${flash.args}" /></div>
      </g:if>
      <g:hasErrors bean="${addressInstance}">
      <div class="flash_message">
          <g:renderErrors bean="${addressInstance}" as="list" />
      </div>
      </g:hasErrors>
      <g:form class="boxed_form" name="crud_panel" action="save" method="post" >
          
                <p>
                      <label for="street"><g:message code="address.street" default="Street" />:</label>
                      <g:textField name="street" value="${fieldValue(bean: addressInstance, field: 'street')}" />

                </p>
          
                <p>
                      <label for="zipCode"><g:message code="address.zipCode" default="Zip Code" />:</label>
                      <g:textField name="zipCode" value="${fieldValue(bean: addressInstance, field: 'zipCode')}" />

                </p>
          
                <p>
                      <label for="town"><g:message code="address.town" default="Town" />:</label>
                      <g:textField name="town" value="${fieldValue(bean: addressInstance, field: 'town')}" />

                </p>
          
          <div class="submit yui-skin-sam">
             <g:buildCreateButtons />
          </div>

      </g:form>

    </body>
</html>
