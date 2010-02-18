
<%@ page import="me.hcl.seekin.Internship.Internship" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="internship.create" /></title>
        <g:YUIButtonRessource />
	    <gui:resources components="accordion, autoComplete"/>
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
      <g:form class="boxed_form" name="crud_panel" action="save" method="post" >
          
                <p>
                      <label for="subject"><g:message code="internship.subject" default="Subject" />:</label>
                      <g:textField name="subject" class="field${hasErrors(bean:internshipInstance ,field:'subject','error')}" value="${fieldValue(bean: internshipInstance, field: 'subject')}" />

                </p>
          
                <p>
                      <label for="beginAt"><g:message code="internship.beginAt" default="Begin At" />:</label>
                      <g:datePicker name="beginAt" value="${internshipInstance?.beginAt}"  />

                </p>
          
                <p>
                      <label for="isApproval"><g:message code="internship.isApproval" default="Is Approval" />:</label>
                      <g:checkBox name="isApproval" value="${internshipInstance?.isApproval}" />

                </p>
          
                <p>
                      <label for="student"><g:message code="internship.student" default="Student" />:</label>
                      <g:select name="student.id" from="${student}" optionKey="id" optionValue="value" value="${internshipInstance?.student?.id}" noSelection="['null': '']" />

                </p>
          
                <p>
                      <label for="academicTutor"><g:message code="internship.academicTutor" default="Academic Tutor" />:</label>
                      <g:select name="academicTutor.id" from="${staff}" optionKey="id" optionValue="value" value="${internshipInstance?.academicTutor?.id}" noSelection="['null': '']" />

                </p>

                <p>
                      <label for="company"><g:message code="internship.company" default="Company" />:</label>

                      <gui:autoComplete
                        id="companyName"
                        controller="company"
                        action="listCompanyAsJSON"
                        minQueryLength='2'
                        queryDelay='0.5'
                        value="${company}"

                      />

                </p>

                <p>
                      <label for="firstName"><g:message code="user.firstName" />:</label>
                      <g:textField name="firstName" class="field${hasErrors(bean:internshipInstance?.companyTutor?.user ,field:'firstName','error')}" value="${fieldValue(bean: internshipInstance?.companyTutor?.user, field:'firstName')}" />

                </p>

                <p>
                      <label for="lastName"><g:message code="user.lastName" />:</label>
                      <g:textField name="lastName" class="field${hasErrors(bean:internshipInstance?.companyTutor?.user ,field:'lastName','error')}" value="${fieldValue(bean: internshipInstance?.companyTutor?.user, field:'lastName')}" />

                </p>

                <p>
                      <label for="email"><g:message code="user.email" />:</label>
                      <g:textField name="email" class="field${hasErrors(bean:internshipInstance?.companyTutor?.user ,field:'firstName','error')}" value="${fieldValue(bean: internshipInstance?.companyTutor?.user, field:'firstName')}" />

                </p>
          
          <div class="submit yui-skin-sam">
             <g:buildCreateButtons />
          </div>

      </g:form>

    </body>
</html>
