
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
            <div class="flash_message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:hiddenField name="id" value="${internshipInstance?.id}" />
                    
                       <p>
                            <label><g:message code="internship.id" default="Id" /></label>
                            <span class="field_value">
                            
                            ${fieldValue(bean: internshipInstance, field: "id")}
                            
                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="internship.subject" default="Subject" /></label>
                            <span class="field_value">
                            
                            ${fieldValue(bean: internshipInstance, field: "subject")}
                            
                            </span>
                      </p>

                       <p>
                            <label><g:message code="internship.description" default="Description" /></label>
                            <span class="field_value">

                            ${fieldValue(bean: internshipInstance, field: "description")}

                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="internship.beginAt" default="Begin At" /></label>
                            <span class="field_value">
                            
                            ${internshipInstance?.beginAt?.formatDate()}
                            
                            </span>
                      </p>

                       <p>
                            <label><g:message code="internship.length" default="Length" /></label>
                            <span class="field_value">

                            ${fieldValue(bean: internshipInstance, field: "length")}

                            </span>
                      </p>

                       <p>
                            <label><g:message code="internship.reason" default="Reason" /></label>
                            <span class="field_value">

                            ${fieldValue(bean: internshipInstance, field: "reason")}

                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="internship.isApproval" default="Is Approval" /></label>
                            <span class="field_value">
                            
                            <g:formatBoolean boolean="${internshipInstance?.isApproval}" />
                            
                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="internship.report" default="Report" /></label>
                            <span class="field_value">
                            
                            <g:link controller="report" action="show" id="${internshipInstance?.report?.id}">${internshipInstance?.report?.title?.encodeAsHTML()}</g:link>
                            
                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="internship.student" default="Student" /></label>
                            <span class="field_value">
                            
                            <g:link controller="user" action="show" id="${internshipInstance?.student?.id}">${internshipInstance?.student?.user?.firstName?.encodeAsHTML()} ${internshipInstance?.student?.user?.lastName?.encodeAsHTML()}</g:link>

                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="internship.academicTutor" default="Academic Tutor" /></label>
                            <span class="field_value">
                            
                            <g:link controller="user" action="show" id="${internshipInstance?.academicTutor?.id}">${internshipInstance?.academicTutor?.user?.firstName?.encodeAsHTML()} ${internshipInstance?.academicTutor?.user?.lastName?.encodeAsHTML()}</g:link>
                            
                            </span>
                      </p>

                       <p>
                            <label><g:message code="internship.company" default="Company" /></label>
                            <span class="field_value">

                            <g:link controller="company" action="show" id="${internshipInstance?.company?.id}">${internshipInstance?.company?.name?.encodeAsHTML()}</g:link>

                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="internship.companyTutor" default="Company Tutor" /></label>
                            <span class="field_value">
                            
                            <g:link controller="user" action="show" id="${internshipInstance?.companyTutor?.id}">${internshipInstance?.companyTutor?.user?.firstName?.encodeAsHTML()} ${internshipInstance?.companyTutor?.user?.lastName?.encodeAsHTML()}</g:link>
                            
                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="internship.convocation" default="Convocation" /></label>
                            <span class="field_value">
                            
                            <g:link controller="convocation" action="show" id="${internshipInstance?.convocation?.id}">${internshipInstance?.convocation?.date?.formatDate().encodeAsHTML()}</g:link>
                            
                            </span>
                      </p>
                        
                      
                      <div class="submit yui-skin-sam">
                        <g:buildShowButtons />
                      </div>
            </g:form>
       </body>
</html>
