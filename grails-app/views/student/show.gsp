
<%@ page import="me.hcl.seekin.Auth.Role.Student" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="student.show" /></title>
        <g:YUIButtonRessource />
    </head>
    <body>

        <h2><g:message code="student.show" /></h2>
        <g:form class="boxed_form" name="crud_panel">
            <g:if test="${flash.message}">
            <div class="flash_message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:hiddenField name="id" value="${studentInstance?.id}" />
                    
                       <p>
                            <label><g:message code="student.id" default="Id" /></label>
                            <span class="field_value">
                            
                            ${fieldValue(bean: studentInstance, field: "id")}
                            
                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="student.user" default="User" /></label>
                            <span class="field_value">
                            
                            <g:link controller="user" action="show" id="${studentInstance?.user?.id}">${studentInstance?.user?.encodeAsHTML()}</g:link>
                            
                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="student.visible" default="Visible" /></label>
                            <span class="field_value">
                            
                            <g:formatBoolean boolean="${studentInstance?.visible}" />
                            
                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="student.internships" default="Internships" /></label>
                            <span class="field_value">
                            
                            <ul>
                            <g:each in="${studentInstance?.internships}" var="internshipInstance">
                                <li><g:link controller="internship" action="show" id="${internshipInstance.id}">${internshipInstance.encodeAsHTML()}</g:link></li>
                            </g:each>
                            </ul>
                            
                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="student.authority" default="Authority" /></label>
                            <span class="field_value">
                            
                            ${fieldValue(bean: studentInstance, field: "authority")}
                            
                            </span>
                      </p>
                        
                      
                      <div class="submit yui-skin-sam">
                        <g:buildShowButtons />
                      </div>
            </g:form>
       </body>
</html>
