
<%@ page import="me.hcl.seekin.Internship.Convocation" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="convocation.create" /></title>
        <g:YUIButtonRessource />
        <tm:resources />
    </head>
    <body>  
      <h2><g:message code="convocation.create" /></h2>
      <g:if test="${flash.message}">
      <div class="flash_message"><g:message code="${flash.message}" transparent="true" args="${flash.args}" /></div>
      </g:if>
      <g:hasErrors bean="${convocationInstance}">
      <div class="flash_message">
          <g:renderErrors bean="${convocationInstance}" as="list" />
      </div>
      </g:hasErrors>
      <g:form class="boxed_form" name="crud_panel" action="save" method="post" >
          
                <p>
                      <label for="date"><g:message code="convocation.date" default="Date" />:</label>
                      <g:datePicker name="date" value="${convocationInstance?.date}"  />

                </p>
          
                <p>
                      <label for="building"><g:message code="convocation.building" default="Building" />:</label>
                      <g:textField name="building" class="field${hasErrors(bean:convocationInstance ,field:'building','error')}" value="${fieldValue(bean: convocationInstance, field: 'building')}" />

                </p>
          
                <p>
                      <label for="room"><g:message code="convocation.room" default="Room" />:</label>
                      <g:textField name="room" class="field${hasErrors(bean:convocationInstance ,field:'room','error')}" value="${fieldValue(bean: convocationInstance, field: 'room')}" />

                </p>
          
                <p>
                      <label for="internship"><g:message code="convocation.internship" default="Internship" />:</label>
                      <g:select name="internship.id" from="${internships}" optionKey="id" optionValue="value" value="${convocationInstance?.internship?.id}"  />

                </p>
          
          <div class="actionpad yui-skin-sam">
             <g:buildCreateButtons />
          </div>

      </g:form>

    </body>
</html>
