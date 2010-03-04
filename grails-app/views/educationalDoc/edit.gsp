
<%@ page import="me.hcl.seekin.Ressource.EducationalDoc" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="educationalDoc.edit"/></title>
        <g:YUIButtonRessource />
    </head>
    <body>
      <h2><g:message code="educationalDoc.edit"/></h2>
      <g:if test="${flash.message}">
      <div class="flash_message"><g:message code="${flash.message}" args="${flash.args}"/></div>
      </g:if>
      <g:hasErrors bean="${educationalDocInstance}">
      <div class="flash_message"><g:renderErrors bean="${educationalDocInstance}" as="list" /></div>
      </g:hasErrors>
      <g:form class="boxed_form" name="crud_panel" method="post" enctype="multipart/form-data" >
          <g:hiddenField name="id" value="${educationalDocInstance?.id}" />
          <g:hiddenField name="version" value="${educationalDocInstance?.version}" />
          
                <p>

                          <label for="title"><g:message code="educationalDoc.title" default="Title" /></label>
                          <g:textField name="title" class="field${hasErrors(bean:educationalDocInstance ,field:'title','error')}" value="${fieldValue(bean: educationalDocInstance, field: 'title')}" />

                </p>
          
                <p>
                          <label for="uri"><g:message code="educationalDoc.file" default="File" />:</label>
                          <input type="file" name="data" />
                </p>
          
                <p>
                      <label for="formations"><g:message code="formations" default="Formations" />:</label>
                      <g:select name="formations"
                        from="${formations}"
                        size="5" multiple="yes" optionKey="id"
                        optionValue="value"
                        value="${selectedFormations?:params.formations}" />
                </p>
          
          <div class="submit yui-skin-sam">
            <g:buildEditButtons />
          </div>
      </g:form>
    </body>
</html>
