
<%@ page import="me.hcl.seekin.Internship.Offer" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="offer.edit"/></title>
        <g:YUIButtonRessource />
    </head>
    <body>
      <h2><g:message code="offer.edit"/></h2>
      <g:if test="${flash.message}">
      <div class="flash_message"><g:message code="${flash.message}" args="${flash.args}"/></div>
      </g:if>
      <g:hasErrors bean="${offerInstance}">
      <div class="flash_message"><g:renderErrors bean="${offerInstance}" as="list" /></div>
      </g:hasErrors>
      <g:form class="boxed_form" name="crud_panel" method="post" >
          <g:hiddenField name="id" value="${offerInstance?.id}" />
          <g:hiddenField name="version" value="${offerInstance?.version}" />
          
                <p>

                          <label for="subject"><g:message code="offer.subject" default="Subject" /></label>
                          <g:textField name="subject" class="field${hasErrors(bean:offerInstance ,field:'subject','error')}" value="${fieldValue(bean: offerInstance, field: 'subject')}" />

                </p>
          
                <p>

                          <label for="description"><g:message code="offer.description" default="Description" /></label>
                          <g:textField name="description" class="field${hasErrors(bean:offerInstance ,field:'description','error')}" value="${fieldValue(bean: offerInstance, field: 'description')}" />

                </p>
          
                <p>

                          <label for="beginAt"><g:message code="offer.beginAt" default="Begin At" /></label>
                          <g:datePicker name="beginAt" value="${offerInstance?.beginAt}"  />

                </p>
          
                <p>

                          <label for="length"><g:message code="offer.length" default="Length" /></label>
                          <g:select name="length" from="${1..52}" value="${offerInstance?.length}"  />

                </p>
          
                <p>

                          <label for="file"><g:message code="offer.file" default="File" /></label>
                          <g:select name="file.id" from="${me.hcl.seekin.Ressource.Document.list()}" optionKey="id" value="${offerInstance?.file?.id}" noSelection="['null': '']" />

                </p>
          
                <p>

                          <label for="author"><g:message code="offer.author" default="Author" /></label>
                          <g:select name="author.id" from="${me.hcl.seekin.Auth.User.list()}" optionKey="id" value="${offerInstance?.author?.id}"  />

                </p>
          
                <p>

                          <label for="promotions"><g:message code="offer.promotions" default="Promotions" /></label>
                          

                </p>
          
          <div class="submit yui-skin-sam">
            <g:buildEditButtons />
          </div>
      </g:form>
    </body>
</html>
