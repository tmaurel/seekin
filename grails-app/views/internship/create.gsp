
<%@ page import="me.hcl.seekin.Internship.Internship" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="internship.create" /></title>
        <g:YUIButtonRessource />
	    <gui:resources components="accordion, autoComplete, tabView"/>
    </head>
    <body>  
      <h2><g:message code="internship.create" /></h2>
      <g:if test="${flash.message}">
      <div class="flash_message"><g:message code="${flash.message}" args="${flash.args}" /></div>
      </g:if>
      <g:hasErrors bean="${internshipInstance}">
      <div class="flash_message">
          <g:renderErrors bean="${internshipInstance}" as="list" />
      </div>

      </g:hasErrors>

      <div class = "yui-skin-sam">
        <gui:tabView>
          <gui:tab label="${message(code: 'internship.createFromZero')}" active="true">
            <g:form class="boxed_form" name="crud_panel" action="save" method="post" >

                      <p>
                            <label for="subject"><g:message code="internship.subject" default="Subject" />:</label>
                            <g:textField name="subject" class="field${hasErrors(bean:internshipInstance ,field:'subject','error')}" value="${fieldValue(bean: internshipInstance, field: 'subject')}" />

                      </p>

                      <p>
                            <label for="description"><g:message code="internship.description" default="Description" />:</label>
                            <g:textField name="description" class="field${hasErrors(bean:internshipInstance ,field:'description','error')}" value="${fieldValue(bean: internshipInstance, field: 'description')}" />

                      </p>

                      <p>
                            <label for="beginAt"><g:message code="internship.beginAt" default="Begin At" />:</label>
                            <g:datePicker name="beginAt" value="${internshipInstance?.beginAt}"  />

                      </p>

                      <p>
                            <label for="length"><g:message code="internship.length" default="Length" />:</label>
                            <g:textField name="length" class="field${hasErrors(bean:internshipInstance ,field:'length','error')}" value="${fieldValue(bean: internshipInstance, field: 'length')}" />

                      </p>

                      <div>
                            <label for="company"><g:message code="internship.company" default="Company" />:</label>

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
                            <label for="firstName"><g:message code="companyTutor.firstName" />:</label>
                            <g:textField name="firstName" class="field${hasErrors(bean:internshipInstance?.companyTutor?.user ,field:'firstName','error')}" value="${firstName}" />

                      </p>

                      <p>
                            <label for="lastName"><g:message code="companyTutor.lastName" />:</label>
                            <g:textField name="lastName" class="field${hasErrors(bean:internshipInstance?.companyTutor?.user ,field:'lastName','error')}" value="${lastName}" />

                      </p>

                      <p>
                            <label for="email"><g:message code="companyTutor.email" />:</label>
                            <g:textField name="email" class="field${hasErrors(bean:internshipInstance?.companyTutor?.user ,field:'firstName','error')}" value="${email}" />

                      </p>

                      <g:ifAnyGranted role="ROLE_ADMIN,ROLE_FORMATIONMANAGER">

                            <p>
                                  <label for="student"><g:message code="internship.student" default="Student" />:</label>
                                  <g:select name="student.id" from="${student}" optionKey="id" optionValue="value" value="${internshipInstance?.student?.id}" noSelection="['null': '']" />

                            </p>

                            <p>
                                  <label for="academicTutor"><g:message code="internship.academicTutor" default="Academic Tutor" />:</label>
                                  <g:select name="academicTutor.id" from="${staff}" optionKey="id" optionValue="value" value="${internshipInstance?.academicTutor?.id}" noSelection="['null': '']" />

                            </p>

                            <p>
                                  <label for="isApproval"><g:message code="internship.isApproval" default="Is Approval" />:</label>
                                  <g:checkBox name="isApproval" value="${internshipInstance?.isApproval}" />

                            </p>
                        
                      </g:ifAnyGranted>

                <div class="actionpad yui-skin-sam">
                   <g:buildCreateButtons />
                </div>

            </g:form>
          </gui:tab>
          <gui:tab label="${message(code: 'internship.createFromOffer')}">
                  <g:form class="boxed_form" name="offer_form" action="create">
                        <p>
                          <label for="offers"><g:message code="offers.list" default="Offers list" />:</label>
                          <g:select name="offer.id" from="${offer}" optionKey="id" optionValue="value" noSelection="['null': '']" onChange="offer_form.submit()" />
                        </p>
                  </g:form>
          </gui:tab>
        </gui:tabView>
      </div>

    </body>
</html>
