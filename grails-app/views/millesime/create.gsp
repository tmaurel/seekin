
<%@ page import="me.hcl.seekin.Formation.Millesime" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="millesime.create" /></title>
        
        <tm:resources />
    </head>
    <body>  
      <h2><g:message code="millesime.create" /></h2>
      <g:if test="${flash.message}">
      <div class="flash_message"><g:message code="${flash.message}" transparent="true" args="${flash.args}" /></div>
      </g:if>
      <g:hasErrors bean="${millesimeInstance}">
      <div class="flash_message">
          <g:renderErrors bean="${millesimeInstance}" as="list" />
      </div>
      </g:hasErrors>
      <g:form class="boxed_form" name="crud_panel" action="save" method="post" >
          
                <p>
                      <label for="beginDate"><g:message code="millesime.beginDate" default="Begin Date" /></label>
                      <g:datePicker name="beginDate" value="${millesimeInstance?.beginDate}" precision="day" />

                </p>
          
                <p>
                      <label for="endDate"><g:message code="millesime.endDate" default="End Date" /></label>
                      <g:datePicker name="endDate" value="${millesimeInstance?.endDate}" precision="day" />

                </p>
          
          <div class="actionpad yui-skin-sam">
             <g:buildCreateButtons />
          </div>

      </g:form>

    </body>
</html>
