
<%@ page import="me.hcl.seekin.Auth.Role.Staff" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="staff.show" /></title>
        <g:YUIButtonRessource />
        <tm:resources />
    </head>
    <body>

        <h2><g:message code="staff.show" /></h2>
        <g:form class="boxed_form" name="crud_panel">
            <g:if test="${flash.message}">
            <div class="flash_message"><g:message code="${flash.message}" transparent="true" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:hiddenField name="id" value="${staffInstance?.id}" />
                    
                       <p>
                            <label><g:message code="staff.id" default="Id" /></label>
                            <span class="field_value">
                            
                            ${fieldValue(bean: staffInstance, field: "id")}
                            
                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="staff.user" default="User" /></label>
                            <span class="field_value">
                            
                            <g:link controller="user" action="show" id="${staffInstance?.user?.id}">${staffInstance?.user?.encodeAsHTML()}</g:link>
                            
                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="staff.authority" default="Authority" /></label>
                            <span class="field_value">
                            
                            ${fieldValue(bean: staffInstance, field: "authority")}
                            
                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="staff.roleName" default="Role Name" /></label>
                            <span class="field_value">
                            
                            ${fieldValue(bean: staffInstance, field: "roleName")}
                            
                            </span>
                      </p>
                        
                      
                      <div class="actionpad yui-skin-sam">
                        <g:buildShowButtons />
                      </div>
            </g:form>
       </body>
</html>
