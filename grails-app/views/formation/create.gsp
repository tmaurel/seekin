
<%@ page import="me.hcl.seekin.Formation.Formation" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="formation.create" /></title>
        <g:YUIButtonRessource />
        <tm:resources />
    </head>
    <body>  
      <h2><g:message code="formation.create" /></h2>
      <g:if test="${flash.message}">
      <div class="flash_message"><g:message code="${flash.message}" transparent="true" args="${flash.args}" /></div>
      </g:if>
      <g:hasErrors bean="${formationInstance}">
      <div class="flash_message">
          <g:renderErrors bean="${formationInstance}" as="list" />
      </div>
      </g:hasErrors>
      <g:form class="boxed_form" name="crud_panel" action="save" method="post" >
          
                <p>
                      <label for="label"><g:message code="formation.label" default="Label" />:</label>
                      <g:textField name="label" class="field${hasErrors(bean:formationInstance ,field:'label','error')}" value="${fieldValue(bean: formationInstance, field: 'label')}" />

                </p>
          
                <p>
                      <label for="description"><g:message code="formation.description" default="Description" />:</label>
                      <g:textField name="description" class="field${hasErrors(bean:formationInstance ,field:'description','error')}" value="${fieldValue(bean: formationInstance, field: 'description')}" />

                </p>
          
                <p>
                      <label for="file"><g:message code="formation.file" default="File" />:</label>
                      <g:select name="file.id" from="${me.hcl.seekin.Ressource.Document.list()}" optionKey="id" value="${formationInstance?.file?.id}" noSelection="['null': '']" />

                </p>
          
          <div class="actionpad yui-skin-sam">
             <g:buildCreateButtons />
          </div>

      </g:form>

    </body>
</html>
