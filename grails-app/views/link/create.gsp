
<%@ page import="me.hcl.seekin.Ressource.Link" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="link.create" /></title>
        <g:YUIButtonRessource />
    </head>
    <body>  
      <h2><g:message code="link.create" /></h2>
      <g:if test="${flash.message}">
      <div class="flash_message"><g:message code="${flash.message}" args="${flash.args}" /></div>
      </g:if>
      <g:hasErrors bean="${linkInstance}">
      <div class="flash_message">
          <g:renderErrors bean="${linkInstance}" as="list" />
      </div>
      </g:hasErrors>
      <g:form class="boxed_form" name="crud_panel" action="save" method="post" >
          
                <p>
                      <label for="title"><g:message code="link.title" default="Title" />:</label>
                      <g:textField name="title" value="${fieldValue(bean: linkInstance, field: 'title')}" />

                </p>
          
                <p>
                      <label for="url"><g:message code="link.url" default="Url" />:</label>
                      <g:textField name="url" value="${fieldValue(bean: linkInstance, field: 'url')}" />

                </p>
          
                <p>
                      <label for="description"><g:message code="link.description" default="Description" />:</label>
                      <g:textField name="description" value="${fieldValue(bean: linkInstance, field: 'description')}" />

                </p>
          
          <div class="submit yui-skin-sam">
             <g:buildCreateButtons />
          </div>

      </g:form>

    </body>
</html>
