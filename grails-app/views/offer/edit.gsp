
<%@ page import="me.hcl.seekin.Internship.Offer" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="offer.edit"/></title>
        
        <tm:resources />
    </head>
    <body>
      <h2><g:message code="offer.edit"/></h2>
      <g:if test="${flash.message}">
      <div class="flash_message"><g:message code="${flash.message}" transparent="true" args="${flash.args}"/></div>
      </g:if>
      <g:hasErrors bean="${offerInstance}">
      <div class="flash_message"><g:renderErrors bean="${offerInstance}" as="list" /></div>
      </g:hasErrors>
      <g:form class="boxed_form" name="crud_panel" method="post" enctype="multipart/form-data" >
          <g:hiddenField name="id" value="${offerInstance?.id}" />
          <g:hiddenField name="version" value="${offerInstance?.version}" />
          <g:hiddenField name="reason" value="${offerInstance?.reason}" />
          
                <p>

                          <label for="subject"><g:message code="offer.subject" default="Subject" /></label>
                          <g:textField name="subject" class="field${hasErrors(bean:offerInstance ,field:'subject','error')}" value="${offerInstance.subject}" />

                </p>
          
                <p>

                          <label for="description"><g:message code="offer.description" default="Description" /></label>
                          <g:textField name="description" class="field${hasErrors(bean:offerInstance ,field:'description','error')}" value="${offerInstance.description}" />

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
                      <label for="data"><g:message code="offer.file" default="File" />:</label>
                      <input type="file" name="data"/>
                </p>

                <p>
                      <label for="promotions"><g:message code="offer.promotions" default="Promotions" />:</label>
                      <g:select name="promotions"
                        from="${promotionInstance}"
                        size="5" multiple="yes" optionKey="id"
                        optionValue="value"
                        value="${selectedPromotions?:params.promotions}" />
                </p>
                
                <g:ifAnyGranted role="ROLE_ADMIN">

                    <p>

                              <label for="validated"><g:message code="offer.validated" default="Validated" /></label>
                              <g:checkBox name="validated" value="${offerInstance?.validated}" />

                    </p>
                  
                </g:ifAnyGranted>
          
          <div class="actionpad yui-skin-sam">
             <g:ifAnyGranted role="ROLE_ADMIN">

                <g:buildDenyButtons />

             </g:ifAnyGranted>
             <g:ifNotGranted role="ROLE_ADMIN">

                <g:buildEditButtons />

             </g:ifNotGranted>
          </div>
      </g:form>
    </body>
</html>
