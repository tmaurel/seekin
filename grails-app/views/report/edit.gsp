
<%@ page import="me.hcl.seekin.Internship.Report" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="report.edit"/></title>
        
        <tm:resources />
    </head>
    <body>
      <h2><g:message code="report.edit"/></h2>
      <g:if test="${flash.message}">
      <div class="flash_message"><g:message code="${flash.message}" transparent="true" args="${flash.args}"/></div>
      </g:if>
      <g:hasErrors bean="${reportInstance}">
      <div class="flash_message"><g:renderErrors bean="${reportInstance}" as="list" /></div>
      </g:hasErrors>
      <g:form class="boxed_form" name="crud_panel" method="post" enctype="multipart/form-data" >
          <g:hiddenField name="id" value="${reportInstance?.id}" />
          <g:hiddenField name="version" value="${reportInstance?.version}" />
          
                <p>

                          <label for="title"><g:message code="report.title" default="Title" /></label>
                          <g:textField name="title" class="field${hasErrors(bean:reportInstance ,field:'title','error')}" value="${reportInstance.title}" />

                </p>
          

                <p>
                      <label for="data"><g:message code="report.file" default="File" />:</label>
                      <input type="file" name="data"/>

                </p>
          
                <p>

                          <label for="isPrivate"><g:message code="report.isPrivate" default="Is Private" /></label>
                          <g:checkBox name="isPrivate" value="${reportInstance?.isPrivate}" />

                </p>
          
           <div class="actionpad yui-skin-sam">
           <g:ifAnyGranted role="ROLE_ADMIN">
                <g:buildEditButtons />
            </g:ifAnyGranted>
             
            <g:ifNotGranted role="ROLE_ADMIN">
            <g:ifAnyGranted role="ROLE_FORMATIONMANAGER">
                <g:listButton />
                <g:if test="${ok}">
                <g:YUISubmitbutton value="update" action="update" />
                <g:deleteButton />
                </g:if>
            </g:ifAnyGranted>
            </g:ifNotGranted>

             <g:ifAnyGranted role="ROLE_STUDENT">
                <g:listButton />
                <g:if test="${ok}">
                <g:YUISubmitbutton value="update" action="update" />
                </g:if>
             </g:ifAnyGranted>
           </div>


      </g:form>
    </body>
</html>
