
<%@ page import="me.hcl.seekin.Internship.Report" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="report.create" /></title>
        <g:YUIButtonRessource />
        <tm:resources />
    </head>
    <body>  
      <h2><g:message code="report.create" /></h2>
      <g:if test="${flash.message}">
      <div class="flash_message"><g:message code="${flash.message}" transparent="true" args="${flash.args}" /></div>
      </g:if>
      <g:hasErrors bean="${reportInstance}">
      <div class="flash_message">
          <g:renderErrors bean="${reportInstance}" as="list" />
      </div>
      </g:hasErrors>
      <g:form class="boxed_form" name="crud_panel" action="save" method="post" enctype="multipart/form-data" >
          
                <p>
                      <label for="title"><g:message code="report.title" default="Title" />:</label>
                      <g:textField name="title" class="field${hasErrors(bean:reportInstance ,field:'title','error')}" value="${fieldValue(bean: reportInstance, field: 'title')}" />

                </p>
          
                <p>
                      <label for="data"><g:message code="report.file" default="File" />:</label>
                      <input type="file" name="data"/>

                </p>
          
                <p>
                      <label for="isPrivate"><g:message code="report.isPrivate" default="Is Private" />:</label>
                      <g:checkBox name="isPrivate" value="${reportInstance?.isPrivate}" />

                </p>
          
                <p>
                      <label for="internship"><g:message code="report.internship" default="Internship" />:</label>
                      <g:select name="internship" from="${internships}" optionKey="id" value="${reportInstance?.internship?.id}"  />

                </p>
          
          <div class="actionpad yui-skin-sam">
             <g:buildCreateButtons />
          </div>

      </g:form>

    </body>
</html>
