
<%@ page import="me.hcl.seekin.Company" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="company.edit"/></title>
        <g:YUIButtonRessource />
        <tm:resources />
    </head>
    <body>
      <h2><g:message code="company.edit"/></h2>
      <g:if test="${flash.message}">
      <div class="flash_message"><g:message code="${flash.message}" transparent="true" args="${flash.args}"/></div>
      </g:if>
      <g:hasErrors bean="${companyInstance}">
      <div class="flash_message"><g:renderErrors bean="${companyInstance}" as="list" /></div>
      </g:hasErrors>
      <g:form class="boxed_form" name="crud_panel" method="post" >
          <g:hiddenField name="id" value="${companyInstance?.id}" />
          <g:hiddenField name="version" value="${companyInstance?.version}" />
          
                <p>

                          <label for="name"><g:message code="company.name" default="Name" /></label>
                          <g:textField name="name" class="field${hasErrors(bean:companyInstance ,field:'name','error')}" value="${companyInstance.name}" />

                </p>
          
                <p>

                          <label for="address"><g:message code="company.address" default="Address" /></label>
                          <g:select name="address.id" from="${me.hcl.seekin.Util.Address.list()}" optionKey="id" value="${companyInstance?.address?.id}"  />

                </p>
          
                <p>

                          <label for="phone"><g:message code="company.phone" default="Phone" /></label>
                          <g:textField name="phone" class="field${hasErrors(bean:companyInstance ,field:'phone','error')}" maxlength="10" value="${companyInstance.phone}" />

                </p>
          
                <p>

                          <label for="employees"><g:message code="company.employees" default="Employees" /></label>
                          <g:select name="employees"
from="${me.hcl.seekin.Auth.User.list()}"
size="5" multiple="yes" optionKey="id"
value="${companyInstance?.employees}" />


                </p>
          
          <div class="actionpad yui-skin-sam">
            <g:buildEditButtons />
          </div>
      </g:form>
    </body>
</html>
