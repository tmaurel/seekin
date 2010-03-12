
<%@ page import="me.hcl.seekin.Internship.Offer" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="offer.create" /></title>
	    <gui:resources components="accordion, autoComplete"/>
        <g:YUIButtonRessource />
        <tm:resources />
    </head>
    <body>  
      <h2><g:message code="offer.create" /></h2>
      <g:if test="${flash.message}">
      <div class="flash_message"><g:message code="${flash.message}" transparent="true" args="${flash.args}" /></div>
      </g:if>
      <g:hasErrors bean="${offerInstance}">
      <div class="flash_message">
          <g:renderErrors bean="${offerInstance}" as="list" />
      </div>
      </g:hasErrors>

      <div class = "yui-skin-sam">
        <g:form class="boxed_form" name="crud_panel" action="save" method="post" enctype="multipart/form-data" >

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

                  <div>
                        <label for="company"><g:message code="internship.company" default="Company" /></label>

                        <gui:autoComplete
                          id="companyName"
                          controller="company"
                          action="listCompanyAsJSON"
                          minQueryLength='2'
                          queryDelay='0.5'
                          value="${company}"

                        />

                  </div>

                  <p>
                        <label for="promotions"><g:message code="offer.promotions" default="Promotions" /></label>
                        <g:select name="promotions"
                          from="${promotionInstance}"
                          size="5" multiple="yes" optionKey="id"
                          optionValue="value"
                          value="${params.promotions}" />
                  </p>

            <div class="actionpad yui-skin-sam">
               <g:buildCreateButtons />
            </div>

        </g:form>
      </div>
    </body>
</html>
