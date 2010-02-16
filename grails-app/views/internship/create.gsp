
<%@ page import="me.hcl.seekin.Internship.Internship" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="internship.create" /></title>
        <g:YUIButtonRessource />
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
                      <label for="report"><g:message code="internship.report" default="Report" />:</label>
                      <g:select name="report.id" from="${me.hcl.seekin.Internship.Report.list()}" optionKey="id" value="${internshipInstance?.report?.id}" noSelection="['null': '']" />

                </p>
          
                <p>
                      <label for="student"><g:message code="internship.student" default="Student" />:</label>
                      <g:select name="student.id" from="${me.hcl.seekin.Auth.User.list()}" optionKey="id" value="${internshipInstance?.student?.id}"  />

                </p>
          
                <p>
                      <label for="academicTutor"><g:message code="internship.academicTutor" default="Academic Tutor" />:</label>
                      <g:select name="academicTutor.id" from="${me.hcl.seekin.Auth.User.list()}" optionKey="id" value="${internshipInstance?.academicTutor?.id}" noSelection="['null': '']" />

                </p>
          
                <p>
                      <label for="companyTutor"><g:message code="internship.companyTutor" default="Company Tutor" />:</label>
                      <g:select name="companyTutor.id" from="${me.hcl.seekin.Auth.User.list()}" optionKey="id" value="${internshipInstance?.companyTutor?.id}" noSelection="['null': '']" />

                </p>
          
                <p>
                      <label for="convocation"><g:message code="internship.convocation" default="Convocation" />:</label>
                      <g:select name="convocation.id" from="${me.hcl.seekin.Convocation.list()}" optionKey="id" value="${internshipInstance?.convocation?.id}" noSelection="['null': '']" />

                </p>
          
          <div class="submit yui-skin-sam">
             <g:buildCreateButtons />
          </div>

      </g:form>

    </body>
</html>
