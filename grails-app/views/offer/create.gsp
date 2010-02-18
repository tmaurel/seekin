
<%@ page import="me.hcl.seekin.Internship.Offer" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="offer.create" /></title>
        <g:YUIButtonRessource />
    </head>
    <body>  
      <h2><g:message code="offer.create" /></h2>
      <g:if test="${flash.message}">
      <div class="flash_message"><g:message code="${flash.message}" args="${flash.args}" /></div>
      </g:if>
      <g:hasErrors bean="${offerInstance}">
      <div class="flash_message">
          <g:renderErrors bean="${offerInstance}" as="list" />
      </div>
      </g:hasErrors>
      <g:form class="boxed_form" name="crud_panel" action="save" method="post" >
          
                <p>
                      <label for="subject"><g:message code="offer.subject" default="Subject" />:</label>
                      <g:textField name="subject" class="field${hasErrors(bean:offerInstance ,field:'subject','error')}" value="${fieldValue(bean: offerInstance, field: 'subject')}" />

                </p>
          
                <p>
                      <label for="description"><g:message code="offer.description" default="Description" />:</label>
                      <g:textField name="description" class="field${hasErrors(bean:offerInstance ,field:'description','error')}" value="${fieldValue(bean: offerInstance, field: 'description')}" />

                </p>
          
                <p>
                      <label for="beginAt"><g:message code="offer.beginAt" default="Begin At" />:</label>
                      <g:datePicker name="beginAt" value="${offerInstance?.beginAt}"  />

                </p>
          
                <p>
                      <label for="length"><g:message code="offer.length" default="Length" />:</label>
                      <g:select name="length" from="${1..52}" value="${offerInstance?.length}"  />

                </p>
          
                <p>
                      <label for="status"><g:message code="offer.status" default="Status" />:</label>
                      <g:textField name="status" class="field${hasErrors(bean:offerInstance ,field:'status','error')}" value="${fieldValue(bean: offerInstance, field: 'status')}" />

                </p>
          
                <p>
                      <label for="file"><g:message code="offer.file" default="File" />:</label>
                      <g:select name="file.id" from="${me.hcl.seekin.Ressource.Document.list()}" optionKey="id" value="${offerInstance?.file?.id}" noSelection="['null': '']" />

                </p>
          
          <div class="submit yui-skin-sam">
             <g:buildCreateButtons />
          </div>

      </g:form>

    </body>
</html>
