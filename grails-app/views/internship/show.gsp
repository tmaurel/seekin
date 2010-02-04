
<%@ page import="me.hcl.seekin.Internship.Internship" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="internship.show" /></title>
        <g:YUIButtonRessource />
    </head>
    <body>

        <h2><g:message code="internship.show" /></h2>
        <g:form class="boxed_form" name="crud_panel">
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:hiddenField name="id" value="${internshipInstance?.id}" />
                    
                       <p>
                            <label><g:message code="internship.id" default="Id" /></label>
                            
                            ${fieldValue(bean: internshipInstance, field: "id")}
                            

                        
                       <p>
                            <label><g:message code="internship.subject" default="Subject" /></label>
                            
                            ${fieldValue(bean: internshipInstance, field: "subject")}
                            

                        
                       <p>
                            <label><g:message code="internship.beginAt" default="Begin At" /></label>
                            
                            <g:formatDate date="${internshipInstance?.beginAt}" />
                            

                        
                       <p>
                            <label><g:message code="internship.isApproval" default="Is Approval" /></label>
                            
                            <g:formatBoolean boolean="${internshipInstance?.isApproval}" />
                            

                        
                       <p>
                            <label><g:message code="internship.report" default="Report" /></label>
                            
                            <g:link controller="report" action="show" id="${internshipInstance?.report?.id}">${internshipInstance?.report?.encodeAsHTML()}</g:link>
                            

                        
                       <p>
                            <label><g:message code="internship.student" default="Student" /></label>
                            
                            <g:link controller="student" action="show" id="${internshipInstance?.student?.id}">${internshipInstance?.student?.encodeAsHTML()}</g:link>
                            

                        
                       <p>
                            <label><g:message code="internship.academicTutor" default="Academic Tutor" /></label>
                            
                            <g:link controller="staff" action="show" id="${internshipInstance?.academicTutor?.id}">${internshipInstance?.academicTutor?.encodeAsHTML()}</g:link>
                            

                        
                       <p>
                            <label><g:message code="internship.companyTutor" default="Company Tutor" /></label>
                            
                            <g:link controller="external" action="show" id="${internshipInstance?.companyTutor?.id}">${internshipInstance?.companyTutor?.encodeAsHTML()}</g:link>
                            

                        
                       <p>
                            <label><g:message code="internship.convocation" default="Convocation" /></label>
                            
                            <g:link controller="convocation" action="show" id="${internshipInstance?.convocation?.id}">${internshipInstance?.convocation?.encodeAsHTML()}</g:link>
                            

                        
                      </p>
                      <div class="submit yui-skin-sam">
                        <g:buildShowButtons />
                      </div>
            </g:form>
       </body>
</html>
