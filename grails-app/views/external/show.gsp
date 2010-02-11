
<%@ page import="me.hcl.seekin.Auth.Role.External" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="external.show" /></title>
        <g:YUIButtonRessource />
    </head>
    <body>

        <h2><g:message code="external.show" /></h2>
        <g:form class="boxed_form" name="crud_panel">
            <g:if test="${flash.message}">
            <div class="flash_message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:hiddenField name="id" value="${externalInstance?.id}" />
                    
                       <p>
                            <label><g:message code="external.id" default="Id" /></label>
                            <span class="field_value">
                            
                            ${fieldValue(bean: externalInstance, field: "id")}
                            
                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="external.user" default="User" /></label>
                            <span class="field_value">
                            
                            <g:link controller="user" action="show" id="${externalInstance?.user?.id}">${externalInstance?.user?.encodeAsHTML()}</g:link>
                            
                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="external.company" default="Company" /></label>
                            <span class="field_value">
                            
                            <g:link controller="company" action="show" id="${externalInstance?.company?.id}">${externalInstance?.company?.encodeAsHTML()}</g:link>
                            
                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="external.formerStudent" default="Former Student" /></label>
                            <span class="field_value">
                            
                            <g:formatBoolean boolean="${externalInstance?.formerStudent}" />
                            
                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="external.authority" default="Authority" /></label>
                            <span class="field_value">
                            
                            ${fieldValue(bean: externalInstance, field: "authority")}
                            
                            </span>
                      </p>
                        
                      
                      <div class="submit yui-skin-sam">
                        <g:buildShowButtons />
                      </div>
            </g:form>
       </body>
</html>
