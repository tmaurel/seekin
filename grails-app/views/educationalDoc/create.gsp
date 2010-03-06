
<%@ page import="me.hcl.seekin.Ressource.EducationalDoc" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="educationalDoc.create" /></title>
        <g:YUIButtonRessource />
        <tm:resources />
    </head>
    <body>  
      <h2><g:message code="educationalDoc.create" /></h2>
      <g:if test="${flash.message}">
      <div class="flash_message"><g:message code="${flash.message}" transparent="true" args="${flash.args}" /></div>
      </g:if>
      <g:hasErrors bean="${educationalDocInstance}">
      <div class="flash_message">
          <g:renderErrors bean="${educationalDocInstance}" as="list" />
      </div>
      </g:hasErrors>
      <g:form class="boxed_form" name="crud_panel" action="save" method="post" enctype="multipart/form-data" >
          
                <p>
                      <label for="title"><g:message code="educationalDoc.title" default="Title" /></label>
                      <g:textField name="title" class="field${hasErrors(bean:educationalDocInstance ,field:'title','error')}" value="${fieldValue(bean: educationalDocInstance, field: 'title')}" />

                </p>
          
                <p>
                      <label for="data"><g:message code="educationalDoc.file" default="File" /></label>
                      <input type="file" name="data"/>

                </p>
                <p>
                      <label for="formations"><g:message code="formations" default="Formations" /></label>
                      <g:select name="formations"
                        from="${formations}"
                        size="5" multiple="yes" optionKey="id"
                        optionValue="value"
                        value="${params.formations}" />
                </p>
          
          <div class="actionpad yui-skin-sam">
             <g:buildCreateButtons />
          </div>

      </g:form>

    </body>
</html>
