
<%@ page import="me.hcl.seekin.Internship.Internship" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="internship.show" default="Show Internship" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir: '')}"><g:message code="home" default="Home" /></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="internship.list" default="Internship List" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="internship.new" default="New Internship" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="internship.show" default="Show Internship" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:form>
                <g:hiddenField name="id" value="${internshipInstance?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="internship.id" default="Id" />:</td>
                                
                                <td valign="top" class="value">${fieldValue(bean: internshipInstance, field: "id")}</td>
                                
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="internship.subject" default="Subject" />:</td>
                                
                                <td valign="top" class="value">${fieldValue(bean: internshipInstance, field: "subject")}</td>
                                
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="internship.beginAt" default="Begin At" />:</td>
                                
                                <td valign="top" class="value"><g:formatDate date="${internshipInstance?.beginAt}" /></td>
                                
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="internship.isApproval" default="Is Approval" />:</td>
                                
                                <td valign="top" class="value"><g:formatBoolean boolean="${internshipInstance?.isApproval}" /></td>
                                
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="internship.report" default="Report" />:</td>
                                
                                <td valign="top" class="value"><g:link controller="report" action="show" id="${internshipInstance?.report?.id}">${internshipInstance?.report?.encodeAsHTML()}</g:link></td>
                                
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="internship.student" default="Student" />:</td>
                                
                                <td valign="top" class="value"><g:link controller="student" action="show" id="${internshipInstance?.student?.id}">${internshipInstance?.student?.encodeAsHTML()}</g:link></td>
                                
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="internship.academicTutor" default="Academic Tutor" />:</td>
                                
                                <td valign="top" class="value"><g:link controller="staff" action="show" id="${internshipInstance?.academicTutor?.id}">${internshipInstance?.academicTutor?.encodeAsHTML()}</g:link></td>
                                
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="internship.companyTutor" default="Company Tutor" />:</td>
                                
                                <td valign="top" class="value"><g:link controller="external" action="show" id="${internshipInstance?.companyTutor?.id}">${internshipInstance?.companyTutor?.encodeAsHTML()}</g:link></td>
                                
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="internship.convocation" default="Convocation" />:</td>
                                
                                <td valign="top" class="value"><g:link controller="convocation" action="show" id="${internshipInstance?.convocation?.id}">${internshipInstance?.convocation?.encodeAsHTML()}</g:link></td>
                                
                            </tr>
                            
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'edit', 'default': 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'delete', 'default': 'Delete')}" onclick="return confirm('${message(code: 'delete.confirm', 'default': 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
