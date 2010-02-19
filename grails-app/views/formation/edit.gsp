
<%@ page import="me.hcl.seekin.Formation.Formation" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="formation.edit"/></title>
        <g:YUIButtonRessource />
    </head>
    <body>
      <h2><g:message code="formation.edit"/></h2>
      <g:if test="${flash.message}">
      <div class="flash_message"><g:message code="${flash.message}" args="${flash.args}"/></div>
      </g:if>
      <g:hasErrors bean="${formationInstance}">
      <div class="flash_message"><g:renderErrors bean="${formationInstance}" as="list" /></div>
      </g:hasErrors>
      <g:form class="boxed_form" name="crud_panel" method="post" >
          <g:hiddenField name="id" value="${formationInstance?.id}" />
          <g:hiddenField name="version" value="${formationInstance?.version}" />
          
                <p>

                          <label for="label"><g:message code="formation.label" default="Label" /></label>
                          <g:textField name="label" class="field${hasErrors(bean:formationInstance ,field:'label','error')}" value="${fieldValue(bean: formationInstance, field: 'label')}" />

                </p>
          
                <p>

                          <label for="description"><g:message code="formation.description" default="Description" /></label>
                          <g:textField name="description" class="field${hasErrors(bean:formationInstance ,field:'description','error')}" value="${fieldValue(bean: formationInstance, field: 'description')}" />

                </p>

                <p>

                          <label for="file"><g:message code="formation.file" default="File" /></label>
                          <g:select name="file.id" from="${me.hcl.seekin.Ressource.Document.list()}" optionKey="id" value="${formationInstance?.file?.id}" noSelection="['null': '']" />

                </p>
          
                <p>

                          <label for="students"><g:message code="formation.students" default="Students" /></label>
                          <g:select name="students"
from="${me.hcl.seekin.Auth.Role.Student.list()}"
size="5" multiple="yes" optionKey="id"
value="${formationInstance?.students}" />


                </p>
          
                <p>

                          <label for="promotions"><g:message code="formation.promotions" default="Promotions" /></label>
                          
<g:link controller="promotion" params="['formation.id': formationInstance?.id]" action="create"><g:message code="promotion.new" default="New Promotion" /></g:link>
<br /><g:each in="${formationInstance?.promotions}" var="promotionInstance">
    - <g:link controller="promotion" action="show" id="${promotionInstance.id}">${promotionInstance?.encodeAsHTML()}</g:link><br />
</g:each>


                </p>
          
          <div class="submit yui-skin-sam">
            <g:buildEditButtons />
          </div>
      </g:form>
    </body>
</html>
