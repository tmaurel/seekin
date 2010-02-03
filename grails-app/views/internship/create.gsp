
<%@ page import="me.hcl.seekin.Internship.Internship" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="internship.create" default="Create Internship" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir: '')}"><g:message code="home" default="Home" /></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="internship.list" default="Internship List" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="internship.create" default="Create Internship" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:hasErrors bean="${internshipInstance}">
            <div class="errors">
                <g:renderErrors bean="${internshipInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="subject"><g:message code="internship.subject" default="Subject" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: internshipInstance, field: 'subject', 'errors')}">
                                    <g:textField name="subject" value="${fieldValue(bean: internshipInstance, field: 'subject')}" />

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="beginAt"><g:message code="internship.beginAt" default="Begin At" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: internshipInstance, field: 'beginAt', 'errors')}">
                                    <g:datePicker name="beginAt" value="${internshipInstance?.beginAt}"  />

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="isApproval"><g:message code="internship.isApproval" default="Is Approval" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: internshipInstance, field: 'isApproval', 'errors')}">
                                    <g:checkBox name="isApproval" value="${internshipInstance?.isApproval}" />

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="report"><g:message code="internship.report" default="Report" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: internshipInstance, field: 'report', 'errors')}">
                                    <g:select name="report.id" from="${me.hcl.seekin.Internship.Report.list()}" optionKey="id" value="${internshipInstance?.report?.id}" noSelection="['null': '']" />

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="student"><g:message code="internship.student" default="Student" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: internshipInstance, field: 'student', 'errors')}">
                                    <g:select name="student.id" from="${me.hcl.seekin.Profile.Student.list()}" optionKey="id" value="${internshipInstance?.student?.id}"  />

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="academicTutor"><g:message code="internship.academicTutor" default="Academic Tutor" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: internshipInstance, field: 'academicTutor', 'errors')}">
                                    <g:select name="academicTutor.id" from="${me.hcl.seekin.Profile.Staff.list()}" optionKey="id" value="${internshipInstance?.academicTutor?.id}"  />

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="companyTutor"><g:message code="internship.companyTutor" default="Company Tutor" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: internshipInstance, field: 'companyTutor', 'errors')}">
                                    <g:select name="companyTutor.id" from="${me.hcl.seekin.Profile.External.list()}" optionKey="id" value="${internshipInstance?.companyTutor?.id}"  />

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="convocation"><g:message code="internship.convocation" default="Convocation" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: internshipInstance, field: 'convocation', 'errors')}">
                                    <g:select name="convocation.id" from="${me.hcl.seekin.Convocation.list()}" optionKey="id" value="${internshipInstance?.convocation?.id}" noSelection="['null': '']" />

                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'create', 'default': 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
