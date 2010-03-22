
<%@ page import="me.hcl.seekin.Internship.Company" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="company.create" /></title>
        <tm:resources />
    </head>
    <body>  
      <h2><g:message code="company.create" /></h2>
      <g:if test="${flash.message}">
      <div class="flash_message"><g:message code="${flash.message}" transparent="true" args="${flash.args}" /></div>
      </g:if>
      <g:hasErrors bean="${companyInstance}">
      <div class="flash_message">
          <g:renderErrors bean="${companyInstance}" as="list" />
      </div>
      </g:hasErrors>
      <g:form class="boxed_form" name="crud_panel" action="save" method="post" >
		<p>
		  <label for="name"><g:message code="company.name" /></label>
		  <g:textField name="name" class="field${hasErrors(bean:companyInstance ,field:'name','error')}" value="${companyInstance?.name}" />
		</p>
		<p>
		  <label for="address.street"><g:message code="address.street" /></label>
		  <g:textField name="address.street" value="${companyInstance?.address?.street}" class="field${hasErrors(bean:userInstance,field:'address.street','error')}"/>
		</p>
		<p>
		  <label for="address.town"><g:message code="address.town" /></label>
		  <g:textField name="address.town" value="${companyInstance?.address?.town}" class="field${hasErrors(bean:userInstance,field:'address.town','error')}"/>
		</p>
		<p>
		  <label for="address.zipCode"><g:message code="address.zipCode" /></label>
		  <g:textField name="address.zipCode" value="${companyInstance?.address?.zipCode}" class="field${hasErrors(bean:userInstance,field:'address.zipCode','error')}"/>
		</p>
		<p>
		  <label for="phone"><g:message code="company.phone" />:</label>
		  <g:textField name="phone" class="field${hasErrors(bean:companyInstance ,field:'phone','error')}" maxlength="10" value="${companyInstance?.phone}" />
		</p>
		<div class="actionpad yui-skin-sam">
		   <g:buildCreateButtons />
		</div>
      </g:form>
    </body>
</html>
